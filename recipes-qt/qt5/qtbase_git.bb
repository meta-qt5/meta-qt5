require qt5.inc
require qt5-git.inc
require qt5-ptest.inc

LICENSE = "GFDL-1.3 & BSD-3-Clause & ( GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0-or-later | LGPL-3.0-only | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.QT-LICENSE-AGREEMENT;md5=38de3b110ade3b6ee2f0b6a95ab16f1a \
"

# common for qtbase-native, qtbase-nativesdk and qtbase
# Patches from https://github.com/meta-qt5/qtbase/commits/b5.15-shared
# 5.15.meta-qt5-shared.4
SRC_URI += "\
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-cmake-Use-OE_QMAKE_PATH_EXTERNAL_HOST_BINS.patch \
    file://0003-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0004-configure-bump-path-length-from-256-to-512-character.patch \
    file://0005-Disable-all-unknown-features-instead-of-erroring-out.patch \
    file://0006-Pretend-Qt5-wasn-t-found-if-OE_QMAKE_PATH_EXTERNAL_H.patch \
    file://0007-Delete-qlonglong-and-qulonglong.patch \
    file://0009-Add-OE-specific-specs-for-clang-compiler.patch \
    file://0010-linux-clang-Invert-conditional-for-defining-QT_SOCKL.patch \
    file://0011-tst_qlocale-Enable-QT_USE_FENV-only-on-glibc.patch \
    file://0012-Disable-ltcg-for-host_build.patch \
    file://0013-Qt5GuiConfigExtras.cmake.in-cope-with-variable-path-.patch \
    file://0014-corelib-Include-sys-types.h-for-uint32_t.patch \
    file://0015-Define-QMAKE_CXX.COMPILER_MACROS-for-clang-on-linux.patch \
    file://0016-tst_qpainter-FE_-macros-are-not-defined-for-every-pl.patch \
    file://0017-Define-__NR_futex-if-it-does-not-exist.patch \
    file://0018-Revert-Fix-workaround-in-pthread-destructor.patch \
    file://0019-tst_QPluginLoader-Simplify-creating-a-fake-pointer-i.patch \
    file://0021-rcc-Just-dcument-file-name-without-full-path-to-redu.patch \
    file://0022-testlib-don-t-track-the-build-or-source-directories.patch \
    file://0023-Remove-unsetting-_FILE_OFFSET_BITS.patch \
    file://CVE-2023-32762.patch \
    file://CVE-2023-32763-qtbase-5.15.diff \
    file://CVE-2023-33285-qtbase-5.15.diff \
    file://CVE-2023-34410-qtbase-5.15.diff \
    file://CVE-2023-37369-qtbase-5.15.diff \
    file://CVE-2023-38197-qtbase-5.15.diff \
    file://CVE-2023-43114-5.15.patch \
    file://0027-xkb-fix-build-with-libxkbcommon-1.6.0-and-later.patch \
    file://0001-CVE-2023-51714-qtbase-5.15.diff \
    file://0002-CVE-2023-51714-qtbase-5.15.diff \
    file://0028-Remove-host-paths-from-qmake.patch \
    file://0029-Remove-ptests-with-SRCDIR.patch \
    file://CVE-2024-25580.patch \
"

# usually pulled by one of the optional dependencies in PACKAGECONFIG, but with very limited PACKAGECONFIG fails with:
# src/corelib/io/qresource.cpp:68:12: fatal error: zstd.h: No such file or directory
DEPENDS = "zstd"

# Disable LTO for now, QT5 patches are being worked upstream, perhaps revisit with
# next major upgrade of QT
LTO = ""

# for syncqt
RDEPENDS:${PN}-tools += "perl"

inherit pkgconfig

