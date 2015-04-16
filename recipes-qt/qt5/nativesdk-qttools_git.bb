LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & Digia-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
    file://LICENSE.LGPLv3;md5=c4fe8c6de4eef597feec6e90ed62e962 \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

QT_MODULE = "qttools"

DEPENDS = "nativesdk-qtbase qtbase-native"

require nativesdk-qt5.inc
require qt5-git.inc

SRC_URI += "file://0001-Allow-to-build-only-lrelease-lupdate-lconvert.patch"

PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"

FILES_${PN}-dbg = " \
    ${OE_QMAKE_PATH_BINS}/.debug \
"

FILES_${PN} = " \
    ${OE_QMAKE_PATH_BINS}/* \
"

do_configure() {
    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} -r ${S} CONFIG+=linguistonly
}

do_install() {
    # Fix install paths for all
    find -name "Makefile*" | xargs sed -i "s,(INSTALL_ROOT)${STAGING_DIR_HOST},(INSTALL_ROOT),g"

    oe_runmake install INSTALL_ROOT=${D}

    # remove things unused in nativesdk
    rm -rf ${D}${libdir}
}

SRCREV = "33c65366a7c3901d2aecfde3dbc485e1eac5c10c"
