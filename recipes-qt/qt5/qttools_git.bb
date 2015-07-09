require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
    file://LICENSE.LGPLv3;md5=b8c75190712063cde04e1f41b6fdad98 \
    file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

DEPENDS += "qtbase"
DEPENDS_class-target = "qtdeclarative qtxmlpatterns"

SRC_URI += " \
    file://0001-Allow-to-build-only-lrelease-lupdate-lconvert.patch \
    file://0002-assistant-help-fix-linking-of-dependent-libraries.patch \
    file://0003-add-noqtwebkit-configuration.patch \
    file://0004-linguist-tools-cmake-allow-overriding-the-location-f.patch \
"

FILES_${PN}-tools += "${datadir}${QT_DIR_NAME}/phrasebooks"
FILES_${PN}-examples = "${datadir}${QT_DIR_NAME}/examples"

PACKAGECONFIG ??= ""
PACKAGECONFIG_class-native ??= "linguistonly"
PACKAGECONFIG_class-nativesdk ??= "linguistonly"
PACKAGECONFIG[linguistonly] = ""
PACKAGECONFIG[qtwebkit] = ",,qtwebkit"

EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'qtwebkit', '', 'CONFIG+=noqtwebkit', d)}"
EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'linguistonly', 'CONFIG+=linguistonly', '', d)}"

SRCREV = "02402887562f55b822fd29a64437da3d1252fd5b"

BBCLASSEXTEND = "native nativesdk"
