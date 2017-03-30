SUMMARY = "QtWebEngine combines the power of Chromium and Qt"

# Read http://blog.qt.io/blog/2016/01/13/new-agreement-with-the-kde-free-qt-foundation/
LICENSE = "BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( LGPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://src/core/browser_context_qt.cpp;md5=b5193b7d68699260f3b40b201365c8d2;beginline=1;endline=38 \
    file://src/3rdparty/chromium/LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d \
    file://LICENSE.LGPL3;md5=8211fde12cc8a4e2477602f5953f5b71 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

DEPENDS += " \
    ninja-native \
    yasm-native \
    qtwebchannel \
    qtbase qtdeclarative qtxmlpatterns qtquickcontrols \
    qtlocation \
    libdrm fontconfig pixman openssl pango cairo icu pciutils \
    libcap \
    gperf-native \
    ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'alsa-lib', '', d)} \
"

EXTRA_QMAKEVARS_PRE += "GYP_CONFIG+=use_system_yasm GYP_CONFIG+=generate_character_data=0"
EXTRA_QMAKEVARS_CONFIGURE += "-feature-system-ninja -no-feature-system-gn"

# To use system ffmpeg you need to enable also libwebp, opus, vpx											    
# Only depenedencies available in oe-core are enabled by default
PACKAGECONFIG ??= "libwebp flac libevent libxslt speex"
PACKAGECONFIG[opus] = "WEBENGINE_CONFIG+=use_system_opus,,libopus"
PACKAGECONFIG[icu] = "WEBENGINE_CONFIG+=use_system_icu,,icu"
PACKAGECONFIG[ffmpeg] = "WEBENGINE_CONFIG+=use_system_ffmpeg,,libav"
PACKAGECONFIG[libwebp] = "WEBENGINE_CONFIG+=use_system_libwebp,,libwebp"
PACKAGECONFIG[flac] = "WEBENGINE_CONFIG+=use_system_flac,,flac"
PACKAGECONFIG[libevent] = "WEBENGINE_CONFIG+=use_system_libevent,,libevent"
PACKAGECONFIG[libxslt] = "WEBENGINE_CONFIG+=use_system_libxslt,,libxslt"
PACKAGECONFIG[speex] = "WEBENGINE_CONFIG+=use_system_speex,,speex"
PACKAGECONFIG[vpx] = "WEBENGINE_CONFIG+=use_system_vpx,,libvpx"

EXTRA_QMAKEVARS_PRE += "${PACKAGECONFIG_CONFARGS}"

COMPATIBLE_MACHINE = "(-)"
COMPATIBLE_MACHINE_x86 = "(.*)"
COMPATIBLE_MACHINE_x86-64 = "(.*)"
COMPATIBLE_MACHINE_armv6 = "(.*)"
COMPATIBLE_MACHINE_armv7a = "(.*)"
COMPATIBLE_MACHINE_armv7ve = "(.*)"
COMPATIBLE_MACHINE_aarch64 = "(.*)"

inherit qmake5
inherit gettext
inherit pythonnative
inherit perlnative

# we don't want gettext.bbclass to append --enable-nls
def gettext_oeconf(d):
    return ""

require qt5.inc
require qt5-git.inc

do_configure() {
    # Disable autodetection from sysroot:
    sed -e 's/packagesExist([^)]*vpx[^)]*):/false:/g'\
        -e 's/config_libvpx:/false:/g' \
        -e 's/config_srtp:/false:/g' \
        -e 's/config_snappy:/false:/g' \
        -e 's/packagesExist(nss):/false:/g' \
        -e 's/packagesExist(minizip, zlib):/false:/g' \
        -e 's/packagesExist(libwebp,libwebpdemux):/false:/g' \
        -e 's/packagesExist(libxml-2.0,libxslt):/false:/g'\
        -e 's/^ *packagesExist($$package):/false:/g' \
        -i ${S}/tools/qmake/mkspecs/features/configure.prf

    # qmake can't find the OE_QMAKE_* variables on it's own so directly passing them as
    # arguments here
    ${OE_QMAKE_QMAKE} ${EXTRA_QMAKEVARS_PRE} ${S} \
        QMAKE_CXX="${OE_QMAKE_CXX}" \
        QMAKE_CC="${OE_QMAKE_CC}" \
        QMAKE_LINK="${OE_QMAKE_LINK}" \
        QMAKE_CFLAGS="${OE_QMAKE_CFLAGS}" \
        QMAKE_CXXFLAGS="${OE_QMAKE_CXXFLAGS}" \
        -after ${EXTRA_QMAKEVARS_POST} -- \
        ${EXTRA_QMAKEVARS_CONFIGURE}
}

do_compile[progress] = "outof:^\[(\d+)/(\d+)\]\s+"

do_install_append() {
    sed -i 's@ -Wl,--start-group.*-Wl,--end-group@@g; s@[^ ]*${B}[^ ]* @@g' ${D}${libdir}/pkgconfig/Qt5WebEngineCore.pc
}
PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"

# for /usr/share/qt5/qtwebengine_resources.pak
FILES_${PN} += "${OE_QMAKE_PATH_QT_TRANSLATIONS} ${OE_QMAKE_PATH_QT_DATA}"

RDEPENDS_${PN}-examples += " \
    ${PN}-qmlplugins \
    qtquickcontrols-qmlplugins \
    qtdeclarative-qmlplugins \
"

QT_MODULE_BRANCH_CHROMIUM = "56-based"

SRC_URI += " \
    ${QT_GIT}/qtwebengine-chromium.git;name=chromium;branch=${QT_MODULE_BRANCH_CHROMIUM};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty \
    file://0001-Force-host-toolchain-configuration.patch \
    file://0002-chromium-Change-false-to-FALSE-and-1-to-TRUE-FIX-qtw.patch \
"

SRCREV_qtwebengine = "57fee86eb2756a2f102e2bbeb16a443b84a17e01"
SRCREV_chromium = "bba44efa6bb5707bf6fb4e92627e3326be3f3a3b"
SRCREV = "${SRCREV_qtwebengine}"

SRCREV_FORMAT = "qtwebengine_chromium"

# WARNING: qtwebengine-5.5.99+5.6.0-rc+gitAUTOINC+3f02c25de4_779a2388fc-r0 do_package_qa: QA Issue: ELF binary '/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtwebengine/5.5.99+5.6.0-rc+gitAUTOINC+3f02c25de4_779a2388fc-r0/packages-split/qtwebengine/usr/lib/libQt5WebEngineCore.so.5.6.0' has relocations in .text [textrel]
INSANE_SKIP_${PN} += "textrel"

# Ninja may fail when path is too long, try to make it a bit shorter
WORKDIR = "${BASE_WORKDIR}/${MULTIMACH_TARGET_SYS}/${PN}/${QT_MODULE_BRANCH}"
