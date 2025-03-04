require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD-3-Clause & ( GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 ) & ( GPL-2.0-or-later | LGPL-3.0-only ) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

DEPENDS += "qtbase"

SRCREV = "0c61151bf14e5b4c74187608b6b47b9d0d6ca745"
