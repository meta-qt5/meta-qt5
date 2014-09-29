SUMMARY = "QtWebEngine combines the power of Chromium and Qt"
LICENSE = "LGPL-2.1 & BSD"
LIC_FILES_CHKSUM = " \
  file://src/core/browser_context_qt.cpp;md5=c23e24b6a534c8b6cc879a397b35db29;beginline=1;endline=40 \
  file://src/3rdparty/chromium/LICENSE;md5=d2d164565cc10f298390174d9cb6d18d \
"
DEPENDS += " \
    ninja-native \
    qtbase qtdeclarative qtxmlpatterns qtquickcontrols \
    libdrm fontconfig pixman openssl pango cairo icu pciutils \
"

inherit qmake5
inherit gettext
inherit pythonnative
inherit perlnative

require recipes-qt/qt5/qt5.inc

S = "${WORKDIR}/git"

# To avoid trouble start with not separated build directory
SEPB = "${S}"
B = "${SEPB}"

# Project ERROR: Missing CMake tests. Either create tests in tests/auto/cmake, or disable cmake config file creation with CONFIG-=create_cmake.
EXTRA_QMAKEVARS_POST += "CONFIG-=create_cmake"

do_configure() {
    # replace LD with CXX, to workaround a possible gyp inheritssue?
    LD="${CXX}" export LD
    CC="${CC}" export CC
    CXX="${CXX}" export CXX
    CC_host="gcc" export CC_host
    CXX_host="g++" export CXX_host

    # qmake can't find the OE_QMAKE_* variables on it's own so directly passing them as
    # arguments here
    ${OE_QMAKE_QMAKE} -r ${EXTRA_QMAKEVARS_PRE} QTWEBENGINE_ROOT="${S}" NINJA_PATH="${STAGING_BINDIR_NATIVE}/ninja" \
        QMAKE_CXX="${OE_QMAKE_CXX}" QMAKE_CC="${OE_QMAKE_CC}" \
        QMAKE_LINK="${OE_QMAKE_LINK}" \
        QMAKE_CFLAGS="${OE_QMAKE_CFLAGS}" \
        QMAKE_CXXFLAGS="${OE_QMAKE_CXXFLAGS}" \
        QMAKE_AR="${OE_QMAKE_AR}" \
        -after ${EXTRA_QMAKEVARS_POST}
}

do_install_append() {
    rmdir ${D}${OE_QMAKE_PATH_PLUGINS}/${BPN} ${D}${OE_QMAKE_PATH_PLUGINS} || true
}
PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"
