SUMMARY = "QtWebEngine combines the power of Chromium and Qt"

LICENSE = "LGPL-3.0 & BSD"
LIC_FILES_CHKSUM = " \
    file://src/core/browser_context_qt.cpp;md5=5fe719c44250955a5d5f8fb15fc8b1da;beginline=1;endline=35 \
    file://src/3rdparty/chromium/LICENSE;md5=537e0b52077bf0a616d0a0c8a79bc9d5 \
    file://LICENSE.LGPLv3;md5=3dcffeed712d3c916f9a2d9135703aff \
    file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb \
    file://LICENSE.GPLv2;md5=05832301944453ec79e40ba3c3cfceec \
"

DEPENDS += " \
    ninja-native \
    qtwebchannel \
    qtbase qtdeclarative qtxmlpatterns qtquickcontrols \
    libdrm fontconfig pixman openssl pango cairo icu pciutils \
    libcap \
"

# when qtbase is built with xcb enabled (default with x11 in DISTRO_FEATURES),
# qtwebengine will have additional dependencies:
# contains(QT_CONFIG, xcb): REQUIRED_PACKAGES += libdrm xcomposite xcursor xi xrandr xscrnsaver xtst
# xscreensaver isn't covered in qtbase DEPENDS
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'x11', 'libxscrnsaver', '', d)}"

DEPENDS += "yasm-native"
EXTRA_QMAKEVARS_PRE += "GYP_CONFIG+=use_system_yasm"

COMPATIBLE_MACHINE = "(-)"
COMPATIBLE_MACHINE_x86 = "(.*)"
COMPATIBLE_MACHINE_x86-64 = "(.*)"
COMPATIBLE_MACHINE_armv6 = "(.*)"
COMPATIBLE_MACHINE_armv7a = "(.*)"

inherit qmake5
inherit gettext
inherit pythonnative
inherit perlnative

require qt5.inc
require qt5-git.inc

# To avoid trouble start with not separated build directory
SEPB = "${S}"
B = "${SEPB}"

export NINJA_PATH="${STAGING_BINDIR_NATIVE}/ninja"

do_configure() {
    # replace LD with CXX, to workaround a possible gyp inheritssue?
    export LD="${CXX}"
    export CC="${CC}"
    export CXX="${CXX}"
    export CC_host="gcc"
    export CXX_host="g++"
    export QMAKE_MAKE_ARGS="${EXTRA_OEMAKE}"

    # qmake can't find the OE_QMAKE_* variables on it's own so directly passing them as
    # arguments here
    ${OE_QMAKE_QMAKE} -r ${EXTRA_QMAKEVARS_PRE} QTWEBENGINE_ROOT="${S}" \
        QMAKE_CXX="${OE_QMAKE_CXX}" QMAKE_CC="${OE_QMAKE_CC}" \
        QMAKE_LINK="${OE_QMAKE_LINK}" \
        QMAKE_CFLAGS="${OE_QMAKE_CFLAGS}" \
        QMAKE_CXXFLAGS="${OE_QMAKE_CXXFLAGS}" \
        QMAKE_AR="${OE_QMAKE_AR} cqs" \
        -after ${EXTRA_QMAKEVARS_POST}
}

do_install_append() {
    rmdir ${D}${OE_QMAKE_PATH_PLUGINS}/${BPN} ${D}${OE_QMAKE_PATH_PLUGINS} || true
}
PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"

# for /usr/share/qt5/qtwebengine_resources.pak
FILES_${PN} += "${OE_QMAKE_PATH_QT_TRANSLATIONS} ${OE_QMAKE_PATH_QT_DATA}"

RDEPENDS_${PN}-examples += " \
    ${PN}-qmlplugins \
    qtquickcontrols-qmlplugins \
    qtdeclarative-qmlplugins \
"

QT_MODULE_BRANCH_CHROMIUM = "45-based"

SRC_URI += " \
    ${QT_GIT}/qtwebengine-chromium.git;name=chromium;branch=${QT_MODULE_BRANCH_CHROMIUM};destsuffix=git/src/3rdparty \
    file://0001-functions.prf-Don-t-match-QMAKE_EXT_CPP-or-QMAKE_EXT.patch \
    file://0002-functions.prf-Make-sure-we-only-use-the-file-name-to.patch \
    file://0003-functions.prf-allow-build-for-linux-oe-g-platform.patch \
    file://0001-chromium-base.gypi-include-atomicops_internals_x86_g.patch \
"
SRCREV_qtwebengine = "40ef43e0d69c4a86c9430b7f264d2cde6340ee0f"
SRCREV_chromium = "ec5b3304fc266dfdec7666b8b73d57a3971ea35f"
SRCREV = "${SRCREV_qtwebengine}"

SRCREV_FORMAT = "qtwebengine_chromium"

S = "${WORKDIR}/git"
