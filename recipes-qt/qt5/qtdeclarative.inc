require qt5.inc

# FIXME!!!
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase qtjsbackend qtsvg qtxmlpatterns"

INC_PR = "r14"

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

do_install_append() {
    ### Fix up the libraries to the right location
    ### TODO: FIX
    install -d ${D}${libdir}/
    mv ${D}/${STAGING_LIBDIR}/* ${D}${libdir}/
    rm -rf ${D}/${STAGING_LIBDIR}/
}

FILES_${PN}-qmlplugins = " \
                          ${libdir}/qt5/qml \
                         "

FILES_${PN}-qmlplugins-dbg = " \
                              ${libdir}/qt5/qml/*/.debug/*.so \
                              ${libdir}/qt5/qml/*/*/.debug/*.so \
                              ${libdir}/qt5/qml/*/*/*/.debug/*.so \
                             "

FILES_${PN}-qmltooling = "${libdir}/qt5/plugins/qmltooling/*.so"
FILES_${PN}-qmltooling-dbg = "${libdir}/qt5/plugins/qmltooling/.debug/*.so"

PACKAGES =+ "${PN}-qmlplugins-dbg ${PN}-qmlplugins ${PN}-qmltooling ${PN}-qmltooling-dbg"
