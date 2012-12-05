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

EXTRA_ENV = 'QMAKE="${STAGING_BINDIR_NATIVE}/qmake -d -after \
             INCPATH+=${STAGING_INCDIR}/freetype2 LIBS+=-L${STAGING_LIBDIR}" \
             LINK="${CXX} -Wl,-rpath-link,${STAGING_LIBDIR}" \
             AR="${TARGET_PREFIX}ar cqs" \
             MOC="${STAGING_BINDIR_NATIVE}/moc" UIC="${STAGING_BINDIR_NATIVE}/uic" MAKE="make -e ${PARALLEL_MAKE}"'

export QT_CONF_PATH="${S}/qt.conf"

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

    mkdir -p ${STAGING_DATADIR}/${QT_DIR_NAME}
    cp -r ${STAGING_DATADIR_NATIVE}/${QT_DIR_NAME}/mkspecs ${STAGING_DATADIR}/${QT_DIR_NAME}/mkspecs

    echo "[Paths]"                                              > $QT_CONF_PATH
    echo "Binaries=${bindir}"                                   >> $QT_CONF_PATH
    echo "Headers=${includedir}/${QT_DIR_NAME}"                 >> $QT_CONF_PATH
    echo "Documentation=${docdir}/${QT_DIR_NAME}"               >> $QT_CONF_PATH
    echo "Libraries=${libdir}"                                  >> $QT_CONF_PATH
    echo "Plugins=${libdir}/${QT_DIR_NAME}/plugins"             >> $QT_CONF_PATH
    echo "Data=${datadir}/${QT_DIR_NAME}"                       >> $QT_CONF_PATH
    echo "Translations=${datadir}/${QT_DIR_NAME}/translations"  >> $QT_CONF_PATH
    echo "Settings=${sysconfdir}/${QT_DIR_NAME}"                >> $QT_CONF_PATH
    echo "Examples=${bindir}/${QT_DIR_NAME}/examples"           >> $QT_CONF_PATH
    echo "HostBinaries=${STAGING_BINDIR_NATIVE}"                >> $QT_CONF_PATH
    echo "HostData=${STAGING_DATADIR}/${QT_DIR_NAME}"    >> $QT_CONF_PATH

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
        -platform ${TARGET_OS}-oe-g++ \
        -xplatform ${TARGET_OS}-oe-g++ \
        ${QT_CONFIG_FLAGS}

    ### Since we are pointing our host data we need to get them there before compile
    if [ ! -e ${STAGING_DATADIR}/${QT_DIR_NAME}/mkspecs/${TARGET_OS}-oe-g++ ]; then
        cp -rf mkspecs/${TARGET_OS}-oe-g++ ${STAGING_DATADIR}/${QT_DIR_NAME}/mkspecs/${TARGET_OS}-oe-g++
    fi
    ### qmodule.pri and qconfig.pri contain target specific stuff copy them over as well
    cp -f mkspecs/qmodule.pri ${STAGING_DATADIR}/${QT_DIR_NAME}/mkspecs/qmodule.pri
    cp -f mkspecs/qconfig.pri ${STAGING_DATADIR}/${QT_DIR_NAME}/mkspecs/qconfig.pri
}

do_compile() {
    unset CFLAGS CXXFLAGS AR

    oe_runmake ${EXTRA_ENV}

    # Build qmake for the target arch
    cd ${S}/qmake
    ${OE_QMAKE_QMAKE}
    oe_runmake CC="${CC}" CXX="${CXX}"
    cd ${S}
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}

    # Fix up the binaries to the right location
    install -d ${D}${bindir}/
    mv ${D}/${STAGING_BINDIR_NATIVE}/* ${D}${bindir}/
    rm -rf ${D}/${STAGING_BINDIR_NATIVE}/

    # Install the right arch qmake
    rm ${D}/${bindir}/qmake
    install -m 0755 bin/qmake2 ${D}${bindir}/qmake
}

FILES_${PN} = "${libdir}/*.so.*"
FILES_${PN}-dbg = "${libdir}/.debug/*.so.*"
FILES_${PN}-plugins = "${libdir}/${QT_DIR_NAME}/platforms/*"
FILES_${PN}-plugins-dbg = "${libdir}/${QT_DIR_NAME}/platforms/.debug/*"

FILES_${PN}-dev = "${libdir}/cmake/* ${libdir}/pkgconfig/*.pc ${libdir}/*.la ${libdir}/*.prl ${includedir}/qt5/*"
FILES_${PN}-staticdev = "${libdir}/libQt*.a"
FILES_${PN}-tools = "${bindir}/*"
FILES_${PN}-mkspecs = "${STAGING_DATADIR}/${QT_DIR_NAME}/mkspecs/*"
FILES_${PN}-doc = "${STAGING_DATADIR}/doc/*"
