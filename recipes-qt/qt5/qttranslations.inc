require qt5.inc

DEPENDS += "qtbase"

# older copyright year than what e.g. qtbase is using now
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de \
                    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6"

PACKAGES =. "${PN}-assistant \
             ${PN}-designer \
             ${PN}-linguist \
             ${PN}-qmlviewer \
             ${PN}-qtconnectivity \
             ${PN}-qtmultimedia \
             ${PN}-qtlocation \
             ${PN}-qtdeclarative \
             ${PN}-qtxmlpatterns \
             ${PN}-qtconfig \
             ${PN}-qtquick1 \
             ${PN}-qtscript \
             ${PN}-qtbase \
             ${PN}-qthelp \
             ${PN}-qt \
             "

FILES_${PN}-translations-assistant = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/assistant_*.qm \
            "

FILES_${PN}-translations-designer = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/designer_*.qm \
            "

FILES_${PN}-translations-linguist = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/linguist_*.qm \
            "

FILES_${PN}-translations-qmlviewer = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qmlviewer_*.qm \
            "

FILES_${PN}-translations-qtconnectivity = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qtconnectivity_*.qm \
            "

FILES_${PN}-translations-qtmultimedia = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qtmultimedia_*.qm \
            "

FILES_${PN}-translations-qtlocation = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qtlocation_*.qm \
            "

FILES_${PN}-translations-qtdeclarative = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qtdeclarative_*.qm \
            "

FILES_${PN}-translations-qtxmlpatterns = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qtxmlpatterns_*.qm \
            "

FILES_${PN}-translations-qtconfig = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qtconfig_*.qm \
            "

FILES_${PN}-translations-qtquick1 = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qtquick1_*.qm \
            "

FILES_${PN}-translations-qtscript = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qtscript_*.qm \
            "

FILES_${PN}-translations-qtbase = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qtbase_*.qm \
            "

FILES_${PN}-translations-qthelp = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_help_*.qm \
            "

FILES_${PN}-translations-qt = " \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_ar.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_cs.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_da.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_de.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_es.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_fa.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_fi.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_fr.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_gl.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_he.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_hu.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_it.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_ja.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_ko.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_lt.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_pl.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_pt.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_ru.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_sk.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_sl.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_sv.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_uk.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_zh_CN.qm \
                ${OE_QMAKE_PATH_TRANSLATIONS}/qt_zh_TW.qm \
            "