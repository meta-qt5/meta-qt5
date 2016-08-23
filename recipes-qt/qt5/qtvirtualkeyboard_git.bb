require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

DEPENDS += "qtbase qtdeclarative qtmultimedia qtquickcontrols qtsvg qtxmlpatterns"

SRCREV = "626e78c9660cff063e1f9370538b5a424631571c"
