require qt5.inc
require qt5-git.inc

LICENSE = "BSD-3-Clause AND GFDL-1.3-no-invariants-only AND GPL-3.0-only OR LicenseRef-The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

PACKAGECONFIG ?= "qtdeclarative"
PACKAGECONFIG[qtdeclarative] = ",,qtdeclarative"

DEPENDS += "qtbase"

QT_MODULE_BRANCH = "5.15.2"
SRCREV = "628d3b8abd47ffde45252cf6591ed10ec2fa28ac"