# separate some parts of PACKAGECONFIG which are often changed
PACKAGECONFIG_GL ?= "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gl', 'no-opengl', d)}"
PACKAGECONFIG_FB ?= "${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)}"
PACKAGECONFIG_X11 ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcb glib xkbcommon', '', d)}"
PACKAGECONFIG_KDE ?= "${@bb.utils.contains('DISTRO_FEATURES', 'kde', 'sm cups fontconfig kms gbm libinput sql-sqlite openssl', '', d)}"
PACKAGECONFIG_FONTS ?= ""
PACKAGECONFIG_SYSTEM ?= "jpeg libpng zlib"
PACKAGECONFIG_DISTRO ?= ""
# Either release or debug, can be overridden in bbappends
PACKAGECONFIG_RELEASE ?= "release"
# This is in qt5.inc, because qtwebkit-examples are using it to enable ca-certificates dependency
# PACKAGECONFIG_OPENSSL ?= "openssl"
PACKAGECONFIG_DEFAULT ?= "accessibility dbus udev evdev widgets tools libs freetype pcre \
    ${@bb.utils.contains('SELECTED_OPTIMIZATION', '-Os', 'optimize-size ltcg', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'ptest', 'tests', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'qt5-static', 'static', '', d)} \
    ${@bb.utils.filter('DISTRO_FEATURES', 'vulkan', d)} \
"

PACKAGECONFIG ?= " \
    ${PACKAGECONFIG_RELEASE} \
    ${PACKAGECONFIG_DEFAULT} \
    ${PACKAGECONFIG_OPENSSL} \
    ${PACKAGECONFIG_GL} \
    ${PACKAGECONFIG_FB} \
    ${PACKAGECONFIG_X11} \
    ${PACKAGECONFIG_KDE} \
    ${PACKAGECONFIG_FONTS} \
    ${PACKAGECONFIG_SYSTEM} \
    ${PACKAGECONFIG_DISTRO} \
"
# Choose whether to link to OpenSSL library at linking time or run time
# Leave the variable empty to let the buildsystem decide, or specify -linked or -runtime
OPENSSL_LINKING_MODE ??= ""

