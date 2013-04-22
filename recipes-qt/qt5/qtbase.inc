require qt5.inc

SRC_URI += " \
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0003-Add-external-hostbindir-option.patch \
    file://0004-qmake-is-already-built-in-qtbase-native.patch \
    file://0005-Allow-building-a-separate-qmake-for-the-target.patch \
    file://0006-qt_functions-temporary-remove-isEmpty-check.patch \
"

DEPENDS += "qtbase-native virtual/libgl freetype jpeg libpng zlib openssl glib-2.0 ${ICU} udev"

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


#do_generate_qt_config_file() {
#    cat > ${WORKDIR}/qt.conf <<EOF
#[Paths]
#Binaries = ${bindir}
#Libraries = ${libdir}
#Prefix = ${prefix}
#Headers = ${includedir}/${QT_DIR_NAME}
#Data = ${datadir}/${QT_DIR_NAME}
#ArchData = ${libdir}/${QT_DIR_NAME}
#Documentation = ${docdir}/${QT_DIR_NAME}
#HostSpec = ${QMAKE_MKSPEC_PATH_NATIVE}
##HostData = ${S}
#TargetSpec = ${S}
#ExternalHostBinaries = ${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}
#EOF
#}

# qtbase is exception, we need to use mkspecs from ${S}
# QMAKE_MKSPEC_PATH = "${S}/mkspecs"
QMAKE_MKSPEC_PATH = "${B}"

do_configure_append() {
    if [ ! -e ${B}/bin/qmake ]; then
        mkdir ${B}/bin
        ln -sf ${OE_QMAKE_QMAKE} ${B}/bin/qmake
    fi

    ${S}/configure -v \
        -opensource -confirm-license \
        -sysroot ${STAGING_DIR_TARGET} \
        -no-gcc-sysroot \
        -prefix ${prefix} \
        -bindir ${bindir}/${QT_DIR_NAME} \
        -libdir ${libdir}/${QT_DIR_NAME} \
        -datadir ${datadir}/${QT_DIR_NAME} \
        -sysconfdir ${sysconfdir}/${QT_DIR_NAME} \
        -docdir ${docdir}/${QT_DIR_NAME} \
        -headerdir ${includedir}/${QT_DIR_NAME} \
        -plugindir ${libdir}/${QT_DIR_NAME}/plugins \
        -importdir ${libdir}/${QT_DIR_NAME}/imports \
        -translationdir ${datadir}/${QT_DIR_NAME}/translations \
        -examplesdir ${bindir}/${QT_DIR_NAME}/examples \
        -hostbindir ${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME} \
        -external-hostbindir ${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME} \
        -platform ${OE_QMAKESPEC} \
        -xplatform linux-oe-g++ \
        ${QT_CONFIG_FLAGS}

    ${OE_QMAKE_QMAKE} -r -d ${S}
}

do_compile_append() {
    # Build qmake for the target arch
    # Disable for now, because doesn't work well with separate ${B}
    # cd ${S}/qmake
    # ${OE_QMAKE_QMAKE}
    # oe_runmake CC="${CC}" CXX="${CXX}"
    # cd ${S}
}

do_install_append() {
    ### Fix up the binaries to the right location
    ### TODO: FIX
    # qemu built on host breaks do_package, remove it here (will be replaces with right qemu when do_compile is fixed
    # ERROR: objcopy failed with exit code 1 (cmd was 'arm-oe-linux-gnueabi-objcopy' --only-keep-debug '/OE/oe-core/tmp-eglibc/work/armv5te-oe-linux-gnueabi/qtbase/5.0.1-r0.0/package/usr/bin/qmake' '/OE/oe-core/tmp-eglibc/work/armv5te-oe-linux-gnueabi/qtbase/5.0.1-r0.0/package/usr/bin/.debug/qmake')
    rm -f ${D}/${bindir}/qmake
    mv ${D}${B}/mkspecs ${D}${libdir}/${QT_DIR_NAME}/mkspecs
    TMP=`dirname ${D}/${B}/mkspecs`
    while test ${TMP} != ${D}; do
        rmdir ${TMP}
        TMP=`dirname ${TMP}`;
    done
}

PACKAGES =. "${PN}-fonts "
FILES_${PN}-fonts = "${libdir}/${QT_DIR_NAME}/fonts"
