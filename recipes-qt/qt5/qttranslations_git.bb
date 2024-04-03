require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
"

DEPENDS += "qtbase qttools-native"

do_install:append() {
    # remove qtquick1 translations - qtquick1 is gone
    for transfile in `find ${D}/${OE_QMAKE_PATH_TRANSLATIONS} -name qt_*.qm ! -name qt_help_*.qm`; do
        rm $transfile
    done
}

PACKAGES =. " \
    ${PN}-assistant \
    ${PN}-designer \
    ${PN}-linguist \
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

FILES:${PN}-assistant = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/assistant_*.qm \
"

FILES:${PN}-designer = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/designer_*.qm \
"

FILES:${PN}-linguist = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/linguist_*.qm \
"

FILES:${PN}-qtconnectivity = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtconnectivity_*.qm \
"

FILES:${PN}-qtmultimedia = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtmultimedia_*.qm \
"

FILES:${PN}-qtlocation = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtlocation_*.qm \
"

FILES:${PN}-qtdeclarative = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtdeclarative_*.qm \
"

FILES:${PN}-qtquickcontrols = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtquickcontrols_*.qm \
"

FILES:${PN}-qtquickcontrols2 = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtquickcontrols2_*.qm \
"

FILES:${PN}-qtwebsockets = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtwebsockets_*.qm \
"

FILES:${PN}-qtwebengine = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtwebengine_*.qm \
"

FILES:${PN}-qtxmlpatterns = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtxmlpatterns_*.qm \
"

FILES:${PN}-qtscript = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtscript_*.qm \
"

FILES:${PN}-qtserialport = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtserialport_*.qm \
"

FILES:${PN}-qtbase = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qtbase_*.qm \
"

FILES:${PN}-qthelp = " \
    ${OE_QMAKE_PATH_TRANSLATIONS}/qt_help_*.qm \
"

SRCREV = "40aabebd04a30ccef374bf20a7bfaa1d8d665b7f"