PACKAGECONFIG[static] = "-static,-shared"
PACKAGECONFIG[release] = "-release,-debug"
PACKAGECONFIG[debug] = ""
PACKAGECONFIG[developer] = "-developer-build"
PACKAGECONFIG[optimize-size] = "-optimize-size"
PACKAGECONFIG[ltcg] = "-ltcg"
PACKAGECONFIG[sm] = "-sm,-no-sm"
PACKAGECONFIG[tests] = "-make tests,-nomake tests"
PACKAGECONFIG[examples] = "-make examples -compile-examples,-nomake examples"
PACKAGECONFIG[tools] = "-make tools,-nomake tools"
# only for completeness, configure will add libs even if you try to explicitly remove it
PACKAGECONFIG[libs] = "-make libs"
# accessibility is required to compile qtquickcontrols
PACKAGECONFIG[accessibility] = "-accessibility,-no-accessibility"
PACKAGECONFIG[glib] = "-glib,-no-glib,glib-2.0"
# use either system freetype or bundled freetype, if you disable freetype completely
# fontdatabases/basic/qbasicfontdatabase.cpp will fail to build and system freetype
# works only together with fontconfig
PACKAGECONFIG[freetype] = "-system-freetype,-qt-freetype,freetype"
PACKAGECONFIG[harfbuzz] = "-system-harfbuzz,-qt-harfbuzz,harfbuzz"
PACKAGECONFIG[jpeg] = "-system-libjpeg,-no-libjpeg,jpeg"
PACKAGECONFIG[libpng] = "-system-libpng,-no-libpng,libpng"
PACKAGECONFIG[gif] = "-gif,-no-gif"
PACKAGECONFIG[ico] = "-ico,-no-ico"
PACKAGECONFIG[zlib] = "-system-zlib,-qt-zlib,zlib"
PACKAGECONFIG[pcre] = "-system-pcre,-qt-pcre,pcre2"
PACKAGECONFIG[eglfs] = "-eglfs,-no-eglfs,drm"
PACKAGECONFIG[gl] = "-opengl desktop,,virtual/libgl"
PACKAGECONFIG[gles2] = "-opengl es2,,virtual/libgles2 virtual/egl"
PACKAGECONFIG[no-opengl] = "-no-opengl"
PACKAGECONFIG[tslib] = "-tslib,-no-tslib,tslib"
PACKAGECONFIG[cups] = "-cups,-no-cups,cups"
PACKAGECONFIG[dbus] = "-dbus,-no-dbus,dbus"
PACKAGECONFIG[xcb] = "-xcb -xcb-xlib -no-bundled-xcb-xinput -DUSE_X11=ON,-no-xcb,libxcb xcb-util-wm xcb-util-image xcb-util-keysyms xcb-util-renderutil libxext"
PACKAGECONFIG[sql-ibase] = "-sql-ibase,-no-sql-ibase"
PACKAGECONFIG[sql-mysql] = "-sql-mysql -mysql_config ${STAGING_BINDIR_CROSS}/mysql_config,-no-sql-mysql,mysql5"
PACKAGECONFIG[sql-psql] = "-sql-psql,-no-sql-psql,postgresql"
PACKAGECONFIG[sql-odbc] = "-sql-odbc,-no-sql-odbc,unixodbc"
PACKAGECONFIG[sql-oci] = "-sql-oci,-no-sql-oci"
PACKAGECONFIG[sql-tds] = "-sql-tds,-no-sql-tds"
PACKAGECONFIG[sql-db2] = "-sql-db2,-no-sql-db2"
PACKAGECONFIG[sql-sqlite2] = "-sql-sqlite2,-no-sql-sqlite2,sqlite"
PACKAGECONFIG[sql-sqlite] = "-sql-sqlite -system-sqlite,-no-sql-sqlite,sqlite3"
PACKAGECONFIG[iconv] = "-iconv,-no-iconv,virtual/libiconv"
PACKAGECONFIG[xkbcommon] = "-xkbcommon,-no-xkbcommon,libxkbcommon,xkeyboard-config"
PACKAGECONFIG[evdev] = "-evdev,-no-evdev"
PACKAGECONFIG[mtdev] = "-mtdev,-no-mtdev,mtdev"
PACKAGECONFIG[lttng] = "-trace lttng,-trace no,lttng-ust"
# depends on glib
PACKAGECONFIG[fontconfig] = "-fontconfig,-no-fontconfig,fontconfig"
PACKAGECONFIG[gtk] = "-gtk,-no-gtk,gtk+3"
PACKAGECONFIG[directfb] = "-directfb,-no-directfb,directfb"
PACKAGECONFIG[linuxfb] = "-linuxfb,-no-linuxfb"
PACKAGECONFIG[kms] = "-kms,-no-kms,drm virtual/egl"
PACKAGECONFIG[gbm] = "-gbm,-no-gbm,virtual/libgbm"
PACKAGECONFIG[icu] = "-icu,-no-icu,icu"
PACKAGECONFIG[udev] = "-libudev,-no-libudev,udev"
PACKAGECONFIG[openssl] = "-openssl${OPENSSL_LINKING_MODE},-no-openssl,openssl,libssl"
PACKAGECONFIG[widgets] = "-widgets,-no-widgets"
PACKAGECONFIG[libproxy] = "-libproxy,-no-libproxy,libproxy"
PACKAGECONFIG[libinput] = "-libinput,-no-libinput,libinput"
PACKAGECONFIG[journald] = "-journald,-no-journald,systemd"
# needs kernel 3.17+
PACKAGECONFIG[getentropy] = "-feature-getentropy,-no-feature-getentropy,"
PACKAGECONFIG[vulkan] = "-vulkan,-no-vulkan,vulkan-headers"

