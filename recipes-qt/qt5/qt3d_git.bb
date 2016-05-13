require qt5.inc
require qt5-git.inc

LICENSE = "LGPL-3.0 | GPL-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=3dcffeed712d3c916f9a2d9135703aff \
    file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb \
    file://LICENSE.GPL;md5=05832301944453ec79e40ba3c3cfceec \
"

DEPENDS += "qtbase"
DEPENDS_class-target += "qtdeclarative qt3d-native"

SRC_URI += " \
    file://0001-Allow-a-tools-only-build.patch \
    "

PACKAGECONFIG ??= ""
PACKAGECONFIG_class-native ??= "tools-only"
PACKAGECONFIG_class-nativesdk ??= "tools-only"
PACKAGECONFIG[tools-only] = "CONFIG+=tools-only"

EXTRA_QMAKEVARS_PRE += "${PACKAGECONFIG_CONFARGS}"

FILES_${PN}-qmlplugins += " \
    ${OE_QMAKE_PATH_QML}/*/*/*.bez \
    ${OE_QMAKE_PATH_QML}/*/*/*.obj \
"

SRCREV = "faf56f50608f9391d2a73ed7c61bfdd9c2afab78"

BBCLASSEXTEND += "native nativesdk"
