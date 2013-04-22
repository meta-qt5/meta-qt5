# Copyright (C) 2012 O.S. Systems Software LTDA.

inherit qmake5_base

# Qt5 is dependent on icu for localization
ICU = "icu "
ICU_powerpc = "pango"

DEPENDS += "virtual/libgl freetype jpeg libpng zlib openssl glib-2.0 ${ICU}"

require qt5_arch.inc

QT_MODULE ?= "${BPN}"

QT_DISTRO_FLAGS ?= "-no-accessibility -no-sm"
QT_DISTRO_FLAGS_linuxstdbase = "-sm"

# Some can be used only for certain QT_MODULEs, so define them here, 
# but add them to QT_CONFIG_FLAGS e.g. in qtbase.inc
QT_SQL_DRIVER_FLAGS ?= "-no-sql-ibase -no-sql-mysql -no-sql-psql -no-sql-odbc -plugin-sql-sqlite"
QT_GLFLAGS ?= ""
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
    -pkg-config \
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

SEPB = "${WORKDIR}/build"
B = "${SEPB}"

do_configure() {
    set_arch
    set_endian

    # Avoid problems with the linkers, since we want the linker to be g++
    unset LD

    # Similar logic is in autotools.bbclass
    if [ -d ${B} -a "${S}" != "${B}" ] ; then
        # Existing separate build directory, exists, remove
        rm -rf "${B}/*"
    fi
}

do_compile() {
    unset CFLAGS CXXFLAGS AR

    oe_runmake ${EXTRA_ENV}
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}

PACKAGES =. "${PN}-tools ${PN}-plugins ${PN}-plugins-dbg ${PN}-mkspecs "
FILES_${PN}-tools = "${bindir}/*"
FILES_${PN}-plugins = "${libdir}/${QT_DIR_NAME}/plugins/*/*${SOLIBSDEV}"
FILES_${PN}-plugins-dbg = "${libdir}/${QT_DIR_NAME}/plugins/*/.debug/*"

FILES_${PN} += " \
    ${libdir}/${QT_DIR_NAME}/lib*${SOLIBS} \
"
FILES_${PN}-dev += " \
    ${libdir}/${QT_DIR_NAME}/pkgconfig \
    ${libdir}/${QT_DIR_NAME}/cmake/* \
    ${libdir}/${QT_DIR_NAME}/*.prl \
    ${libdir}/${QT_DIR_NAME}/*.la \
    ${datadir}/${QT_DIR_NAME}/* \
    ${includedir}/${QT_DIR_NAME}/* \
"
FILES_${PN}-dbg += " \
    ${libdir}/${QT_DIR_NAME}/.debug \
    ${libdir}/${QT_DIR_NAME}/lib*${SOLIBSDEV} \
"
FILES_${PN}-staticdev += " \
    ${libdir}/${QT_DIR_NAME}/*.a \
"
FILES_${PN}-mkspecs += "\
    ${libdir}/${QT_DIR_NAME}/mkspecs \
"
