LICENSE = "BSD-3-Clause & ( GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 ) & ( GPL-2.0-or-later | LGPL-3.0-only ) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

require qt5.inc
require qt5-git.inc

DEPENDS += "qtbase qtdeclarative qtremoteobjects-native"

# Patches from https://github.com/meta-qt5/qtremoteobjects/commits/b5.15
# 5.15.meta-qt5.1
SRC_URI += " \
    file://0001-Allow-a-tools-only-build.patch \
    file://0002-cmake-Use-OE_QMAKE_PATH_EXTERNAL_HOST_BINS.patch \
"

PACKAGECONFIG ??= ""
PACKAGECONFIG:class-native ??= "tools-only"
PACKAGECONFIG:class-nativesdk ??= "tools-only"
PACKAGECONFIG[tools-only] = "CONFIG+=tools-only"

EXTRA_QMAKEVARS_PRE += "${PACKAGECONFIG_CONFARGS}"

SRCREV = "1d8e4d3645c437cb0a5e5cdb4862949190f89259"

BBCLASSEXTEND += "native nativesdk"
