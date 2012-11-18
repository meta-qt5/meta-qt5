# Copyright (C) 2012 O.S. Systems Software LTDA.

inherit qmake5_base

SRC_URI += " \
  file://qmake-build.conf.sh \
  file://qmake.conf.sh \
  file://qplatformdefs.h \
"

# Qt5 is dependent on icu for localization
ICU = "icu "
ICU_powerpc = "pango"

DEPENDS += "qt5-tools-native virtual/libgl freetype jpeg libpng zlib openssl glib-2.0 ${ICU}"

require qt5_arch.inc

QT_MODULE ?= "${PN}"

QT_DISTRO_FLAGS ?= "-no-accessibility -no-sm"
QT_DISTRO_FLAGS_linuxstdbase = "-sm"

# Some can be used only for certain QT_MODULEs, so define them here, 
# but add them to QT_CONFIG_FLAGS e.g. in qtbase.inc
QT_SQL_DRIVER_FLAGS ?= "-no-sql-ibase -no-sql-mysql -no-sql-psql -no-sql-odbc -plugin-sql-sqlite"
QT_GLFLAGS ?= ""
QT_QT3SUPPORT ?= "-qt3support"
QT_XML ?= "-xmlpatterns"
QT_WEBKIT ?= "-webkit"
QT_PHONON ?= "-phonon"
QT_DBUS ?= "-qdbus"
QT_MULTIMEDIA ?= "${@base_contains('DISTRO_FEATURES', 'pulseaudio', '-pulseaudio', '-no-pulseaudio', d)}"
QT_MODULE_FLAGS ?= ""
QT_NAS ?= "-no-nas-sound"
QT_NIS ?= "-no-nis"
QT_CUPS ?= "-no-cups"
QT_STL ?= "-stl"
QT_SYSTEM_LIBS ?= "-system-libjpeg -system-libpng -system-zlib"
QT_TESTS ?= "-nomake tests"
QT_EXAMPLES ?= "-nomake examples"
QT_DEMOS ?= "-nomake demos"

QT_CONFIG_FLAGS += " \
  -release \
  -reduce-relocations \
  -shared \
  -silent \
  -glib \
  -no-pch \
  -no-rpath \
  -no-fast \
  ${QT_SYSTEM_LIBS} \
  ${QT_NIS} \
  ${QT_CUPS} \
  ${QT_SQL_DRIVER_FLAGS} \
  ${QT_DISTRO_FLAGS} \
  ${QT_MODULE_FLAGS} \
  ${QT_GLFLAGS} \
  ${QT_TESTS} \
  ${QT_EXAMPLES} \
  ${QT_DEMOS} \
"


EXTRA_OEMAKE = "-e"

do_configure() {
    set_endian

    if [ ! -e bin/qmake ]; then
        ln -sf ${STAGING_BINDIR_NATIVE}/qmake bin/qmake
    fi

    # Avoid problems with the linkers, since we want the linker to be g++
    unset LD

    mkdir -p mkspecs/${TARGET_OS}-oe-g++
    cp -f ${WORKDIR}/qplatformdefs.h mkspecs/${TARGET_OS}-oe-g++
    bash ${WORKDIR}/qmake.conf.sh > mkspecs/${TARGET_OS}-oe-g++/qmake.conf

    mkdir -p mkspecs/build-oe-g++
    cp -f ${WORKDIR}/qplatformdefs.h mkspecs/build-oe-g++

    ## FIXME, the file nameing is dumb...
    cp ${WORKDIR}/qmake-build.conf.sh  mkspecs/build-oe-g++/qmake.conf

    ./configure -v \
        -opensource -confirm-license \
        -prefix ${prefix} \
        -bindir ${bindir} \
        -libdir ${libdir} \
        -datadir ${datadir}/${QT_DIR_NAME} \
        -sysconfdir ${sysconfdir}/${QT_DIR_NAME} \
        -docdir ${docdir}/${QT_DIR_NAME} \
        -headerdir ${includedir}/${QT_DIR_NAME} \
        -plugindir ${libdir}/${QT_DIR_NAME}/plugins \
        -importdir ${libdir}/${QT_DIR_NAME}/imports \
        -translationdir ${datadir}/${QT_DIR_NAME}/translations \
        -examplesdir ${bindir}/${QT_DIR_NAME}/examples \
        -platform build-oe-g++ \
        -xplatform ${TARGET_OS}-oe-g++ \
        ${QT_CONFIG_FLAGS}
}

do_compile() {
    unset CFLAGS CXXFLAGS AR

    export QMAKESPEC="${S}/mkspecs/${TARGET_OS}-oe-g++"

    oe_runmake
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}

FILES_${PN} = "${libdir}/*.so.*"
FILES_${PN}-dbg = "${libdir}/.debug/*.so.*"
FILES_${PN}-dev = "${libdir}/cmake/* ${libdir}/pkgconfig/*.pc ${libdir}/*.la ${libdir}/*.prl ${includedir}/qt5/*"
FILES_${PN}-staticdev = "${libdir}/libQt*.a"
