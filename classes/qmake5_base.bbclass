QMAKE_MKSPEC_PATH_NATIVE = "${STAGING_DATADIR_NATIVE}/${QT_DIR_NAME}/mkspecs"
OE_QMAKE_PLATFORM_NATIVE = "${BUILD_OS}-oe-g++"

QMAKE_MKSPEC_PATH = "${STAGING_DATADIR}/${QT_DIR_NAME}/mkspecs"
OE_QMAKE_PLATFORM = "${TARGET_OS}-oe-g++"

EXTRA_OEMAKE = " MAKEFLAGS='${PARALLEL_MAKE}'"

EXTRA_ENV = 'QMAKE="${OE_QMAKE_QMAKE} -d -after \
             INCPATH+=${STAGING_INCDIR}/freetype2 LIBS+=-L${STAGING_LIBDIR}" \
             LINK="${CXX} -Wl,-rpath-link,${STAGING_LIBDIR}" \
             AR="${TARGET_PREFIX}ar cqs" \
             MOC="${OE_QMAKE_MOC}" \
             UIC="${OE_QMAKE_UIC}" \
             RCC="${OE_QMAKE_RCC}" \
             MAKE="make -e ${PARALLEL_MAKE}"'

export OE_QMAKESPEC = "${QMAKE_MKSPEC_PATH_NATIVE}/${OE_QMAKE_PLATFORM_NATIVE}"
export OE_XQMAKESPEC = "${QMAKE_MKSPEC_PATH}/${OE_QMAKE_PLATFORM}"
export OE_QMAKE_QMAKE = "${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/qmake"
export OE_QMAKE_UIC = "${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/uic"
export OE_QMAKE_MOC = "${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/moc"
export OE_QMAKE_RCC = "${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/rcc"
export OE_QMAKE_COMPILER = "${CC}"
export OE_QMAKE_CC = "${CC}"
export OE_QMAKE_CFLAGS = "${CFLAGS}"
export OE_QMAKE_CXX = "${CXX}"
export OE_QMAKE_LDFLAGS = "${LDFLAGS}"
export OE_QMAKE_AR = "${AR} cqs"
export OE_QMAKE_STRIP = "echo"
export OE_QMAKE_RPATH = "-Wl,-rpath-link,"
export OE_QMAKE_CONF_COMPILER = "g++"
export QT_CONF_PATH = "${WORKDIR}/qt.conf"
export QT_DIR_NAME ?= "qt5"

# do not export STRIP to the environment
STRIP[unexport] = "1"

do_generate_qt_config_file() {
    cat > ${WORKDIR}/qt.conf <<EOF
[Paths]
Binaries = ${bindir}
Libraries = ${libdir}
Prefix = ${prefix}
Headers = ${includedir}/${QT_DIR_NAME}
Data = ${datadir}/${QT_DIR_NAME}
ArchData = ${libdir}/${QT_DIR_NAME}
Documentation = ${docdir}/${QT_DIR_NAME}
HostData = ${STAGING_DATADIR}/${QT_DIR_NAME}
HostSpecPath = ${QMAKE_MKSPEC_PATH}
HostBinaries = ${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}
EOF
}

addtask generate_qt_config_file after do_patch before do_configure
