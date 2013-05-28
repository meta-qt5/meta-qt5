require qt5-native.inc

LICENSE = "BSD & LGPL-2.1 | GPL-3.0"
LIC_FILES_CHKSUM = "file://${S}/src/compositor/compositor_api/qwaylandsurfacenode.cpp;endline=40;md5=e38eccd7e9a6a149c9e31b6fb47daac1 \
                    file://${S}/src/compositor/compositor_api/qwaylandcompositor.cpp;endline=39;md5=8897abe415f27031e4237f09db7b9a36 \
"

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