QT_CONFIG_FLAGS_GOLD = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', '-use-gold-linker', '-no-use-gold-linker', d)}"
# workaround for gold bug:
# https://bugs.debian.org/cgi-bin/bugreport.cgi?bug=842304
# https://sourceware.org/bugzilla/show_bug.cgi?id=21712
# it's triggered only in combination of gold and security_flags.inc,
# because security_flags.inc now enable pie by default.
# Adding -no-pie or changing -fuse-ld=gold to -fuse-ld=bfd
# works around this issue, will use -fuse-ld=bfd as it's considered
# binutils bug.
# OE @ ~/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/build/tests/auto/corelib/kernel/qmetatype $ i586-oe-linux-g++  -m32 -march=i586 --sysroot=/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/recipe-sysroot -Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed --sysroot=/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/recipe-sysroot -Wl,-O1 -fuse-ld=gold -Wl,--enable-new-dtags -o tst_qmetatype .obj/tst_qmetatype.o   -L/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/build/lib -lQt5Test -lQt5Core -lpthread
# .obj/tst_qmetatype.o(.qtversion+0x0): error: unexpected reloc 3 against global symbol qt_version_tag without base register in object file when generating a position-independent output file
# collect2: error: ld returned 1 exit status
#
# with -no-pie:
# OE @ ~/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/build/tests/auto/corelib/kernel/qmetatype $ i586-oe-linux-g++ -no-pie -m32 -march=i586 --sysroot=/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/recipe-sysroot -Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed --sysroot=/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/recipe-sysroot -Wl,-O1 -fuse-ld=gold -Wl,--enable-new-dtags -o tst_qmetatype .obj/tst_qmetatype.o   -L/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/build/lib -lQt5Test -lQt5Core -lpthread
#
# with -fuse-ld=gold replaced with -fuse-ld=bfd:
# OE @ ~/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/build/tests/auto/corelib/kernel/qmetatype $ i586-oe-linux-g++  -m32 -march=i586 --sysroot=/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/recipe-sysroot -Wl,-O1 -Wl,--hash-style=gnu -Wl,--as-needed --sysroot=/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/recipe-sysroot -Wl,-O1 -fuse-ld=bfd -Wl,--enable-new-dtags -o tst_qmetatype .obj/tst_qmetatype.o   -L/OE/build/oe-core/tmp-glibc/work/i586-oe-linux/qtbase/5.9.0+gitAUTOINC+f6b36eaafe-r0/build/lib -lQt5Test -lQt5Core -lpthread
#
# http://errors.yoctoproject.org/Errors/Details/150329/
# QT_CONFIG_FLAGS_GOLD:x86 = "-no-use-gold-linker"
# LDFLAGS:append:x86 = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"

# since the upgrade to 5.12.2 this got worse, with gold enabled configure will no longer pass the test for xlib
# because with full paths to libraries since qtbase commit 521a85395 it fails to link with
# /OE/build/oe-core/tmp-glibc/work/core2-64-oe-linux/qtbase/5.12.3+gitAUTOINC+b527725766-r0/recipe-sysroot/usr/lib/libm.so
# as reported in:
# https://github.com/meta-qt5/meta-qt5/pull/181#issuecomment-484425112
# resulting in do_configure failure:
# http://errors.yoctoproject.org/Errors/Details/237856/
QT_CONFIG_FLAGS_GOLD = "-no-use-gold-linker"
LDFLAGS:append = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"

LDFLAGS:append:riscv64 = " -pthread"

QT_CONFIG_FLAGS += " \
    ${QT_CONFIG_FLAGS_GOLD} \
    -silent \
    -no-pch \
    -no-rpath \
    -pkg-config \
    ${PACKAGECONFIG_CONFARGS} \
"

export CC_host:toolchain-clang = "clang"
export CXX_host:toolchain-clang = "clang++"
export LD_host:toolchain-clang = "clang++"
export CC_host ?= "gcc"
export CXX_host ?= "g++"
export LD_host ?= "g++"

# for qtbase configuration we need default settings
# since we cannot set empty set filename to a not existent file
deltask generate_qt_config_file

XPLATFORM:toolchain-clang = "linux-oe-clang"
XPLATFORM ?= "linux-oe-g++"

