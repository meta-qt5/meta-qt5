require qt5.inc
require qt5-git.inc

LICENSE = "LGPL-3.0 | GPL-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=3dcffeed712d3c916f9a2d9135703aff \
    file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb \
    file://LICENSE.GPL;md5=05832301944453ec79e40ba3c3cfceec \
"

DEPENDS = "qtdeclarative"

FILES_${PN}-qmlplugins += " \
    ${OE_QMAKE_PATH_QML}/*/*/*.bez \
    ${OE_QMAKE_PATH_QML}/*/*/*.obj \
"

SRCREV = "0bcb6600db2a74ee8c8b51207d3ba15e4973aa95"
