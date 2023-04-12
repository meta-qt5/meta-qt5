SUMMARY = "Qt Pdf support"

# Read http://blog.qt.io/blog/2016/01/13/new-agreement-with-the-kde-free-qt-foundation/
LICENSE = "BSD-3-Clause & ( GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( LGPL-3.0-only | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://src/3rdparty/chromium/LICENSE;md5=0fca02217a5d49a14dfe2d11837bb34d \
    file://LICENSE.LGPL3;md5=8211fde12cc8a4e2477602f5953f5b71 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
"

DEPENDS += " \
    libpng-native \
    ninja-native \
    bison-native \
    qtbase qtdeclarative qtxmlpatterns qtquickcontrols qtquickcontrols2 \
    jpeg-native \
    freetype-native \
    gperf-native \
    ${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'alsa-lib', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libxcomposite libxcursor libxi libxrandr libxtst libxkbfile', '', d)} \
"

DEPENDS:append:libc-musl = " libexecinfo"

inherit pkgconfig

EXTRA_QMAKEVARS_CONFIGURE += "-feature-webengine-system-ninja -no-feature-webengine-system-gn"
EXTRA_QMAKEVARS_PRE += "CONFIG+=force_debug_info"

# chromium/third_party/openh264/openh264.gyp adds
# -Wno-format to openh264_cflags_add
# similarly chromium/third_party/openh264/BUILD.gn for newer qtwebengine
# causing following error, because -Wformat-security cannot be used together with -Wno-format
# cc1plus: error: -Wformat-security ignored without -Wformat [-Werror=format-security]
# http://errors.yoctoproject.org/Errors/Details/150333/
SECURITY_STRINGFORMAT = ""

# To use system ffmpeg you need to enable also libwebp, opus, libvpx
# Only depenedencies available in oe-core are enabled by default
PACKAGECONFIG ??= "libevent libpng \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"

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
PACKAGECONFIG[libxml2] = "-feature-webengine-system-libxml2,-no-feature-webengine-system-libxml2,libxml2"
PACKAGECONFIG[minizip] = "-feature-webengine-system-minizip,-no-feature-webengine-system-minizip,minizip"
PACKAGECONFIG[proprietary-codecs] = "-feature-webengine-proprietary-codecs,-no-feature-webengine-proprietary-codecs"
PACKAGECONFIG[pepper-plugins] = "-feature-webengine-pepper-plugins,-no-feature-webengine-pepper-plugins"
PACKAGECONFIG[printing-and-pdf] = "-feature-webengine-printing-and-pdf,-no-feature-webengine-printing-and-pdf"
PACKAGECONFIG[spellchecker] = "-feature-webengine-spellchecker,-no-feature-webengine-spellchecker"
PACKAGECONFIG[pulseaudio] = "-feature-webengine-pulseaudio,-no-feature-webengine-pulseaudio,pulseaudio"

EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

COMPATIBLE_MACHINE = "(-)"
COMPATIBLE_MACHINE:x86 = "(.*)"
COMPATIBLE_MACHINE:x86-64 = "(.*)"
COMPATIBLE_MACHINE:armv6 = "(.*)"
COMPATIBLE_MACHINE:armv7a = "(.*)"
COMPATIBLE_MACHINE:armv7ve = "(.*)"
COMPATIBLE_MACHINE:aarch64 = "(.*)"
COMPATIBLE_MACHINE:riscv64 = "(.*)"

inherit qmake5
inherit gettext
inherit perlnative
inherit features_check


# Static builds of QtWebEngine aren't supported.
CONFLICT_DISTRO_FEATURES = "qt5-static"

# we don't want gettext.bbclass to append --enable-nls
def gettext_oeconf(d):
    return ""

require qt5.inc
require qt5-git.inc

export GN_PKG_CONFIG_HOST = "${STAGING_BINDIR_NATIVE}/pkg-config-native"
export GN_HOST_TOOLCHAIN_EXTRA_CPPFLAGS = "-I${STAGING_DIR_NATIVE}/usr/include"
export GN_HOST_TOOLCHAIN_EXTRA_LDFLAGS = "-L${STAGING_LIBDIR_NATIVE}"
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
        ${EXTRA_QMAKEVARS_CONFIGURE} -verbose
}

