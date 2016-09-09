require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=fb91571854638f10b2e5f36562661a5a \
    file://LICENSE.LGPLv3;md5=a909b94c1c9674b2aa15ff03a86f518a \
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
    file://0006-QOpenGLPaintDevice-sub-area-support.patch \
    file://0007-linux-oe-g-Invert-conditional-for-defining-QT_SOCKLE.patch \
    file://0008-configure-paths-for-target-qmake-properly.patch \
    file://0009-Pretend-Qt5-wasn-t-found-if-OE_QMAKE_PATH_EXTERNAL_H.patch \
"

DEPENDS += "qtbase-native"

# LGPL-3.0 is used only in src/plugins/platforms/android/extract.cpp

# for syncqt
RDEPENDS_${PN}-tools += "perl"

# separate some parts of PACKAGECONFIG which are often changed
# be aware that you need to add icu to build qtwebkit, default
# PACKAGECONFIG is kept rather minimal for people who don't need
# stuff like webkit (and it's easier to add options than remove)

PACKAGECONFIG_GL ?= "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gl', '', d)}"
PACKAGECONFIG_FB ?= "${@bb.utils.contains('DISTRO_FEATURES', 'directfb', 'directfb', '', d)}"
PACKAGECONFIG_X11 ?= "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcb xvideo xsync xshape xrender xrandr xfixes xinput2 xinput xinerama xcursor glib gtkstyle xkb', '', d)}"
PACKAGECONFIG_FONTS ?= ""
PACKAGECONFIG_SYSTEM ?= "jpeg libpng zlib"
PACKAGECONFIG_MULTIMEDIA ?= "${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"
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
PACKAGECONFIG[eglfs] = "-eglfs,-no-eglfs,drm"
PACKAGECONFIG[gl] = "-opengl desktop,,virtual/libgl"
PACKAGECONFIG[gles2] = "-opengl es2,,virtual/libgles2 virtual/egl"
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
PACKAGECONFIG[xinerama] = "-xinerama,-no-xinerama,libxinerama"
PACKAGECONFIG[xinput] = "-xinput,-no-xinput"
PACKAGECONFIG[xinput2] = "-xinput2,-no-xinput2,libxi"
PACKAGECONFIG[xfixes] = "-xfixes,-no-xfixes,libxfixes"
PACKAGECONFIG[xrandr] = "-xrandr,-no-xrandr,libxrandr"
PACKAGECONFIG[xrender] = "-xrender,-no-xrender,libxrender"
PACKAGECONFIG[xshape] = "-xshape,-no-xshape"
PACKAGECONFIG[xsync] = "-xsync,-no-xsync"
PACKAGECONFIG[xvideo] = "-xvideo,-no-xvideo"
PACKAGECONFIG[openvg] = "-openvg,-no-openvg"
PACKAGECONFIG[iconv] = "-iconv,-no-iconv,virtual/libiconv"
PACKAGECONFIG[xkb] = "-xkb,-no-xkb -no-xkbcommon,libxkbcommon"
PACKAGECONFIG[xkbcommon-evdev] = "-xkbcommon-evdev,-no-xkbcommon-evdev,libxkbcommon,xkeyboard-config"
PACKAGECONFIG[evdev] = "-evdev,-no-evdev"
PACKAGECONFIG[mtdev] = "-mtdev,-no-mtdev,mtdev"
# depends on glib
PACKAGECONFIG[fontconfig] = "-fontconfig,-no-fontconfig,fontconfig"
PACKAGECONFIG[gtkstyle] = "-gtkstyle,-no-gtkstyle,gtk+"
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
PACKAGECONFIG[widgets] = "-widgets,-no-widgets"
PACKAGECONFIG[libproxy] = "-libproxy,-no-libproxy,libproxy"
PACKAGECONFIG[libinput] = "-libinput,-no-libinput,libinput"

QT_CONFIG_FLAGS += " \
    -shared \
    -silent \
    -no-pch \
    -no-rpath \
    -pkg-config \
    ${PACKAGECONFIG_CONFARGS} \
"

do_generate_qt_config_file_append() {
    cat >> ${QT_CONF_PATH} <<EOF

[EffectivePaths]
Prefix=..
EOF
}

# qtbase is exception, we need to use mkspecs from ${S}
QMAKE_MKSPEC_PATH = "${B}"

