require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0-only OR LicenseRef-The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

DEPENDS += "qtbase qtdeclarative qtmultimedia qtxmlpatterns"

SRCREV = "b52063032fda9af894ca0e582774364ce8caf0f8"
