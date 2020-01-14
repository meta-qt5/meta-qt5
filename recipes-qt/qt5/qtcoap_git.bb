require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

PACKAGECONFIG ?= "qtdeclarative"
PACKAGECONFIG[qtdeclarative] = ",,qtdeclarative"

DEPENDS += "qtbase"

SRCREV = "e04fdb6d20ad06141f14bd18e7f1a13c941be35e"
