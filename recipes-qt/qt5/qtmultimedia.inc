require qt5.inc

# FIXME!!!
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase qtdeclarative gstreamer alsa-lib "

INC_PR = "r6"

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
    install -d ${D}${libdir}
    mv ${D}/${STAGING_LIBDIR}/* ${D}${libdir}/
    rm -rf ${D}/${STAGING_LIBDIR}/
}

FILES_${PN}-plugins = "/usr/lib/qt5/plugins/*/*.so"
FILES_${PN}-plugins-dbg = "/usr/lib/qt5/plugins/*/.debug"

PACKAGES += "${PN}-qmlplugins-dbg ${PN}-qmlplugins"

FILES_${PN}-qmlplugins = " \
                           ${libdir}/qt5/qml/QtMultimedia/qmldir \
                           ${libdir}/qt5/qml/QtMultimedia/plugins.qmltypes \
                           ${libdir}/qt5/qml/QtMultimedia/Video.qml \
                           ${libdir}/qt5/qml/QtMultimedia/libdeclarative_multimedia.so"

FILES_${PN}-qmlplugins-dbg = "${libdir}/qt5/qml/QtMultimedia/.debug"
