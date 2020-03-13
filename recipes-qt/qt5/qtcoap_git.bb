require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

PACKAGECONFIG ?= "qtdeclarative"
PACKAGECONFIG[qtdeclarative] = ",,qtdeclarative"

DEPENDS += "qtbase"

SRCREV = "02dbe3346c414d9c606acd5bc799a79a67ad3c1c"
