require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=d3bb688e8d381a9fa5ee9063114b366d \
    file://LICENSE.LGPLv3;md5=3fd06ee442011942b532cc6dedb0b39c \
    file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

# common for qtbase-native, qtbase-nativesdk and qtbase
SRC_URI += "\
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-configure-Separate-host-and-build-platform.patch \
    file://0004-qt_module-Fix-pkgconfig-and-libtool-replacements.patch \
    file://0005-configure-bump-path-length-from-256-to-512-character.patch \
    file://0006-QOpenGLPaintDevice-sub-area-support.patch \
    file://0007-linux-oe-g-Invert-conditional-for-defining-QT_SOCKLE.patch \
    file://0008-configure-paths-for-target-qmake-properly.patch \
"

DEPENDS += "qtbase-native"

# LGPL-3.0 is used only in src/plugins/platforms/android/extract.cpp

# for syncqt
RDEPENDS_${PN}-tools += "perl"

# separate some parts of PACKAGECONFIG which are often changed
# be aware that you need to add icu to build qtwebkit, default
# PACKAGECONFIG is kept rather minimal for people who don't need
# stuff like webkit (and it's easier to add options than remove)

PACKAGECONFIG_GL ?= "${@base_contains('DISTRO_FEATURES', 'opengl', 'gl', '', d)}"
PACKAGECONFIG_FB ?= "${@base_contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)}"
PACKAGECONFIG_X11 ?= "${@base_contains('DISTRO_FEATURES', 'x11', 'xcb xsync xshape xrender xrandr xfixes xinput2 xcursor glib xkb', '', d)}"
PACKAGECONFIG_FONTS ?= ""
PACKAGECONFIG_SYSTEM ?= "jpeg libpng zlib"
PACKAGECONFIG_MULTIMEDIA ?= "${@base_contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"
PACKAGECONFIG_DISTRO ?= ""
# Either release or debug, can be overridden in bbappends
PACKAGECONFIG_RELEASE ?= "release"
# This is in qt5.inc, because qtwebkit-examples are using it to enable ca-certificates dependency
# PACKAGECONFIG_OPENSSL ?= "openssl"
PACKAGECONFIG_DEFAULT ?= "dbus udev evdev widgets tools libs"

PACKAGECONFIG ?= " \
    ${PACKAGECONFIG_RELEASE} \
    ${PACKAGECONFIG_DEFAULT} \
    ${PACKAGECONFIG_OPENSSL} \
    ${PACKAGECONFIG_GL} \
    ${PACKAGECONFIG_FB} \
    ${PACKAGECONFIG_X11} \
    ${PACKAGECONFIG_FONTS} \
    ${PACKAGECONFIG_SYSTEM} \
    ${PACKAGECONFIG_MULTIMEDIA} \
    ${PACKAGECONFIG_DISTRO} \
"

PACKAGECONFIG[release] = "-release,-debug"
PACKAGECONFIG[developer] = "-developer-build"
PACKAGECONFIG[sm] = "-sm,-no-sm"
PACKAGECONFIG[tests] = "-make tests,-nomake tests"
PACKAGECONFIG[examples] = "-make examples -compile-examples,-nomake examples"
PACKAGECONFIG[tools] = "-make tools,-nomake tools"
# only for completeness, configure will add libs even if you try to explicitly remove it
PACKAGECONFIG[libs] = "-make libs,-nomake libs"
# accessibility is required to compile qtquickcontrols
PACKAGECONFIG[accessibility] = "-accessibility,-no-accessibility"
PACKAGECONFIG[glib] = "-glib,-no-glib,glib-2.0"
# use either system freetype or bundled freetype, if you disable freetype completely
# fontdatabases/basic/qbasicfontdatabase.cpp will fail to build and system freetype
# works only together with fontconfig
PACKAGECONFIG[freetype] = "-system-freetype,-freetype,freetype"
PACKAGECONFIG[harfbuzz] = "-system-harfbuzz,-no-harfbuzz,harfbuzz"
PACKAGECONFIG[jpeg] = "-system-libjpeg,-no-libjpeg,jpeg"
PACKAGECONFIG[libpng] = "-system-libpng,-no-libpng,libpng"
PACKAGECONFIG[zlib] = "-system-zlib,-qt-zlib,zlib"
PACKAGECONFIG[pcre] = "-system-pcre,-qt-pcre,pcre"
PACKAGECONFIG[gl] = "-opengl desktop -no-eglfs,,virtual/libgl"
PACKAGECONFIG[gles2] = "-opengl es2 -eglfs,,virtual/libgles2 virtual/egl"
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
PACKAGECONFIG[xcursor] = "-xcursor,-no-xcursor,libxcursor"
PACKAGECONFIG[xinput2] = "-xinput2,-no-xinput2,libxi"
PACKAGECONFIG[xfixes] = "-xfixes,-no-xfixes,libxfixes"
PACKAGECONFIG[xrandr] = "-xrandr,-no-xrandr,libxrandr"
PACKAGECONFIG[xrender] = "-xrender,-no-xrender,libxrender"
PACKAGECONFIG[xshape] = "-xshape,-no-xshape"
PACKAGECONFIG[xsync] = "-xsync,-no-xsync"
PACKAGECONFIG[openvg] = "-openvg,-no-openvg"
PACKAGECONFIG[iconv] = "-iconv,-no-iconv,virtual/libiconv"
PACKAGECONFIG[xkb] = "-xkb,-no-xkb -no-xkbcommon,libxkbcommon"
PACKAGECONFIG[xkbcommon-evdev] = "-xkbcommon-evdev,-no-xkbcommon-evdev,libxkbcommon,xkeyboard-config"
PACKAGECONFIG[evdev] = "-evdev,-no-evdev"
PACKAGECONFIG[mtdev] = "-mtdev,-no-mtdev,mtdev"
# depends on glib
PACKAGECONFIG[fontconfig] = "-fontconfig,-no-fontconfig,fontconfig"
PACKAGECONFIG[gtk] = "-gtk,-no-gtk,gtk+"
PACKAGECONFIG[directfb] = "-directfb,-no-directfb,directfb"
PACKAGECONFIG[linuxfb] = "-linuxfb,-no-linuxfb"
PACKAGECONFIG[mitshm] = "-mitshm,-no-mitshm,mitshm"
PACKAGECONFIG[kms] = "-kms,-no-kms,drm virtual/egl"
# needed for qtwebkit
PACKAGECONFIG[icu] = "-icu,-no-icu,icu"
PACKAGECONFIG[udev] = "-libudev,-no-libudev,udev"
PACKAGECONFIG[openssl] = "-openssl,-no-openssl,openssl,libssl"
PACKAGECONFIG[alsa] = "-alsa,-no-alsa,alsa-lib"
PACKAGECONFIG[pulseaudio] = "-pulseaudio,-no-pulseaudio,pulseaudio"
PACKAGECONFIG[nis] = "-nis,-no-nis"
PACKAGECONFIG[widgets] = "-widgets,-no-widgets"
PACKAGECONFIG[libproxy] = "-libproxy,-no-libproxy,libproxy"
PACKAGECONFIG[libinput] = "-libinput,-no-libinput,libinput"