# another exception is that we need to run bin/qmake, because EffectivePaths are relative to qmake location
OE_QMAKE_QMAKE_ORIG = "${STAGING_BINDIR_NATIVE}${QT_DIR_NAME}/qmake"
OE_QMAKE_QMAKE = "bin/qmake"

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
    # we need symlink in path relative to source, because
    # EffectivePaths:Prefix is relative to qmake location
    if [ ! -e ${B}/bin/qmake ]; then
        mkdir -p ${B}/bin
        ln -sf ${OE_QMAKE_QMAKE_ORIG} ${B}/bin/qmake
    fi

    ${S}/configure -v \
        -opensource -confirm-license \
        -sysroot ${STAGING_DIR_TARGET} \
        -no-gcc-sysroot \
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
        -platform ${OE_QMAKESPEC} \
        -xplatform linux-oe-g++ \
        ${QT_CONFIG_FLAGS}

    qmake5_base_do_configure
}

do_install_append() {
    # Avoid qmake error "Cannot read [...]/usr/lib/qt5/mkspecs/oe-device-extra.pri: No such file or directory"
    touch ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/oe-device-extra.pri

    install -m 0755 ${B}/bin/qmake-target ${D}/${bindir}${QT_DIR_NAME}/qmake

    ### Fix up the binaries to the right location
    ### TODO: FIX
    # install fonts manually if they are missing
    if [ ! -d ${D}/${OE_QMAKE_PATH_QT_FONTS} ]; then
        mkdir -p ${D}/${OE_QMAKE_PATH_QT_FONTS}
        cp -d ${S}/lib/fonts/* ${D}/${OE_QMAKE_PATH_QT_FONTS}
        chown -R root:root ${D}/${OE_QMAKE_PATH_QT_FONTS}
    fi
    # Remove example.pro file as it is useless
    rm -f ${D}${OE_QMAKE_PATH_EXAMPLES}/examples.pro

    # Remove macx-ios-clang directory because /usr/lib/qt5/mkspecs/macx-ios-clang/rename_main.sh:#!/bin/bash
    # triggers QA Issue: qtbase-mkspecs requires /bin/bash, but no providers in its RDEPENDS [file-rdeps]
    rm -rf ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/macx-ios-clang
    # and this one has /bin/bash shebang, but checkbashisms doesn't show any reason for it
    sed -i 's@^#!/bin/bash$@#!/bin/sh@g' ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/features/data/mac/objc_namespace.sh

    # Replace host paths with qmake built-in properties
    sed -i -e 's|${STAGING_DIR_NATIVE}${prefix_native}|$$[QT_HOST_PREFIX]|g' \
        -e 's|${STAGING_DIR_HOST}|$$[QT_SYSROOT]|g' \
        ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/*.pri
}

PACKAGES =. " \
    ${PN}-fonts \
    ${PN}-fonts-ttf-vera \
    ${PN}-fonts-ttf-dejavu \
    ${PN}-fonts-pfa \
    ${PN}-fonts-pfb \
    ${PN}-fonts-qpf \
"

RRECOMMENDS_${PN}-fonts = " \
    ${PN}-fonts-ttf-vera \
    ${PN}-fonts-ttf-dejavu \
    ${PN}-fonts-pfa \
    ${PN}-fonts-pfb \
    ${PN}-fonts-qpf \
"

ALLOW_EMPTY_${PN}-fonts = "1"

FILES_${PN}-fonts-ttf-vera       = "${OE_QMAKE_PATH_QT_FONTS}/Vera*.ttf"
FILES_${PN}-fonts-ttf-dejavu     = "${OE_QMAKE_PATH_QT_FONTS}/DejaVu*.ttf"
FILES_${PN}-fonts-pfa            = "${OE_QMAKE_PATH_QT_FONTS}/*.pfa"
FILES_${PN}-fonts-pfb            = "${OE_QMAKE_PATH_QT_FONTS}/*.pfb"
FILES_${PN}-fonts-qpf            = "${OE_QMAKE_PATH_QT_FONTS}/*.qpf*"
FILES_${PN}-fonts                = "${OE_QMAKE_PATH_QT_FONTS}/README \
                                    ${OE_QMAKE_PATH_QT_FONTS}/fontdir"

RRECOMMENDS_${PN}-plugins += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libx11-locale', '', d)}"

sysroot_stage_dirs_append() {
    # $to is 2nd parameter passed to sysroot_stage_dir, e.g. ${SYSROOT_DESTDIR} passed from sysroot_stage_all
    rm -rf $to${OE_QMAKE_PATH_QT_FONTS}
}

SRCREV = "7bf002c3b3f8009138fca217c7fa0c234aed21bd"
