require qt5.inc
require qt5-git.inc

LICENSE = "(LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=4bfd28363f541b10d9f024181b8df516 \
    file://LICENSE.LGPLv3;md5=e0459b45c5c4840b353141a8bbed91f0 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
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
    ${PN}-qtquickcontrols2 \
    ${PN}-qtwebsockets \
    ${PN}-qtwebengine \
    ${PN}-qtxmlpatterns \
    ${PN}-qtconfig \
    ${PN}-qtquick1 \
    ${PN}-qtscript \
    ${PN}-qtserialport \
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

FILES_${PN}-qtquickcontrols2 = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtquickcontrols2_*.qm \
"

FILES_${PN}-qtwebsockets = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtwebsockets_*.qm \
"

FILES_${PN}-qtwebengine = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtwebengine_*.qm \
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

FILES_${PN}-qtserialport = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtserialport_*.qm \
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

SRCREV = "4925f90e9ceb0b4081649d1fc1eec10beb65f722"