QT_CONFIG_FLAGS += " \
    -shared \
    -silent \
    -no-pch \
    -no-rpath \
    -pkg-config \
    ${EXTRA_CONF_PACKAGECONFIG} \
"

OE_QMAKE_PATH_HOST_BINS = "${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}"

generate_qt_config_file_effective_paths() {
    cat >> ${OE_QMAKE_QTCONF_PATH} <<EOF
[EffectivePaths]
Prefix=${B}
EOF
}

OE_QMAKE_PATH_HOST_DATA = "${STAGING_LIBDIR}${QT_DIR_NAME}"
# qtbase is exception, we need to use mkspecs from ${S}
QMAKE_MKSPEC_PATH_TARGET = "${B}"
QMAKE_MKSPEC_PATH = "${S}"

# qtbase is exception, configure script is using our get(X)QEvalMakeConf and setBootstrapEvalVariable functions to read it from shell
export OE_QMAKE_COMPILER
export OE_QMAKE_CC
export OE_QMAKE_CFLAGS
export OE_QMAKE_CXX
export OE_QMAKE_CXXFLAGS
export OE_QMAKE_LINK
export OE_QMAKE_LDFLAGS
export OE_QMAKE_AR
export OE_QMAKE_STRIP

do_configure() {
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
        -hostbindir ${OE_QMAKE_PATH_HOST_BINS} \
        -external-hostbindir ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS} \
        -hostdatadir ${OE_QMAKE_PATH_HOST_DATA} \
        -platform ${OE_QMAKE_PLATFORM_NATIVE} \
        -xplatform linux-oe-g++ \
        ${QT_CONFIG_FLAGS}

    qmake5_base_do_configure
}

do_install_append() {

    # Remove example.pro file as it is useless
    rm -f ${D}${OE_QMAKE_PATH_EXAMPLES}/examples.pro

    # Remove macx-ios-clang directory because /usr/lib/qt5/mkspecs/macx-ios-clang/rename_main.sh:#!/bin/bash
    # triggers QA Issue: qtbase-mkspecs requires /bin/bash, but no providers in its RDEPENDS [file-rdeps]
    rm -rf ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/macx-ios-clang
    # and this one has /bin/bash shebang, but checkbashisms doesn't show any reason for it
    sed -i 's@^#!/bin/bash$@#!/bin/sh@g' ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/features/data/mac/objc_namespace.sh

    # Replace host paths with qmake built-in properties
    sed -i -e 's|${STAGING_DIR_NATIVE}${prefix_native}|$$[QT_HOST_PREFIX/get]|g' \
        -e 's|${STAGING_DIR_HOST}|$$[QT_SYSROOT]|g' \
        ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/*.pri
}

RRECOMMENDS_${PN}-plugins += "${@base_contains('DISTRO_FEATURES', 'x11', 'libx11-locale', '', d)}"

SRCREV = "1d8030cc64df2cdfdc0faf3d06ea7d9ed0306948"
