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
}

FILES_${PN}-qmlplugins = "${libdir}/qt5/qml/*"
FILES_${PN}-qmltooling = "${libdir}/qt5/plugins/qmltooling/*"
FILES_${PN}-qmltooling-dbg = "${libdir}/qt5/plugins/qmltooling/.debug/*"

PACKAGES .= "${PN}-qmlplugins ${PN}-qmltooling ${PN}-qmltooling-dbg"
