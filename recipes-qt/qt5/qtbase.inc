require qt5.inc

SRC_URI += " \
  file://0001-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
  file://0002-qmake-is-already-built-in-qt5-tools-native.patch \
  file://0003-Allow-building-a-separate-qmake-for-the-target.patch \
  file://0004-configure-eval-QMAKE_CXX.patch \
  file://qmake.conf \
"

DEPENDS += "qtbase-native virtual/libgl freetype jpeg libpng zlib openssl glib-2.0 ${ICU}"

# to provide xcb/xcb_icccm.h, xcb/xcb_image.h, xcb/xcb_keysyms.h and dbus/dbus.h
XCB_DEPENDS += "xcb-util-wm xcb-util-image xcb-util-keysyms dbus"
# reenable when we switch to "-qt-xcb" and build of 
# DEPENDS += "${XCB_DEPENDS}"
# QT_MODULE_FLAGS = "-qt-xcb"
# src/platformsupport/glxconvenience/ is resolved to be before build of
# src/plugins/platforms/xcb/

QT_MODULE_FLAGS = "-no-xcb"


INC_PR = "r0"

QT_BASE_NAME = "qt5"
QT_DIR_NAME = "qt5"

# Qt uses atomic instructions not supported in thumb mode
ARM_INSTRUCTION_SET = "arm"

inherit qmake5

do_generate_qt_config_file() {
    :
    cat > ${WORKDIR}/qt.conf <<EOF
[Paths]
Binaries = ${bindir}
Libraries = ${libdir}
Prefix = ${prefix}
Headers = ${includedir}/${QT_DIR_NAME}
Data = ${datadir}/${QT_DIR_NAME}
ArchData = ${libdir}/${QT_DIR_NAME}
Documentation = ${docdir}/${QT_DIR_NAME}
HostData = ${S}
HostSpecPath = ${QMAKE_MKSPEC_PATH_NATIVE}
HostBinaries = ${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}
EOF
}

# qtbase is exception, we need to use mkspecs from ${S}
QMAKE_MKSPEC_PATH = "${S}/mkspecs"
OE_XQMAKESPEC="${S}/mkspecs/${TARGET_OS}-oe-g++"

do_configure_append() {
    if [ ! -e ${OE_XQMAKESPEC} ]; then
        mkdir -p ${OE_XQMAKESPEC}
    fi
    # use default qplatformdefs.h from linux-g++
    cp -va ${QMAKE_MKSPEC_PATH}/linux-g++/qplatformdefs.h ${OE_XQMAKESPEC}/
    # use modified qmake.conf which is using OE_QMAKE_* shell variables
    # exported from qmake5_base.bbclass to define toolchain and flags
    cp -va ${WORKDIR}/qmake.conf ${OE_XQMAKESPEC}/

    ./configure -v \
        -dont-process \
        -opensource -confirm-license \
        -prefix ${prefix} \
        -bindir ${bindir} \
        -libdir ${libdir} \
        -datadir ${S} \
        -sysconfdir ${sysconfdir}/${QT_DIR_NAME} \
        -docdir ${docdir}/${QT_DIR_NAME} \
        -headerdir ${includedir}/${QT_DIR_NAME} \
        -plugindir ${libdir}/${QT_DIR_NAME}/plugins \
        -importdir ${libdir}/${QT_DIR_NAME}/imports \
        -translationdir ${datadir}/${QT_DIR_NAME}/translations \
        -examplesdir ${bindir}/${QT_DIR_NAME}/examples \
        -hostdatadir ${S} \
        -hostbindir ${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME} \
        -platform ${OE_QMAKESPEC} \
        -xplatform ${OE_XQMAKESPEC} \
        ${QT_CONFIG_FLAGS}

    ${OE_QMAKE_QMAKE} -r -d
}

do_compile_append() {
    # Build qmake for the target arch
    cd ${S}/qmake
    ${OE_QMAKE_QMAKE}
    oe_runmake CC="${CC}" CXX="${CXX}"
    cd ${S}
}

do_install_append() {
    ### Fix up the binaries to the right location
    ### TODO: FIX
    install -d ${D}${bindir}/
    mv ${D}/${STAGING_BINDIR_NATIVE}/* ${D}${bindir}/
    rm -rf ${D}/${STAGING_BINDIR_NATIVE}/

    # Install the right arch qmake
    rm ${D}/${bindir}/qmake
    install -m 0755 bin/qmake2 ${D}${bindir}/qmake
}

PACKAGES =. "${PN}-fonts "
FILES_${PN}-fonts = "${libdir}/fonts"
