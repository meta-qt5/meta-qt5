require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
"

DEPENDS += "qtbase qtdeclarative qt3d"

QT_MODULE_BRANCH = "2.2"
QT_GIT_PROJECT = "qt3dstudio"
PV = "2.2+git${SRCPV}"

SRCREV = "8cdb99c910f2f57972b652c3fb8d520ba615f110"
