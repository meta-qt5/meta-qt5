SUMMARY = "Qt Simple Image Viewer"
DESCRIPTION = "A simple image viewer using a mix of C++ and qml code for demonstration."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=11c7965a9059e287de5d93b98adf6d1a"
DEPENDS = "qtdeclarative"

SRCREV = "7b9810b0f02f9ac74fae3ead6e2e9fb5c1382173"
SRC_URI = "git://code.ossystems.com.br/qt/qsiv;protocol=http;branch=master"

S = "${WORKDIR}/git"

inherit qmake5

EXTRA_QMAKEVARS_PRE += "target.path=${libdir}/${P}"

do_install:append() {
    install -d ${D}${bindir}
    echo "#!/bin/sh" > ${D}${bindir}/qsiv
    echo "export QML_IMPORT_PATH=${libdir}/${P}/qml/qsiv" >> ${D}${bindir}/qsiv
    echo "export QML2_IMPORT_PATH=${libdir}/${P}/qml/qsiv" >> ${D}${bindir}/qsiv
    echo "${libdir}/${P}/qsiv \$* " >> ${D}${bindir}/qsiv
    chmod +x ${D}${bindir}/qsiv
}

FILES:${PN} += "${libdir}/${P}"
RDEPENDS:${PN} += "qtdeclarative-qmlplugins"
