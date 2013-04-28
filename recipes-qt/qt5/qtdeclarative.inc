require qt5.inc

# FIXME!!!
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase qtjsbackend"

INC_PR = "r0"

inherit qmake5

do_configure () {
    # Avoid setting QMAKE_LINK from LD (since we want the linker to be g++)
    unset LD

    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} -r ${S}
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
    # everything except HostData and HostBinaries is prefixed with sysroot value,
    # but we cannot remove sysroot override, because that's useful for pkg-config etc
    cp -ra ${D}${STAGING_DIR_TARGET}/* ${D}
    rm -rf ${D}${STAGING_DIR_TARGET}
    # remove empty dirs
    TMP=`dirname ${D}/${STAGING_DIR_TARGET}`
    while test ${TMP} != ${D}; do
        rmdir ${TMP}
        TMP=`dirname ${TMP}`;
    done
}

FILES_${PN}-qmlplugins-dbg = " \
    ${libdir}/${QT_DIR_NAME}/qml/*/.debug \
    ${libdir}/${QT_DIR_NAME}/qml/*/*/.debug \
    ${libdir}/${QT_DIR_NAME}/qml/*/*/*/.debug \
"
FILES_${PN}-qmltooling-dbg = " \
    ${libdir}/${QT_DIR_NAME}/plugins/qmltooling/.debug/* \
"
FILES_${PN}-qmlplugins = " \
    ${libdir}/${QT_DIR_NAME}/qml/* \
"
FILES_${PN}-qmltooling = " \
    ${libdir}/${QT_DIR_NAME}/plugins/qmltooling/* \
"

PACKAGES .= " ${PN}-qmlplugins-dbg ${PN}-qmltooling-dbg ${PN}-qmlplugins ${PN}-qmltooling"
