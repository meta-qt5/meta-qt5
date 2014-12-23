DEPENDS = "qtbase-native"

QT_MODULE = "qttools"

require qt5-native.inc

SRC_URI += "file://0001-Allow-to-build-only-lrelease-lupdate-lconvert.patch"

do_configure() {
    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} -r ${S} CONFIG+=linguistonly
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}
