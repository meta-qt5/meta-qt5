require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
"

DEPENDS += "qtbase qttools-native"

do_install_append() {
    # remove qtquick1 translations - qtquick1 is gone
    for transfile in `find ${D}/${OE_QMAKE_PATH_TRANSLATIONS} -name qtquick1_*.qm -o -name qt_*.qm ! -name qt_help_*.qm`; do
        rm $transfile
    done
}

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
    ${PN}-qtscript \
    ${PN}-qtserialport \
    ${PN}-qtbase \
    ${PN}-qthelp \
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

SRCREV = "ed5110171ca294207f341f46610c716170c06282"