do_configure:prepend:libc-musl() {
        for f in `find ${S}/src/3rdparty/chromium/third_party/ffmpeg/chromium/config/Chromium/linux/ -name config.h -o -name config.asm`; do
                sed -i -e "s:define HAVE_SYSCTL 1:define HAVE_SYSCTL 0:g" $f
        done
}

do_compile[progress] = "outof:^\[(\d+)/(\d+)\]\s+"

# for /usr/share/qt5/qtwebengine_resources.pak
FILES:${PN} += "${OE_QMAKE_PATH_QT_TRANSLATIONS} ${OE_QMAKE_PATH_QT_DATA}"

# Chromium uses libpci to determine which optimizations/workarounds to apply
RDEPENDS:${PN}:append:x86 = " libpci"

RDEPENDS:${PN}-examples += " \
    ${PN}-qmlplugins \
    qtquickcontrols-qmlplugins \
    qtdeclarative-qmlplugins \
"

QT_MODULE_BRANCH_CHROMIUM = "87-based"

QT_MODULE_BRANCH = "5.15"
PV = "5.15.13+git${SRCPV}"

# Uses the same repository and couple patches as qtwebengine, but qtwebengine
# still depends on python2
QT_MODULE = "qtwebengine"
FILESEXTRAPATHS =. "${FILE_DIRNAME}/qtwebengine:"

# Patches from https://github.com/meta-qt5/qtwebengine/commits/b5.15-glibc
# 5.15-glibc.meta-qt5.17
SRC_URI += " \
    ${QT_GIT}/qtwebengine-chromium.git;name=chromium;branch=${QT_MODULE_BRANCH_CHROMIUM};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty \
    file://0001-Force-host-toolchain-configuration.patch \
    file://0002-qmake.conf-lower-MODULE_VERSION-to-5.15.X.patch \
"
# Patches from https://github.com/meta-qt5/qtwebengine/commits/b5.15
# 5.15.meta-qt5.17
SRC_URI:append:libc-musl = "\
    file://0003-musl-don-t-use-pvalloc-as-it-s-not-available-on-musl.patch \
    file://0004-musl-link-against-libexecinfo.patch \
    file://0005-mkspecs-Allow-builds-with-libc-glibc.patch \
"

# Patches from https://github.com/meta-qt5/qtwebengine-chromium/commits/87-based-glibc
# 87-based-glibc.meta-qt5.11
SRC_URI += " \
    file://chromium/0001-chromium-workaround-for-too-long-.rps-file-name.patch;patchdir=src/3rdparty \
    file://chromium/0002-chromium-fix-build-with-clang.patch;patchdir=src/3rdparty \
    file://chromium/0003-chromium-Exclude-CRC32-for-32bit-arm.patch;patchdir=src/3rdparty \
    file://chromium/0004-chromium-Do-not-try-to-set-the-guessed-values-for.patch;patchdir=src/3rdparty \
    file://chromium/0005-chromium-fix-build-after-y2038-changes-in-glibc.patch;patchdir=src/3rdparty \
    file://chromium/0006-chromium-Fix-build-on-32bit-arches-with-64bit-time_t.patch;patchdir=src/3rdparty \
    file://chromium/0007-chromium-Include-cstddef-for-size_t-definition.patch;patchdir=src/3rdparty \
    file://chromium/0008-chromium-Move-CharAllocator-definition-to-a-header-f.patch;patchdir=src/3rdparty \
    file://chromium/0009-chromium-Link-v8-with-libatomic-on-x86.patch;patchdir=src/3rdparty \
    file://chromium/0010-chromium-icu-use-system-library-only-targets.patch;patchdir=src/3rdparty \
    file://chromium/0011-chromium-skia-Fix-build-with-gcc-12.patch;patchdir=src/3rdparty \
    file://chromium/0012-Remove-unsetting-_FILE_OFFSET_BITS.patch;patchdir=src/3rdparty \
    file://chromium/0013-Fix-build-with-gcc-13.patch;patchdir=src/3rdparty \
"

