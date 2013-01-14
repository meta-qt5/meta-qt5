require qt5.inc

# FIXME!!!
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase qtjsbackend qtsvg qtxmlpatterns"

INC_PR = "r18"

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

PACKAGES = "${PN}-dbg ${PN}-dev ${PN}-staticdev ${PN}-tools ${PN}-quick ${PN}-qml ${PN}-quicktest ${PN}-quickparticles"

FILES_${PN}-tools = "${bindir}/qml*"
FILES_${PN}-quick = "${libdir}/*Quick.so.*"
FILES_${PN}-qml = "${libdir}/*Qml.so.*"
FILES_${PN}-quicktest = "${libdir}/*QuickTest.so.*"
FILES_${PN}-quickparticles = "${libdir}/*QuickParticles.so.*"

PACKAGES += "${PN}-plugins-qtquick2"
FILES_${PN}-plugins-qtquick2 = " \
                                 ${libdir}/qt5/qml/QtQuick.2/qmldir \
                                 ${libdir}/qt5/qml/QtQuick.2/*.qmltypes \
                                 ${libdir}/qt5/qml/QtQuick.2/*.so \
                               "

PACKAGES += "${PN}-plugins-folderlistmodel"
FILES_${PN}-plugins-folderlistmodel = " \
                                 ${libdir}/qt5/qml/Qt/labs/folderlistmodel/qmldir \
                                 ${libdir}/qt5/qml/Qt/labs/folderlistmodel/*.qmltypes \
                                 ${libdir}/qt5/qml/Qt/labs/folderlistmodel/*.so \
                               "

PACKAGES += "${PN}-plugins-xmllistmodel"
FILES_${PN}-plugins-xmllistmodel = " \
                                 ${libdir}/qt5/qml/QtQuick/XmlListModel/qmldir \
                                 ${libdir}/qt5/qml/QtQuick/XmlListModel/*.qmltypes \
                                 ${libdir}/qt5/qml/QtQuick/XmlListModel/*.so \
                               "

PACKAGES += "${PN}-plugins-window2"
FILES_${PN}-plugins-window2 = " \
                                 ${libdir}/qt5/qml/QtQuick/Window.2/qmldir \
                                 ${libdir}/qt5/qml/QtQuick/Window.2/*.qmltypes \
                                 ${libdir}/qt5/qml/QtQuick/Window.2/*.so \
                               "

PACKAGES += "${PN}-plugins-localstorage"
FILES_${PN}-plugins-localstorage = " \
                                 ${libdir}/qt5/qml/QtQuick/LocalStorage/qmldir \
                                 ${libdir}/qt5/qml/QtQuick/LocalStorage/*.qmltypes \
                                 ${libdir}/qt5/qml/QtQuick/LocalStorage/*.so \
                               "

PACKAGES += "${PN}-plugins-particles2"
FILES_${PN}-plugins-particles2 = " \
                                 ${libdir}/qt5/qml/QtQuick/Particles.2/qmldir \
                                 ${libdir}/qt5/qml/QtQuick/Particles.2/*.qmltypes \
                                 ${libdir}/qt5/qml/QtQuick/Particles.2/*.so \
                               "

PACKAGES += "${PN}-plugins-test"
FILES_${PN}-plugins-test = " \
                                 ${libdir}/qt5/qml/QtTest/qmldir \
                                 ${libdir}/qt5/qml/QtTest/*.qmltypes \
                                 ${libdir}/qt5/qml/QtTest/*.so \
                                 ${libdir}/qt5/qml/QtTest/*.js \
                                 ${libdir}/qt5/qml/QtTest/*.qml \
                               "

FILES_${PN}-qmlplugins-dbg = " \
                              ${libdir}/qt5/qml/*/.debug/*.so \
                              ${libdir}/qt5/qml/*/*/.debug/*.so \
                              ${libdir}/qt5/qml/*/*/*/.debug/*.so \
                             "

PACKAGES =+ "${PN}-qmlplugins-dbg"
