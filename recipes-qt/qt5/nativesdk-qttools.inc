DEPENDS = "nativesdk-qtbase qtbase-native"

QT_MODULE = "qttools"

require nativesdk-qt5.inc

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
