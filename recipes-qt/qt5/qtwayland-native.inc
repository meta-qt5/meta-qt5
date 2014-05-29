require qt5-native.inc

QT_MODULE = "${BPN}"

DEPENDS = "qtbase-native wayland-native"

SRC_URI += " \
    file://0001-Install-the-qtwaylandscanner-tool-to-the-native-side.patch \
"

do_configure() {
    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} -r ${S}/src/qtwaylandscanner
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}

# older copyright year than what e.g. qtbase is using now
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de \
                    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
                    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e"
