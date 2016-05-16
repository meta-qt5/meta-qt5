require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0 | The-Qt-Company-TPLA-2.4"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.PREVIEW.COMMERCIAL;md5=c458c2ae1b463cca5219eaee54f6287e \
"

DEPENDS += "qtbase qtdeclarative"

SRCREV = "13a6c51f26128015c0a3c335bdae676b46df6ed6"
