require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=d3bb688e8d381a9fa5ee9063114b366d \
    file://LICENSE.LGPLv3;md5=3fd06ee442011942b532cc6dedb0b39c \
    file://LICENSE.GPLv3;md5=a40e2bb02b1ac431f461afd03ff9d1d6 \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

DEPENDS += "qtbase qtdeclarative qtxmlpatterns"

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

SRCREV = "05206b44cf25ca6a895cc9a8716f2b7f2f14191f"

BBCLASSEXTEND = "native nativesdk"
