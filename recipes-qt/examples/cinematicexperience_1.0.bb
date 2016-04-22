SUMMARY = "Qt5 technology demo"
DESCRIPTION = "Cinematic Experience collects many of the new Qt5 QtQuick 2.0 features into the same UX demo application. It uses particles, sprites, path animation, custom shaders etc. features which Qt5 introduces for QML UIs."
HOMEPAGE = "http://quitcoding.com/?page=work#cinex"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://README;beginline=38;endline=50;md5=51babd597624b70752069953876aaa18"

SRC_URI = "http://quitcoding.com/download/Qt5_CinematicExperience_rpi_1.0.tgz"
SRC_URI += "file://fix_qt5_3_compatibility.patch"

SRC_URI[md5sum] = "935a5db0a6b2a72c67236e72f52be7d1"
SRC_URI[sha256sum] = "0dd602983ced5f7c0cfd5ad0fbfe2b0b7e3c9ff715e4ef23eef818ccc2b6c60b"

S = "${WORKDIR}/Qt5_CinematicExperience_rpi_${PV}/"

# other version available for small screens
#SRC_URI = "http://quitcoding.com/download/Qt5_CinematicExperience_1.0.tgz"
#SRC_URI[md5sum] = "1c4f9bf5411c985fc5d3dbfc5d826a29"
#SRC_URI[sha256sum] = "0e547e0259667915a24e84ade5efdcd0c553f81786734452c2c8dbce19a19f44"
#S = "${WORKDIR}/Qt5_CinematicExperience_${PV}/"

DEPENDS = "qtdeclarative qtgraphicaleffects"
RDEPENDS_${PN} = "qtdeclarative-qmlplugins qtgraphicaleffects-qmlplugins"

require recipes-qt/qt5/qt5.inc

do_install() {
    install -d ${D}${datadir}/${P}
    install -m 0755 ${B}/Qt5_CinematicExperience ${D}${datadir}/${P}
    cp -R --no-dereference --preserve=mode,links ${S}/content ${D}${datadir}/${P}
    install -m 0644 ${S}/Qt5_CinematicExperience.qml ${D}${datadir}/${P}

    install -d ${D}${bindir}
    echo "#!/bin/sh" > ${D}${bindir}/Qt5_CinematicExperience
    echo "export QML_IMPORT_PATH=${datadir}/${P}" >> ${D}${bindir}/Qt5_CinematicExperience
    echo "export QML2_IMPORT_PATH=${datadir}/${P}" >> ${D}${bindir}/Qt5_CinematicExperience
    echo "${datadir}/${P}/Qt5_CinematicExperience \$* " >> ${D}${bindir}/Qt5_CinematicExperience
    chmod +x ${D}${bindir}/Qt5_CinematicExperience
}

FILES_${PN}-dbg += "${datadir}/${P}/.debug"
FILES_${PN} += "${datadir}"
