require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & LGPL-3.0 | GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.LGPLv3;md5=382747d0119037529ec2b98b24038eb0 \
    file://LICENSE.GPLv3;md5=dce746aa5261707df6d6999ab9958d8b \
"

DEPENDS += "qtdeclarative"

SRCREV = "e693ad2c4ae22f8813befd5fe825c855537497ed"
