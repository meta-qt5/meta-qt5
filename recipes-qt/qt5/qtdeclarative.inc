require qt5.inc

# FIXME!!!
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase qtjsbackend"

INC_PR = "r0"

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

    ${OE_QMAKE_QMAKE} -r -d
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}

FILES_${PN}-qmlplugins = "${libdir}/qt5/qml/*"
FILES_${PN}-qmltooling = "${libdir}/qt5/plugins/qmltooling/*"
FILES_${PN}-qmltooling-dbg = "${libdir}/qt5/plugins/qmltooling/.debug/*"

PACKAGES .= "${PN}-qmlplugins ${PN}-qmltooling ${PN}-qmltooling-dbg"
