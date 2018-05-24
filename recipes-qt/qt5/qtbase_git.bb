require qt5.inc
require qt5-git.inc

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
SRC_URI += "\
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0003-Add-external-hostbindir-option.patch \
    file://0004-qt_module-Fix-pkgconfig-and-libtool-replacements.patch \
    file://0005-configure-bump-path-length-from-256-to-512-character.patch \
    file://0009-Disable-all-unknown-features-instead-of-erroring-out.patch \
    file://0010-Pretend-Qt5-wasn-t-found-if-OE_QMAKE_PATH_EXTERNAL_H.patch \
"

# only for target qtbase
SRC_URI += "\
    file://0008-configure-paths-for-target-qmake-properly.patch \
"

DEPENDS += "qtbase-native"

# LGPL-3.0 is used only in src/plugins/platforms/android/extract.cpp

# for syncqt
RDEPENDS_${PN}-tools += "perl"

# separate some parts of PACKAGECONFIG which are often changed
PACKAGECONFIG_GL ?= "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gl', '', d)}"
PACKAGECONFIG_FB ?= "${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)}"
PACKAGECONFIG_X11 ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcb xrender xinput2 glib xkb xkbcommon-evdev', '', d)}"
PACKAGECONFIG_FONTS ?= ""
PACKAGECONFIG_SYSTEM ?= "jpeg libpng zlib"
PACKAGECONFIG_DISTRO ?= ""
# Either release or debug, can be overridden in bbappends
PACKAGECONFIG_RELEASE ?= "release"
# This is in qt5.inc, because qtwebkit-examples are using it to enable ca-certificates dependency
# PACKAGECONFIG_OPENSSL ?= "openssl"
PACKAGECONFIG_DEFAULT ?= "dbus udev evdev widgets tools libs freetype"

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
PACKAGECONFIG[pcre] = "-system-pcre,-qt-pcre,pcre"
PACKAGECONFIG[eglfs] = "-eglfs,-no-eglfs,drm"
PACKAGECONFIG[gl] = "-opengl desktop,,virtual/libgl"
PACKAGECONFIG[gles2] = "-opengl es2,,virtual/libgles2 virtual/egl"
PACKAGECONFIG[no-opengl] = "-no-opengl"
PACKAGECONFIG[tslib] = "-tslib,-no-tslib,tslib"
PACKAGECONFIG[cups] = "-cups,-no-cups,cups"
PACKAGECONFIG[dbus] = "-dbus,-no-dbus,dbus"
PACKAGECONFIG[xcb] = "-xcb -xcb-xlib -system-xcb,-no-xcb,libxcb xcb-util-wm xcb-util-image xcb-util-keysyms xcb-util-renderutil"
PACKAGECONFIG[sql-ibase] = "-sql-ibase,-no-sql-ibase"
PACKAGECONFIG[sql-mysql] = "-sql-mysql -mysql_config ${STAGING_BINDIR_CROSS}/mysql_config,-no-sql-mysql,mysql5"
PACKAGECONFIG[sql-psql] = "-sql-psql,-no-sql-psql,postgresql"
PACKAGECONFIG[sql-odbc] = "-sql-odbc,-no-sql-odbc"
PACKAGECONFIG[sql-oci] = "-sql-oci,-no-sql-oci"
PACKAGECONFIG[sql-tds] = "-sql-tds,-no-sql-tds"
PACKAGECONFIG[sql-db2] = "-sql-db2,-no-sql-db2"
PACKAGECONFIG[sql-sqlite2] = "-sql-sqlite2,-no-sql-sqlite2,sqlite"
PACKAGECONFIG[sql-sqlite] = "-sql-sqlite -system-sqlite,-no-sql-sqlite,sqlite3"
PACKAGECONFIG[xinput2] = "-xinput2,-no-xinput2,libxi"
PACKAGECONFIG[xrender] = "-xrender,-no-xrender,libxrender"
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

QT_CONFIG_FLAGS += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', '-use-gold-linker', '-no-use-gold-linker', d)} \
    -shared \
    -silent \
    -no-pch \
    -no-rpath \
    -pkg-config \
    ${PACKAGECONFIG_CONFARGS} \
"

# for qtbase configuration we need default settings
# since we cannot set empty set filename to a not existent file
export OE_QMAKE_QTCONF_PATH = "foodummy"

# Causes qdrawhelper.s: Error: unaligned opcodes detected in executable segment
# when building qtbase/5.6.3+gitAUTOINC+e6f8b072d2-r0/git/src/gui/painting/qdrawhelper.cpp
ARM_INSTRUCTION_SET_armv4 = "arm"
ARM_INSTRUCTION_SET_armv5 = "arm"

do_configure() {
    # Avoid qmake error "Cannot read [...]/usr/lib/qt5/mkspecs/oe-device-extra.pri: No such file or directory" during configuration
    touch ${S}/mkspecs/oe-device-extra.pri

    ${S}/configure -v \
        -opensource -confirm-license \
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
        -xplatform linux-oe-g++ \
        ${QT_CONFIG_FLAGS}
}

do_install_append() {
    # Avoid qmake error "Cannot read [...]/usr/lib/qt5/mkspecs/oe-device-extra.pri: No such file or directory"
    touch ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/oe-device-extra.pri

    # Replace host paths with qmake built-in properties
    sed -i -e 's|${STAGING_DIR_NATIVE}${prefix_native}|$$[QT_HOST_PREFIX/get]|g' \
        -e 's|${STAGING_DIR_HOST}|$$[QT_SYSROOT]|g' \
        ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/*.pri

    # Fix up absolute paths in scripts
    grep -lr /usr/bin/perl ${D}${OE_QMAKE_PATH_QT_ARCHDATA}/ | \
        xargs -r sed -i -e '1s,#!.*perl,#! ${USRBINPATH}/env perl,'
}

# mkspecs have mac specific scripts that depend on perl and bash
INSANE_SKIP_${PN}-mkspecs += "file-rdeps"

RRECOMMENDS_${PN}-plugins += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libx11-locale', '', d)}"

SRCREV = "49dc9aa409d727824f26b246054a22b5a7dd5980"