# Causes qdrawhelper.s: Error: unaligned opcodes detected in executable segment
# when building qtbase/5.6.3+gitAUTOINC+e6f8b072d2-r0/git/src/gui/painting/qdrawhelper.cpp
ARM_INSTRUCTION_SET:armv4 = "arm"
ARM_INSTRUCTION_SET:armv5 = "arm"

do_configure() {
    # Regenerate header files when they are included in source tarball
    # Otherwise cmake files don't set PRIVATE_HEADERS correctly
    rm -rf ${S}/include
    mkdir -p ${S}/.git || true

    # Avoid qmake error "Cannot read [...]/usr/lib/qt5/mkspecs/oe-device-extra.pri: No such file or directory" during configuration
    touch ${S}/mkspecs/oe-device-extra.pri

    ${S}/configure -v \
        -${QT_EDITION} -confirm-license \
        -sysroot ${STAGING_DIR_TARGET} \
        -prefix ${OE_QMAKE_PATH_PREFIX} \
        -bindir ${OE_QMAKE_PATH_BINS} \
        -libdir ${OE_QMAKE_PATH_LIBS} \
        -datadir ${OE_QMAKE_PATH_DATA} \
        -sysconfdir ${OE_QMAKE_PATH_SETTINGS} \
        -docdir ${OE_QMAKE_PATH_DOCS} \
        -headerdir ${OE_QMAKE_PATH_HEADERS} \
        -archdatadir ${OE_QMAKE_PATH_ARCHDATA} \
        -libexecdir ${OE_QMAKE_PATH_LIBEXECS} \
        -plugindir ${OE_QMAKE_PATH_PLUGINS} \
        -qmldir ${OE_QMAKE_PATH_QML} \
        -translationdir ${OE_QMAKE_PATH_TRANSLATIONS} \
        -testsdir ${OE_QMAKE_PATH_TESTS} \
        -examplesdir ${OE_QMAKE_PATH_EXAMPLES} \
        -hostprefix ${OE_QMAKE_PATH_HOST_PREFIX} \
        -hostlibdir ${OE_QMAKE_PATH_HOST_LIBS} \
        -hostbindir ${OE_QMAKE_PATH_HOST_BINS} \
        -external-hostbindir ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS} \
        -hostdatadir ${OE_QMAKE_PATH_HOST_DATA} \
        -platform ${OE_QMAKE_PLATFORM_NATIVE} \
        -xplatform ${XPLATFORM} \
        ${QT_CONFIG_FLAGS}
}

