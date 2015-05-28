require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & Digia-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=d87ae0d200af76dca730d911474cbe5b \
    file://LICENSE.LGPLv3;md5=ffcfac38a32c9ebdb8ff768fa1702478 \
    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

# common for qtbase-native, qtbase-nativesdk and qtbase
SRC_URI += "\
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0003-Add-external-hostbindir-option.patch \
    file://0004-qt_module-Fix-pkgconfig-and-libtool-replacements.patch \
    file://0005-Revert-eglfs-Print-the-chosen-config-in-debug-mode.patch \
    file://0006-qeglplatformintegration-Undefine-CursorShape-from-X..patch \
    file://0007-configure-bump-path-length-from-256-to-512-character.patch \
    file://0008-eglfs-fix-egl-error-for-platforms-only-supporting-on.patch \
    file://0009-QOpenGLPaintDevice-sub-area-support.patch \
    file://0010-Make-Qt5GuiConfigExtras.cmake-find-gl-es-include-dir.patch \
"
 
# specific for qtbase
SRC_URI += "\
    file://0011-qmake-don-t-build-it-in-configure-but-allow-to-build.patch \
    file://0012-Set-paths-for-target-properly.patch \
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
PACKAGECONFIG_X11 ?= "${@base_contains('DISTRO_FEATURES', 'x11', 'xcb xvideo xsync xshape xrender xrandr xfixes xinput2 xinput xinerama xcursor gtkstyle xkb', '', d)}"
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
PACKAGECONFIG[sql-sqlite] = "-sql-sqlite,-no-sql-sqlite,sqlite3"
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
PACKAGECONFIG[evdev] = "-evdev,-no-evdev"
PACKAGECONFIG[mtdev] = "-mtdev,-no-mtdev,mtdev"
# depends on glib
PACKAGECONFIG[fontconfig] = "-fontconfig,-no-fontconfig,fontconfig"
PACKAGECONFIG[gtkstyle] = "-gtkstyle,-no-gtkstyle,gtk+"
PACKAGECONFIG[directfb] = "-directfb,-no-directfb,directfb"
PACKAGECONFIG[linuxfb] = "-linuxfb,-no-linuxfb"
PACKAGECONFIG[mitshm] = "-mitshm,-no-mitshm,mitshm"
PACKAGECONFIG[kms] = "-kms,-no-kms,virtual/mesa virtual/egl"
# needed for qtwebkit
PACKAGECONFIG[icu] = "-icu,-no-icu,icu"
PACKAGECONFIG[udev] = "-libudev,-no-libudev,udev"
# use -openssl-linked here to ensure that RDEPENDS for libcrypto and libssl are detected
PACKAGECONFIG[openssl] = "-openssl-linked,-no-openssl,openssl"
PACKAGECONFIG[alsa] = "-alsa,-no-alsa,alsa-lib"
PACKAGECONFIG[pulseaudio] = "-pulseaudio,-no-pulseaudio,pulseaudio"
PACKAGECONFIG[nis] = "-nis,-no-nis"
PACKAGECONFIG[widgets] = "-widgets,-no-widgets"

QT_CONFIG_FLAGS += " \
    -shared \
    -silent \
    -no-pch \
    -no-rpath \
    -pkg-config \
    ${EXTRA_OECONF} \
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
OE_QMAKE_QMAKE_ORIG = "${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/qmake"
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
        mkdir ${B}/bin
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
        -hostdatadir ${OE_QMAKE_PATH_HOST_DATA} \
        -external-hostbindir ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS} \
        -platform ${OE_QMAKESPEC} \
        -xplatform linux-oe-g++ \
        ${QT_CONFIG_FLAGS}

    qmake5_base_do_configure
}

do_compile_append() {
    # copy corelib/3rdparty/qmake sources required by qmake -> ${B}
    cp -ra ${S}/src/corelib ${B}/src
    cp -ra ${S}/src/3rdparty ${B}/src
    cp -ra ${S}/qmake ${B}
    cp ${S}/.qmake.conf ${B}/qmake
    cd ${B}/qmake
    # align qt5 tools source path to ${S}
    sed -i 's:\.\./tools:${S}/tools:g' qmake.pro
    ../${OE_QMAKE_QMAKE}
    oe_runmake CC="${CC}" CXX="${CXX}"
}

do_install_append() {
    install -m 0755 ${B}/qmake/bin/qmake ${D}/${bindir}/${QT_DIR_NAME}

    ### Fix up the binaries to the right location
    ### TODO: FIX
    # install fonts manually if they are missing
    if [ ! -d ${D}/${OE_QMAKE_PATH_LIBS}/fonts ]; then
        cp -a ${S}/lib/fonts ${D}/${OE_QMAKE_PATH_LIBS}
        chown -R root:root ${D}/${OE_QMAKE_PATH_LIBS}/fonts
    fi

    # Remove example.pro file as it is useless
    rm -f ${D}${OE_QMAKE_PATH_EXAMPLES}/examples.pro	

    # Remove macx-ios-clang directory because /usr/lib/qt5/mkspecs/macx-ios-clang/rename_main.sh:#!/bin/bash
    # triggers QA Issue: qtbase-mkspecs requires /bin/bash, but no providers in its RDEPENDS [file-rdeps]
    rm -rf ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/macx-ios-clang
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

FILES_${PN}-fonts-ttf-vera       = "${OE_QMAKE_PATH_LIBS}/fonts/Vera*.ttf"
FILES_${PN}-fonts-ttf-dejavu     = "${OE_QMAKE_PATH_LIBS}/fonts/DejaVu*.ttf"
FILES_${PN}-fonts-pfa            = "${OE_QMAKE_PATH_LIBS}/fonts/*.pfa"
FILES_${PN}-fonts-pfb            = "${OE_QMAKE_PATH_LIBS}/fonts/*.pfb"
FILES_${PN}-fonts-qpf            = "${OE_QMAKE_PATH_LIBS}/fonts/*.qpf*"
FILES_${PN}-fonts                = "${OE_QMAKE_PATH_LIBS}/fonts/README \
                                    ${OE_QMAKE_PATH_LIBS}/fonts/fontdir"

sysroot_stage_dirs_append() {
    # $to is 2nd parameter passed to sysroot_stage_dir, e.g. ${SYSROOT_DESTDIR} passed from sysroot_stage_all
    rm -rf $to${OE_QMAKE_PATH_LIBS}/fonts
}

SRCREV = "f58e882b7594c59b6050d3c87562fcf836d10f60"
