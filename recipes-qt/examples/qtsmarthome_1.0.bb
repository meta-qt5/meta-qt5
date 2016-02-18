SUMMARY = "Qt5 smarthome QML demo application"
DESCRIPTION = "This is the Smarthome QML demo application. It shows some user interfaces for controlling an automated house"
HOMEPAGE = "http://www.basyskom.com/news/143-demos-qt5-port.html"
LICENSE = "LGPLv2.1+ & GFDL-1.2"
LIC_FILES_CHKSUM = "file://COPYING.DOC;md5=ad1419ecc56e060eccf8184a87c4285f \
                    file://COPYING.LIB;md5=2d5025d4aa3495befef8f17206a5b0a1"

DEPENDS = "qtdeclarative qtgraphicaleffects"

SRC_URI = "http://share.basyskom.com/demos/smarthome_src.tar.gz"
SRC_URI[md5sum] = "883b0376239baec20ebec072e938a995"
SRC_URI[sha256sum] = "fceaa813c33e462bad6c0383eaef81a6f6e586c15d1fa73898173b517fc1cda6"

S = "${WORKDIR}/smarthome_src"

require recipes-qt/qt5/qt5.inc

do_install() {
    install -d ${D}${datadir}/${P}
    install -m 0755 ${B}/smarthome ${D}${datadir}/${P}
    cp -R --no-dereference --preserve=mode,links ${S}/qml ${D}${datadir}/${P}
    cp -R --no-dereference --preserve=mode,links ${S}/components ${D}${datadir}/${P}
}

FILES_${PN}-dbg += "${datadir}/${P}/.debug"
FILES_${PN} += "${datadir}"

RDEPENDS_${PN} = "qtdeclarative-qmlplugins qtgraphicaleffects-qmlplugins"
