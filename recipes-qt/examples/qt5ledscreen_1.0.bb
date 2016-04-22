SUMMARY = "QT5 LedScreen"
DESCRIPTION = "This is LedScreen Qt5 QML component"
HOMEPAGE = "http://quitcoding.com/?page=work#cinex"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://README.txt;md5=fe07f4a0be40fe88f8c7ceaca63a28b5"

DEPENDS = "qtdeclarative qtgraphicaleffects"

SRC_URI = "http://quitcoding.com/download/ledscreen_1.0.tgz"
SRC_URI[md5sum] = "8fc482d5a8b16f43f022c9a282f305b8"
SRC_URI[sha256sum] = "fa4759b70a5fa148a901a3f9a659f7bd2503d73774022012bded880dffa0d15c"

S = "${WORKDIR}/ledscreen_1.0"

do_install() {
    install -d ${D}${datadir}/${P}
    #install -m 0755 ${B}/QUItBattery ${D}${datadir}/${P}
    cp -R --no-dereference --preserve=mode,links ${S}/* ${D}${datadir}/${P}
}

FILES_${PN}-dbg += "${datadir}/${P}/.debug"
FILES_${PN} += "${datadir}"

RDEPENDS_${PN} = "qtdeclarative-qmlplugins qtgraphicaleffects-qmlplugins"
