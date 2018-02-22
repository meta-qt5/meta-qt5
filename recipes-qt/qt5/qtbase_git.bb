require qt5.inc
require qt5-git.inc
require qt5-ptest.inc

LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0+ | LGPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.LGPLv21;md5=fb91571854638f10b2e5f36562661a5a \
    file://LICENSE.LGPLv3;md5=a909b94c1c9674b2aa15ff03a86f518a \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

# common for qtbase-native, qtbase-nativesdk and qtbase
# Patches from https://github.com/meta-qt5/qtbase/commits/b5.10-shared
# 5.10.meta-qt5-shared.2
SRC_URI += "\
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-cmake-Use-OE_QMAKE_PATH_EXTERNAL_HOST_BINS.patch \
    file://0003-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0004-configure-bump-path-length-from-256-to-512-character.patch \
    file://0005-Disable-all-unknown-features-instead-of-erroring-out.patch \
    file://0006-Pretend-Qt5-wasn-t-found-if-OE_QMAKE_PATH_EXTERNAL_H.patch \
    file://0007-Delete-qlonglong-and-qulonglong.patch \
    file://0008-Replace-pthread_yield-with-sched_yield.patch \
    file://0009-Add-OE-specific-specs-for-clang-compiler.patch \
    file://0010-linux-clang-Invert-conditional-for-defining-QT_SOCKL.patch \
    file://0011-tst_qlocale-Enable-QT_USE_FENV-only-on-glibc.patch \
    file://0012-mkspecs-common-gcc-base.conf-Use-I-instead-of-isyste.patch \
"

# LGPL-3.0 is used only in src/plugins/platforms/android/extract.cpp

# for syncqt
RDEPENDS_${PN}-tools += "perl"

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
QT_CONFIG_FLAGS_GOLD_x86 = "-no-use-gold-linker"
LDFLAGS_append_x86 = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"

# separate some parts of PACKAGECONFIG which are often changed
PACKAGECONFIG_GL ?= "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gl', '', d)}"
PACKAGECONFIG_FB ?= "${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)}"
PACKAGECONFIG_X11 ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcb xinput2 glib xkb xkbcommon-evdev', '', d)}"
PACKAGECONFIG_FONTS ?= ""
PACKAGECONFIG_SYSTEM ?= "jpeg libpng zlib"
PACKAGECONFIG_DISTRO ?= ""
# Either release or debug, can be overridden in bbappends
PACKAGECONFIG_RELEASE ?= "release"
# This is in qt5.inc, because qtwebkit-examples are using it to enable ca-certificates dependency
# PACKAGECONFIG_OPENSSL ?= "openssl"
PACKAGECONFIG_DEFAULT ?= "dbus udev evdev widgets tools libs freetype tests \
    ${@bb.utils.contains('SELECTED_OPTIMIZATION', '-Os', 'optimize-size', '', d)} \
"

PACKAGECONFIG ?= " \
    ${PACKAGECONFIG_RELEASE} \
    ${PACKAGECONFIG_DEFAULT} \
    ${PACKAGECONFIG_OPENSSL} \
    ${PACKAGECONFIG_GL} \
    ${PACKAGECONFIG_FB} \
    ${PACKAGECONFIG_X11} \
    ${PACKAGECONFIG_FONTS} \
    ${PACKAGECONFIG_SYSTEM} \
    ${PACKAGECONFIG_DISTRO} \
"

PACKAGECONFIG[release] = "-release,-debug"
PACKAGECONFIG[debug] = ""
PACKAGECONFIG[developer] = "-developer-build"
PACKAGECONFIG[qml-debug] = "-qml-debug,-no-qml-debug"
PACKAGECONFIG[optimize-size] = "-optimize-size"
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
PACKAGECONFIG[harfbuzz] = "-system-harfbuzz,-no-harfbuzz,harfbuzz"
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
PACKAGECONFIG[xcb] = "-xcb -xcb-xlib -system-xcb,-no-xcb,libxcb xcb-util-wm xcb-util-image xcb-util-keysyms xcb-util-renderutil"
PACKAGECONFIG[sql-ibase] = "-sql-ibase,-no-sql-ibase"
PACKAGECONFIG[sql-mysql] = "-sql-mysql,-no-sql-mysql,mysql5"
PACKAGECONFIG[sql-psql] = "-sql-psql,-no-sql-psql,postgresql"
PACKAGECONFIG[sql-odbc] = "-sql-odbc,-no-sql-odbc"
PACKAGECONFIG[sql-oci] = "-sql-oci,-no-sql-oci"
PACKAGECONFIG[sql-tds] = "-sql-tds,-no-sql-tds"
PACKAGECONFIG[sql-db2] = "-sql-db2,-no-sql-db2"
PACKAGECONFIG[sql-sqlite2] = "-sql-sqlite2,-no-sql-sqlite2,sqlite"
PACKAGECONFIG[sql-sqlite] = "-sql-sqlite -system-sqlite,-no-sql-sqlite,sqlite3"
PACKAGECONFIG[xinput2] = "-xinput2,-no-xinput2,libxi"
PACKAGECONFIG[iconv] = "-iconv,-no-iconv,virtual/libiconv"
PACKAGECONFIG[xkb] = "-xkb,-no-xkb -no-xkbcommon,libxkbcommon"
PACKAGECONFIG[xkbcommon-evdev] = "-xkbcommon-evdev,-no-xkbcommon-evdev,libxkbcommon,xkeyboard-config"
PACKAGECONFIG[evdev] = "-evdev,-no-evdev"
PACKAGECONFIG[mtdev] = "-mtdev,-no-mtdev,mtdev"
# depends on glib
PACKAGECONFIG[fontconfig] = "-fontconfig,-no-fontconfig,fontconfig"
PACKAGECONFIG[gtk] = "-gtk,-no-gtk,gtk+3"
PACKAGECONFIG[directfb] = "-directfb,-no-directfb,directfb"
PACKAGECONFIG[linuxfb] = "-linuxfb,-no-linuxfb"
PACKAGECONFIG[kms] = "-kms,-no-kms,drm virtual/egl"
PACKAGECONFIG[gbm] = "-gbm,-no-gbm,virtual/mesa"
PACKAGECONFIG[icu] = "-icu,-no-icu,icu"
PACKAGECONFIG[udev] = "-libudev,-no-libudev,udev"
PACKAGECONFIG[openssl] = "-openssl,-no-openssl,openssl,libssl"
PACKAGECONFIG[widgets] = "-widgets,-no-widgets"
PACKAGECONFIG[libproxy] = "-libproxy,-no-libproxy,libproxy"
PACKAGECONFIG[libinput] = "-libinput,-no-libinput,libinput"
PACKAGECONFIG[journald] = "-journald,-no-journald,systemd"

