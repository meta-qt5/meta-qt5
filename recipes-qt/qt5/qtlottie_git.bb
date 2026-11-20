require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0-only AND LicenseRef-The-Qt-Company-GPL-Exception-1.0 OR LicenseRef-The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
"

DEPENDS += "qtbase qtdeclarative"

SRCREV = "5f1be885cf4ee0f1eb5400bf0c5d138818fc01d0"
