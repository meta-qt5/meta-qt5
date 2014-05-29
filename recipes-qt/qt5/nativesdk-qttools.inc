DEPENDS = "nativesdk-qtbase qtbase-native"

QT_MODULE = "qttools"

require nativesdk-qt5.inc

# it's already included with newer oe-core, but include it here for dylan
FILESEXTRAPATHS =. "${FILE_DIRNAME}/qttools:"

SRC_URI += "file://0001-Allow-to-build-only-lrelease-lupdate-lconvert.patch"

# older copyright year than what e.g. qtbase is using now
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de \
                    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
                    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e"

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
