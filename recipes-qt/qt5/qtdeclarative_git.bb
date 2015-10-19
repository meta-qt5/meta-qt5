require qt5.inc
require qt5-git.inc

# There are no LGPLv3-only licensed files in this component.
LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
    file://LICENSE.LGPLv3;md5=b8c75190712063cde04e1f41b6fdad98 \
    file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

DEPENDS += "qtbase"

SRC_URI += " \
    file://0001-qmltestexample-fix-link.patch \
"

EXTRA_OEMAKE += "QMAKE_SYNCQT=${STAGING_BINDIR_NATIVE}${QT_DIR_NAME}/syncqt"

PACKAGECONFIG ??= "qtxmlpatterns"
PACKAGECONFIG[qtxmlpatterns] = ",,qtxmlpatterns"

do_configure_prepend() {
    # disable qtxmlpatterns test if it isn't enabled by PACKAGECONFIG
    sed -e 's/^\(qtHaveModule(xmlpatterns)\)/OE_QTXMLPATTERNS_ENABLED:\1/' -i ${S}/src/imports/imports.pro
    sed -e 's/^\(!qtHaveModule(xmlpatterns)\)/!OE_QTXMLPATTERNS_ENABLED|\1/' -i ${S}/tests/auto/quick/quick.pro

    #set the path for syncqt properly
    echo "QT_TOOL.syncqt.binary = \"${STAGING_BINDIR_NATIVE}${QT_DIR_NAME}/syncqt\"" > ${B}/.qmake.cache
}

EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'qtxmlpatterns', 'CONFIG+=OE_QTXMLPATTERNS_ENABLED', '', d)}"

SRCREV = "3e9f61f305dc4c988e6f2718df56df80f639734e"

BBCLASSEXTEND =+ "native nativesdk"
