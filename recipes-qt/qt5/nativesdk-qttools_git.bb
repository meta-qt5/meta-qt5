LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & Digia-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=d87ae0d200af76dca730d911474cbe5b \
    file://LICENSE.LGPLv3;md5=ffcfac38a32c9ebdb8ff768fa1702478 \
    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

QT_MODULE = "qttools"

DEPENDS = "nativesdk-qtbase qtbase-native"

require nativesdk-qt5.inc
require qt5-git.inc

# it's already included with newer oe-core, but include it here for dylan
FILESEXTRAPATHS =. "${FILE_DIRNAME}/qttools:"

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

SRCREV = "a6ed9b418d1b4464f088b378e5bdb96ec420db6c"
