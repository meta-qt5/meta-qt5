require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & LGPL-3.0 | GPL-3.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.LGPLv3;md5=8ba7f2099d17d636d5fcc8303bb17587 \
    file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb \
"

DEPENDS += "qtdeclarative"

SRCREV = "1583f8b2ee56726f7920524b85636bb831c2f0d0"