do_install:append() {
    # Avoid qmake error "Cannot read [...]/usr/lib/qt5/mkspecs/oe-device-extra.pri: No such file or directory"
    touch ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/oe-device-extra.pri

    # Update the mkspecs to include the default OE toolchain config for the target
    conf=${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/${XPLATFORM}/qmake.conf

    # qmake already knows the sysroot, see above $$[QT_SYSROOT], so remove the hardcoded sysroot
    OE_QMAKE_CC_NO_SYSROOT=$(echo ${OE_QMAKE_CC} | sed -e 's!--sysroot=[^ ]*!!g')
    OE_QMAKE_CXX_NO_SYSROOT=$(echo ${OE_QMAKE_CXX} | sed -e 's!--sysroot=[^ ]*!!g')
    OE_QMAKE_LINK_NO_SYSROOT=$(echo ${OE_QMAKE_LINK} | sed -e 's!--sysroot=[^ ]*!!g')

    echo "" >> $conf
    echo "# default compiler options which can be overwritten from the environment" >> $conf
    echo "count(QMAKE_AR, 1): QMAKE_AR = ${OE_QMAKE_AR} cqs" >> $conf
    echo "isEmpty(QMAKE_CC): QMAKE_CC = $OE_QMAKE_CC_NO_SYSROOT" >> $conf
    echo "isEmpty(QMAKE_CXX): QMAKE_CXX = $OE_QMAKE_CXX_NO_SYSROOT" >> $conf
    # OE_QMAKE_CFLAGS and OE_QMAKE_CXXFLAGS contain path of the build host, which is not useful for the target.
    echo "isEmpty(QMAKE_CFLAGS): QMAKE_CFLAGS = ${OE_QMAKE_CFLAGS}" | sed -e 's/-fdebug-prefix-map=[^ ]*//g' | sed -e 's/-fmacro-prefix-map=[^ ]*//g' >> $conf
    echo "isEmpty(QMAKE_CXXFLAGS): QMAKE_CXXFLAGS = ${OE_QMAKE_CXXFLAGS}" | sed -e 's/-fdebug-prefix-map=[^ ]*//g' | sed -e 's/-fmacro-prefix-map=[^ ]*//g' >> $conf
    echo "isEmpty(QMAKE_LINK): QMAKE_LINK = $OE_QMAKE_LINK_NO_SYSROOT" >> $conf
    echo "isEmpty(QMAKE_LINK_SHLIB): QMAKE_LINK_SHLIB = $OE_QMAKE_LINK_NO_SYSROOT" >> $conf
    echo "isEmpty(QMAKE_LINK_C): QMAKE_LINK_C = $OE_QMAKE_LINK_NO_SYSROOT" >> $conf
    echo "isEmpty(QMAKE_LINK_C_SHLIB): QMAKE_LINK_C_SHLIB = $OE_QMAKE_LINK_NO_SYSROOT" >> $conf
    # OE_QMAKE_LFLAGS contains path of the build host, which is not useful for the target.
    echo "isEmpty(QMAKE_LFLAGS): QMAKE_LFLAGS = ${OE_QMAKE_LDFLAGS}" | sed -e 's/-fdebug-prefix-map=[^ ]*//g' | sed -e 's/-fmacro-prefix-map=[^ ]*//g' >> $conf
    echo "isEmpty(QMAKE_OBJCOPY): QMAKE_OBJCOPY = ${TARGET_PREFIX}objcopy" >> $conf
    echo "isEmpty(QMAKE_STRIP): QMAKE_STRIP = ${TARGET_PREFIX}strip" >> $conf
    echo "isEmpty(CC_host): CC_host = ${CC_host}" >> $conf
    echo "isEmpty(CXX_host): CXX_host = ${CXX_host}" >> $conf
    echo "isEmpty(LD_host): LD_host = ${LD_host}" >> $conf

    generate_target_qt_config_file ${D}${OE_QMAKE_PATH_BINS}/qt.conf

    # Fix up absolute paths in scripts and use python3 instead of python
    sed -i -e '1s,#!/usr/bin/python$,#! ${USRBINPATH}/env python3,' \
        ${D}${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/features/uikit/devices.py
}

# mkspecs have mac specific scripts that depend on perl and bash
INSANE_SKIP:${PN}-mkspecs += "file-rdeps"

RRECOMMENDS:${PN}-plugins += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libx11-locale', '', d)}"

TARGET_MKSPEC ?= "linux-g++"

# use clean mkspecs on target
pkg_postinst:${PN}-tools () {
sed -i \
    -e 's:HostSpec =.*:HostSpec = ${TARGET_MKSPEC}:g' \
    -e 's:TargetSpec =.*:TargetSpec = ${TARGET_MKSPEC}:g' \
    $D${OE_QMAKE_PATH_BINS}/qt.conf
}

pkg_postinst:${PN}-mkspecs () {
sed -i 's: cross_compile : :g' $D${OE_QMAKE_PATH_ARCHDATA}/mkspecs/qconfig.pri
sed -i \
    -e 's: cross_compile : :g' \
    -e 's:HOST_QT_TOOLS =.*::g' \
    $D${OE_QMAKE_PATH_ARCHDATA}/mkspecs/qmodule.pri
}

SRCREV = "4e158f6bfa7d0747d8da70b3b15a44b52e35bb8a"
