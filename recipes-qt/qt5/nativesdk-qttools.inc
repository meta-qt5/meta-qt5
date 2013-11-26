DEPENDS = "nativesdk-qtbase qtbase-native"

QT_MODULE = "qttools"

require nativesdk-qt5.inc

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
    oe_runmake install INSTALL_ROOT=${D}
}
