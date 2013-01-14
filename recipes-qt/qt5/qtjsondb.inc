require qt5.inc

# FIXME!!!
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase qtdeclarative icu"

INC_PR = "r4"

inherit qmake5

SRC_URI += " \
    file://module_qmake.conf \
    "

export QT_CONF_PATH="${WORKDIR}/qt.conf"

do_configure () {
    # Avoid setting QMAKE_LINK from LD (since we want the linker to be g++)
    unset LD

    # This should not be needed. Perhaps the lack of this file is an indication
    # of an error on the native recipe...
    cp ${WORKDIR}/module_qmake.conf ${S}/.qmake.conf
    echo "" >> ${S}/.qmake.conf
    echo "load(qt_build_config)" >> ${S}/.qmake.conf

    ${OE_QMAKE_QMAKE} -r -d
}

do_install_append() {
    ### Fix up the libraries to the right location
    ### TODO: FIX
    install -d ${D}${libdir}
    mv ${D}/${STAGING_LIBDIR}/* ${D}${libdir}/
    rm -rf ${D}/${STAGING_LIBDIR}/
#    install -d ${D}${bindir}
#    mv ${D}/${STAGING_BINDIR}/* ${D}${bindir}/
#    rm -rf ${D}/${STAGING_BINDIR}/
}

PACKAGES += "${PN}-qmlplugins ${PN}-qmlplugins-dbg"

FILES_${PN}-qmlplugins = "${libdir}/qt5/qml/QtJsonDb/qmldir ${libdir}/qt5/qml/QtJsonDb/libjsondbplugin.so"
FILES_${PN}-qmlplugins-dbg = "${libdir}/qt5/qml/QtJsonDb/.debug"
