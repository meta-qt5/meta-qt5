require qt5.inc
require qt5-git.inc

LICENSE = "(LGPL-2.1 & Digia-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
    file://LICENSE.LGPLv3;md5=c4fe8c6de4eef597feec6e90ed62e962 \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
"

DEPENDS += "qtbase qttools-native"

PACKAGES =. " \
    ${PN}-assistant \
    ${PN}-designer \
    ${PN}-linguist \
    ${PN}-qmlviewer \
    ${PN}-qtconnectivity \
    ${PN}-qtmultimedia \
    ${PN}-qtlocation \
    ${PN}-qtdeclarative \
    ${PN}-qtquickcontrols \
    ${PN}-qtxmlpatterns \
    ${PN}-qtconfig \
    ${PN}-qtquick1 \
    ${PN}-qtscript \
    ${PN}-qtbase \
    ${PN}-qthelp \
    ${PN}-qt \
"

FILES_${PN}-assistant = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/assistant_*.qm \
"

FILES_${PN}-designer = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/designer_*.qm \
"

FILES_${PN}-linguist = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/linguist_*.qm \
"

FILES_${PN}-qmlviewer = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qmlviewer_*.qm \
"

FILES_${PN}-qtconnectivity = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtconnectivity_*.qm \
"

FILES_${PN}-qtmultimedia = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtmultimedia_*.qm \
"

FILES_${PN}-qtlocation = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtlocation_*.qm \
"

FILES_${PN}-qtdeclarative = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtdeclarative_*.qm \
"

FILES_${PN}-qtquickcontrols = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtquickcontrols_*.qm \
"

FILES_${PN}-qtxmlpatterns = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtxmlpatterns_*.qm \
"

FILES_${PN}-qtconfig = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtconfig_*.qm \
"

FILES_${PN}-qtquick1 = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtquick1_*.qm \
"

FILES_${PN}-qtscript = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtscript_*.qm \
"

FILES_${PN}-qtbase = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtbase_*.qm \
"

FILES_${PN}-qthelp = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qt_help_*.qm \
"

FILES_${PN}-qt = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qt_*.qm \
"

SRCREV = "5e65c6fcdbbc75040426c8239c686f7b1ea689fd"
