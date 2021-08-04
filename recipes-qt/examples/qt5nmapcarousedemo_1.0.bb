SUMMARY = "NMap Carousel application is demonstrating the normal mapping technique using Qt5"
DESCRIPTION = "Normal mapping is used for making the icons appear 3D with lighting and shadows"
HOMEPAGE = "http://quitcoding.com/?page=work#cinex"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://README;beginline=44;endline=55;md5=3a9db934c393a0cf198cbe7e73ebec86"

DEPENDS = "qtdeclarative qtgraphicaleffects"

SRC_URI = "http://quitcoding.com/download/Qt5_NMap_CarouselDemo_1.0.tgz"
SRC_URI[md5sum] = "c1b4568cdbb6b3af4ca10c5a90aa8128"
SRC_URI[sha256sum] = "445da212074a10a432f4508d125814212bbe7a967bfa47b015b92dfac6bfd65f"

S = "${WORKDIR}/Qt5_NMap_CarouselDemo_1.0"

require recipes-qt/qt5/qt5.inc

do_install() {
    install -d ${D}${datadir}/${P}
    install -m 0755 ${B}/Qt5_NMap_CarouselDemo ${D}${datadir}/${P}
    cp ${S}/Qt5_NMap_CarouselDemo.qml ${D}${datadir}/${P}
    cp -R --no-dereference --preserve=mode,links ${S}/content ${D}${datadir}/${P}
}

FILES:${PN} += "${datadir}"

RDEPENDS:${PN} = "qtdeclarative-qmlplugins qtgraphicaleffects-qmlplugins"