QT_CONFIG_FLAGS_GOLD = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', '-use-gold-linker', '-no-use-gold-linker', d)}"
QT_CONFIG_FLAGS += " \
    ${QT_CONFIG_FLAGS_GOLD} \
    -shared \
    -silent \
    -no-pch \
    -no-rpath \
    -pkg-config \
    ${PACKAGECONFIG_CONFARGS} \
"

# for qtbase configuration we need default settings
# since we cannot set empty set filename to a not existent file
deltask generate_qt_config_file

XPLATFORM_toolchain-clang = "linux-oe-clang"
XPLATFORM ?= "linux-oe-g++"

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
        -importdir ${OE_QMAKE_PATH_IMPORTS} \
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

do_install_append() {
    # Avoid qmake error "Cannot read [...]/usr/lib/qt5/mkspecs/oe-device-extra.pri: No such file or directory"
    touch ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/oe-device-extra.pri

    # Replace host paths with qmake built-in properties
    sed -i -e 's|${STAGING_DIR_NATIVE}|$$[QT_HOST_PREFIX/get]|g' \
        -e 's|${STAGING_DIR_HOST}|$$[QT_SYSROOT]|g' \
        ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/*.pri

    # Update the mkspecs to include the default OE toolchain config for the target
    conf=${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/${XPLATFORM}/qmake.conf

    # qmake already knows the sysroot, see above $$[QT_SYSROOT], so remove the hardcoded sysroot
    OE_QMAKE_CC_NO_SYSROOT=$(echo ${OE_QMAKE_CC} | sed -e 's!--sysroot=[^ ]*!!g')
    OE_QMAKE_CXX_NO_SYSROOT=$(echo ${OE_QMAKE_CXX} | sed -e 's!--sysroot=[^ ]*!!g')
    OE_QMAKE_LINK_NO_SYSROOT=$(echo ${OE_QMAKE_LINK} | sed -e 's!--sysroot=[^ ]*!!g')

    echo "" >> $conf
    echo "# default compiler options which can be overwritten from the environment" >> $conf
    echo "isEmpty(QMAKE_AR): QMAKE_AR = ${OE_QMAKE_AR} cqs" >> $conf
    echo "isEmpty(QMAKE_CC): QMAKE_CC = $OE_QMAKE_CC_NO_SYSROOT" >> $conf
    echo "isEmpty(QMAKE_CFLAGS): QMAKE_CFLAGS = ${OE_QMAKE_CFLAGS}" >> $conf
    echo "isEmpty(QMAKE_CXX): QMAKE_CXX = $OE_QMAKE_CXX_NO_SYSROOT" >> $conf
    echo "isEmpty(QMAKE_CXXFLAGS): QMAKE_CXXFLAGS = ${OE_QMAKE_CXXFLAGS}" >> $conf
    echo "isEmpty(QMAKE_LINK): QMAKE_LINK = $OE_QMAKE_LINK_NO_SYSROOT" >> $conf
    echo "isEmpty(QMAKE_LINK_SHLIB): QMAKE_LINK_SHLIB = $OE_QMAKE_LINK_NO_SYSROOT" >> $conf
    echo "isEmpty(QMAKE_LINK_C): QMAKE_LINK_C = $OE_QMAKE_LINK_NO_SYSROOT" >> $conf
    echo "isEmpty(QMAKE_LINK_C_SHLIB): QMAKE_LINK_C_SHLIB = $OE_QMAKE_LINK_NO_SYSROOT" >> $conf
    echo "isEmpty(QMAKE_LFLAGS): QMAKE_LFLAGS = ${OE_QMAKE_LDFLAGS}" >> $conf
    echo "isEmpty(QMAKE_STRIP): QMAKE_STRIP = ${TARGET_PREFIX}strip" >> $conf

    generate_target_qt_config_file ${D}${OE_QMAKE_PATH_BINS}/qt.conf
}

# mkspecs have mac specific scripts that depend on perl and bash
INSANE_SKIP_${PN}-mkspecs += "file-rdeps"

RRECOMMENDS_${PN}-plugins += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libx11-locale', '', d)}"

SRCREV = "6c6ace9d23f90845fd424e474d38fe30f070775e"
