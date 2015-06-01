DEPENDS = "qtbase-native"

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & Digia-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=d87ae0d200af76dca730d911474cbe5b \
    file://LICENSE.LGPLv3;md5=ffcfac38a32c9ebdb8ff768fa1702478 \
    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

QT_MODULE = "qttools"

require qt5-native.inc
require qt5-git.inc

SRC_URI += "file://0001-Allow-to-build-only-lrelease-lupdate-lconvert.patch"

do_configure() {
    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} -r ${S} CONFIG+=linguistonly
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}

SRCREV = "a6ed9b418d1b4464f088b378e5bdb96ec420db6c"