# Patches from https://github.com/meta-qt5/qtwebengine-chromium/commits/87-based
# 87-based.meta-qt5.11
SRC_URI:append:libc-musl = "\
    file://chromium/0013-chromium-musl-sandbox-Define-TEMP_FAILURE_RETRY-if-n.patch;patchdir=src/3rdparty \
    file://chromium/0014-chromium-musl-Avoid-mallinfo-APIs-on-non-glibc-linux.patch;patchdir=src/3rdparty \
    file://chromium/0015-chromium-musl-include-fcntl.h-for-loff_t.patch;patchdir=src/3rdparty \
    file://chromium/0016-chromium-musl-use-off64_t-instead-of-the-internal-__.patch;patchdir=src/3rdparty \
    file://chromium/0017-chromium-musl-linux-glibc-make-the-distinction.patch;patchdir=src/3rdparty \
    file://chromium/0018-chromium-musl-Define-res_ninit-and-res_nclose-for-no.patch;patchdir=src/3rdparty \
    file://chromium/0019-chromium-musl-Do-not-define-__sbrk-on-musl.patch;patchdir=src/3rdparty \
    file://chromium/0020-chromium-musl-Adjust-default-pthread-stack-size.patch;patchdir=src/3rdparty \
    file://chromium/0021-chromium-musl-elf_reader.cc-include-sys-reg.h-to-get.patch;patchdir=src/3rdparty \
    file://chromium/0022-chromium-musl-pread-pwrite.patch;patchdir=src/3rdparty \
    file://chromium/0023-chromium-musl-initialize-msghdr-in-a-compatible-mann.patch;patchdir=src/3rdparty \
"

SRCREV_qtwebengine = "d15a42baae7141952e91665bed22a7c7cfb54b95"
SRCREV_chromium = "fb66d7ca9641724670c96e999ad5b0fd6eb78d46"
SRCREV = "${SRCREV_qtwebengine}"

SRCREV_FORMAT = "qtwebengine_chromium"

# WARNING: qtwebengine-5.5.99+5.6.0-rc+gitAUTOINC+3f02c25de4_779a2388fc-r0 do_package_qa: QA Issue: ELF binary '/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtwebengine/5.5.99+5.6.0-rc+gitAUTOINC+3f02c25de4_779a2388fc-r0/packages-split/qtwebengine/usr/lib/libQt5WebEngineCore.so.5.6.0' has relocations in .text [textrel]
INSANE_SKIP:${PN} += "textrel"

###
### This recipe's part above is mostly a copy of qtwebengine_git.bb.
### The PACKAGECONFIG flags were kept to avoid enablement of any unwished-for features
### There were only some (build) dependencies removed.
###

# First patch skips "python2" dependency checks for the pdf module
# Second patch repairs a failing build of the `gn` buildtool due to missing (host) libstdc++
# Patches from https://github.com/meta-qt5/qtwebengine/commits/b5.15-qtpdf
# 5.15-qtpdf.meta-qt5.5
SRC_URI += " \
    file://0001-configure.json-remove-python2-dependency.patch \
    file://0002-gn.pro-do-not-try-to-statically-link-stdc.patch \
"
SRC_URI:append:toolchain-clang:runtime-llvm = " file://0003-Fix-build-with-clang.patch"

# These flags below go more into detail than qtwebengine's documentation
PACKAGECONFIG[no-core] = "-no-build-qtwebengine-core,,"
PACKAGECONFIG[qtpdf] = "-feature-build-qtpdf,-no-feature-build-qtpdf,python3-native"
PACKAGECONFIG[webengine-pdf] = "-feature-webengine-python2,-no-feature-webengine-python2,python3-native"

# The flags below enable just the PDF widget
PACKAGECONFIG = "no-core qtpdf"

# Work around gn's dependency on python (2).
# To build qtpdf it works just as well with python3.
EXTRA_QMAKEVARS_PRE += "QMAKE_PYTHON2=python3"
