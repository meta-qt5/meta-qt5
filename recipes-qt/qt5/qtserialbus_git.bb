require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & (LGPL-3.0 | GPL-2.0+) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=e0459b45c5c4840b353141a8bbed91f0 \
    file://LICENSE.GPLv2;md5=c96076271561b0e3785dad260634eaa8 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.FDL;md5=f70ee9a6c44ae8917586fea34dff0ab5 \
"

DEPENDS += "qtbase qtserialport"

SRCREV = "d5d35d64d06729322c80327de40badaf553df87e"
