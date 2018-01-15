require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=fb91571854638f10b2e5f36562661a5a \
    file://LICENSE.LGPLv3;md5=a909b94c1c9674b2aa15ff03a86f518a \
    file://LICENSE.GPLv3;md5=bfdd8aa675169432d6d9b63d056de148 \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

DEPENDS += "qtbase qtdeclarative qtxmlpatterns"

# Patches from https://github.com/meta-qt5/qttools/commits/b5.6
# 5.6.meta-qt5.1
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

EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'qtwebkit', '', 'CONFIG+=noqtwebkit', d)}"
EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'linguistonly', 'CONFIG+=linguistonly', '', d)}"

SRCREV = "56dbabb7a64a4d416e8cf7b61e068208fd032355"

BBCLASSEXTEND = "native nativesdk"
