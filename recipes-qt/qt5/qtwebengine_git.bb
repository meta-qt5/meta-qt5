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
    libpng-native \
    nss-native \
    nspr-native \
    ninja-native \
    nasm-native \
    bison-native \
    qtwebchannel \
    qtbase qtdeclarative qtxmlpatterns qtquickcontrols qtquickcontrols2 \
    qtlocation \
    libdrm fontconfig pixman openssl pango cairo icu pciutils nss \
    libcap \
    gperf-native \
    ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'alsa-lib', '', d)} \
"

DEPENDS_append_libc-musl = " libexecinfo"

EXTRA_QMAKEVARS_CONFIGURE += "-feature-webengine-system-ninja -no-feature-webengine-system-gn"
EXTRA_QMAKEVARS_PRE += "CONFIG+=force_debug_info"

# chromium/third_party/openh264/openh264.gyp adds
# -Wno-format to openh264_cflags_add
# similarly chromium/third_party/openh264/BUILD.gn for newer qtwebengine
# causing following error, because -Wformat-security cannot be used together with -Wno-format
# cc1plus: error: -Wformat-security ignored without -Wformat [-Werror=format-security]
# http://errors.yoctoproject.org/Errors/Details/150333/
SECURITY_STRINGFORMAT = ""

# To use system ffmpeg you need to enable also libwebp, opus, vpx
# Only depenedencies available in oe-core are enabled by default
PACKAGECONFIG ??= "libwebp libevent libpng"
PACKAGECONFIG[icu] = "-feature-webengine-system-icu,-no-feature-webengine-system-icu,icu"
PACKAGECONFIG[ffmpeg] = "-feature-webengine-system-ffmpeg,-no-feature-webengine-system-ffmpeg,libav"
PACKAGECONFIG[webrtc] = "-feature-webengine-webrtc,-no-feature-webengine-webrtc,libvpx"
PACKAGECONFIG[libwebp] = "-feature-webengine-system-libwebp,-no-feature-webengine-system-libwebp,libwebp"
PACKAGECONFIG[opus] = "-feature-webengine-system-opus,-no-feature-webengine-system-opus,libopus"
PACKAGECONFIG[libvpx] = "-feature-webengine-system-libvpx,-no-feature-webengine-system-libvpx,libvpx"
PACKAGECONFIG[libevent] = "-feature-webengine-system-libevent,-no-feature-webengine-system-libevent,libevent"
PACKAGECONFIG[libpng] = "-feature-webengine-system-png,-no-feature-webengine-system-png,libpng"
PACKAGECONFIG[harfbuzz] = "-feature-webengine-system-harfbuzz,-no-feature-webengine-system-harfbuzz,harfbuzz"
PACKAGECONFIG[glib] = "-feature-webengine-system-glib,-no-feature-webengine-system-glib,glib-2.0"
PACKAGECONFIG[zlib] = "-feature-webengine-system-zlib,-no-feature-webengine-system-zlib,zlib"
PACKAGECONFIG[protobuf] = "-feature-webengine-system-protobuf,-no-feature-webengine-system-protobuf,protobuf"
PACKAGECONFIG[jasoncpp] = "-feature-webengine-system-jsoncpp,-no-feature-webengine-system-jsoncpp,jasoncpp"
PACKAGECONFIG[libxml2] = "-feature-webengine-system-libxml2,-no-feature-webengine-system-libxml2,libxml2"
PACKAGECONFIG[minizip] = "-feature-webengine-system-minizip,-no-feature-webengine-system-minizip,minizip"
PACKAGECONFIG[proprietary-codecs] = "-feature-webengine-proprietary-codecs,-no-feature-webengine-proprietary-codecs"
PACKAGECONFIG[pepper-plugins] = "-feature-webengine-pepper-plugins,-no-feature-webengine-pepper-plugins"
PACKAGECONFIG[printing-and-pdf] = "-feature-webengine-printing-and-pdf,-no-feature-webengine-printing-and-pdf"
PACKAGECONFIG[spellchecker] = "-feature-webengine-spellchecker,-no-feature-webengine-spellchecker"

EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

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
inherit distro_features_check

# Static builds of QtWebEngine aren't supported.
CONFLICT_DISTRO_FEATURES = "qt5-static"

# we don't want gettext.bbclass to append --enable-nls
def gettext_oeconf(d):
    return ""

require qt5.inc
require qt5-git.inc

export GN_PKG_CONFIG_HOST = "${STAGING_BINDIR_NATIVE}/pkg-config-native"
export GN_HOST_TOOLCHAIN_EXTRA_CPPFLAGS = "-I${STAGING_DIR_NATIVE}/usr/include"
export NINJAFLAGS="${PARALLEL_MAKE}"

