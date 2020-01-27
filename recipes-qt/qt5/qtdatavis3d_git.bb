require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

DEPENDS += "qtbase qtdeclarative qtmultimedia qtxmlpatterns"

SRCREV = "6b15c2bee08a0477e718a407fdf81917a064c1ca"

QT_MODULE_BRANCH_PARAM = "nobranch=1"
