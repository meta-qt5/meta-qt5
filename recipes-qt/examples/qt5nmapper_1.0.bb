SUMMARY = "QT5 NMapper application"
DESCRIPTION = "NMapper application is demonstrating the normal mapping technique using Qt5"
HOMEPAGE = "http://quitcoding.com/?page=work#cinex"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://README;beginline=47;endline=58;md5=9fb5bf76d564bc53812e40b133ff40dc"

DEPENDS = "qtdeclarative qtgraphicaleffects"

SRC_URI = "http://quitcoding.com/download/Qt5_NMapper_1.0.tgz"
SRC_URI[md5sum] = "dafc425280144d8e286788e75a0dba0f"
SRC_URI[sha256sum] = "607fbf4c448f00d3c563f9ef8a582bcb6e8fe550e80b56bf8d9127a417faa53b"

S = "${WORKDIR}/Qt5_NMapper_1.0"

require recipes-qt/qt5/qt5.inc

do_install() {
    install -d ${D}${datadir}/${P}
    install -m 0755 ${B}/Qt5_NMapper ${D}${datadir}/${P}  
    cp ${S}/Qt5_NMapper.qml ${D}${datadir}/${P}  
    cp -a ${S}/content ${D}${datadir}/${P}  
}

FILES_${PN}-dbg += "${datadir}/${P}/.debug"
FILES_${PN} += "${datadir}"

RDEPENDS_${PN} = "qtdeclarative-qmlplugins qtgraphicaleffects-qmlplugins"

