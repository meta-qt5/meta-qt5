require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & LGPL-3.0 | GPL-3.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.LGPLv3;md5=a37e6cd7102174853307e03e6edc5f30 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
"

DEPENDS += "qtdeclarative"

FILES_${PN}-qmlplugins += "${libdir}/qt5/qml/Qt"

SRCREV = "eed9b9dd83f0db9cbfe794072d8f5147e3d1234a"
