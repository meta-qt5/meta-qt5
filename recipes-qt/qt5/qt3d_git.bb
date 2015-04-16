require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & LGPL-3.0 & DIGIA-TPLA-2.4 | GPL-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL;md5=05832301944453ec79e40ba3c3cfceec\
"

DEPENDS = "qtdeclarative"

FILES_${PN}-qmlplugins += " \
    ${OE_QMAKE_PATH_QML}/*/*/*.bez \
    ${OE_QMAKE_PATH_QML}/*/*/*.obj \
"

SRCREV = "a3b99941751598fb734b189333d5020666df2b2c"
