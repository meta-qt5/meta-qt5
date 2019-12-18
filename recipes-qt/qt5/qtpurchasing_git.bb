require qt5.inc
require qt5-git.inc

HOMEPAGE = "http://www.qt.io"
LICENSE = "Apache-2.0 & BSD & ( LGPL-3.0 | GPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=b8c75190712063cde04e1f41b6fdad98 \
    file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb \
"

DEPENDS += "qtbase qtdeclarative"

SRCREV = "0c5493b7e31f96277851667a9c0c04f0d7c9d750"
