SUMMARY = "QT5 QUItBattery"
DESCRIPTION = "This package contains QUItBatteryComponent with few usage examples"
HOMEPAGE = "http://quitcoding.com/?page=work#cinex"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://README;beginline=37;endline=46;md5=6df4bcb7f9092d42a84f32eacb61bdc4"

DEPENDS = "qtdeclarative qtgraphicaleffects"

SRC_URI = "http://quitcoding.com/download/QUItBattery_1.0.0.tar.gz"
SRC_URI[md5sum] = "7c7babc1086491b116b01d154b6163fd"
SRC_URI[sha256sum] = "38dcb7630553c397f9d8a53c6411b1a6237956ed8dd4859e01487b1dd8f37873"

S = "${WORKDIR}/QUItBattery_1.0.0"

require recipes-qt/qt5/qt5.inc

do_install() {
    install -d ${D}${datadir}/${P}
    install -m 0755 ${B}/QUItBattery ${D}${datadir}/${P}
    cp -R --no-dereference --preserve=mode,links ${S}/qml ${D}${datadir}/${P}
}

FILES_${PN}-dbg += "${datadir}/${P}/.debug"
FILES_${PN} += "${datadir}"

RDEPENDS_${PN} = "qtdeclarative-qmlplugins qtgraphicaleffects-qmlplugins"