do_configure() {

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

do_configure_prepend_libc-musl() {
        for f in `find ${S}/src/3rdparty/chromium/third_party/ffmpeg/chromium/config/Chromium/linux/ -name config.h -o -name config.asm`; do
                sed -i -e "s:define HAVE_SYSCTL 1:define HAVE_SYSCTL 0:g" $f
        done
        sed -i -e "s:define HAVE_STRUCT_MALLINFO 1:/*undef HAVE_STRUCT_MALLINFO */:g" ${S}/src/3rdparty/chromium/third_party/tcmalloc/chromium/src/config_linux.h
}

do_compile[progress] = "outof:^\[(\d+)/(\d+)\]\s+"

do_install_append() {
    sed -i 's@ -Wl,--start-group.*-Wl,--end-group@@g; s@[^ ]*${B}[^ ]* @@g' ${D}${libdir}/pkgconfig/Qt5WebEngineCore.pc
}
PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"

# for /usr/share/qt5/qtwebengine_resources.pak
FILES_${PN} += "${OE_QMAKE_PATH_QT_TRANSLATIONS} ${OE_QMAKE_PATH_QT_DATA}"

# Chromium uses libpci to determine which optimizations/workarounds to apply
RDEPENDS_${PN}_append_x86 = " libpci"

RDEPENDS_${PN}-examples += " \
    ${PN}-qmlplugins \
    qtquickcontrols-qmlplugins \
    qtdeclarative-qmlplugins \
"

QT_MODULE_BRANCH_CHROMIUM = "65-based"

# Patches from https://github.com/meta-qt5/qtwebengine/commits/b5.11
# 5.11.meta-qt5.8
SRC_URI += " \
    ${QT_GIT}/qtwebengine-chromium.git;name=chromium;branch=${QT_MODULE_BRANCH_CHROMIUM};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty \
    file://0001-Force-host-toolchain-configuration.patch \
    file://0002-chromium_overrides.cpp-Fix-build-with-plugins-and-oz.patch \
"
SRC_URI_append_libc-musl = "\
    file://0003-musl-don-t-use-pvalloc-as-it-s-not-available-on-musl.patch \
    file://0004-musl-link-against-libexecinfo.patch \
"

# Patches from https://github.com/meta-qt5/qtwebengine-chromium/commits/65-based
# 65-based.meta-qt5.7
SRC_URI += " \
    file://chromium/0001-chromium-Force-host-toolchain-configuration.patch;patchdir=src/3rdparty \
    file://chromium/0002-chromium-workaround-for-too-long-.rps-file-name.patch;patchdir=src/3rdparty \
    file://chromium/0003-chromium-Fix-build-with-gcc8.patch;patchdir=src/3rdparty \
"

SRC_URI_append_libc-musl = "\
    file://chromium/0004-chromium-musl-sandbox-Define-TEMP_FAILURE_RETRY-if-n.patch;patchdir=src/3rdparty \
    file://chromium/0005-chromium-musl-Avoid-mallinfo-APIs-on-non-glibc-linux.patch;patchdir=src/3rdparty \
    file://chromium/0006-chromium-musl-include-fcntl.h-for-loff_t.patch;patchdir=src/3rdparty \
    file://chromium/0007-chromium-musl-use-off64_t-instead-of-the-internal-__.patch;patchdir=src/3rdparty \
    file://chromium/0008-chromium-musl-linux-glibc-make-the-distinction.patch;patchdir=src/3rdparty \
    file://chromium/0009-chromium-musl-allocator-Do-not-include-glibc_weak_sy.patch;patchdir=src/3rdparty \
    file://chromium/0010-chromium-musl-Use-correct-member-name-__si_fields-fr.patch;patchdir=src/3rdparty \
    file://chromium/0011-chromium-musl-Define-res_ninit-and-res_nclose-for-no.patch;patchdir=src/3rdparty \
    file://chromium/0012-chromium-musl-Do-not-define-__sbrk-on-musl.patch;patchdir=src/3rdparty \
    file://chromium/0013-chromium-musl-Adjust-default-pthread-stack-size.patch;patchdir=src/3rdparty \
    file://chromium/0014-chromium-musl-include-asm-generic-ioctl.h-for-TCGETS.patch;patchdir=src/3rdparty \
    file://chromium/0015-chromium-musl-tcmalloc-Use-off64_t-insread-of-__off6.patch;patchdir=src/3rdparty \
    file://chromium/0016-chromium-musl-Use-_fpstate-instead-of-_libc_fpstate-.patch;patchdir=src/3rdparty \
    file://chromium/0017-chromium-musl-elf_reader.cc-include-sys-reg.h-to-get.patch;patchdir=src/3rdparty \
"

SRCREV_qtwebengine = "18412af977d658f243eb5b25b62284924cfa362f"
SRCREV_chromium = "2095a35f300bcbe82e968e9a547dff7c79be29d8"
SRCREV = "${SRCREV_qtwebengine}"

SRCREV_FORMAT = "qtwebengine_chromium"

# WARNING: qtwebengine-5.5.99+5.6.0-rc+gitAUTOINC+3f02c25de4_779a2388fc-r0 do_package_qa: QA Issue: ELF binary '/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtwebengine/5.5.99+5.6.0-rc+gitAUTOINC+3f02c25de4_779a2388fc-r0/packages-split/qtwebengine/usr/lib/libQt5WebEngineCore.so.5.6.0' has relocations in .text [textrel]
INSANE_SKIP_${PN} += "textrel"
