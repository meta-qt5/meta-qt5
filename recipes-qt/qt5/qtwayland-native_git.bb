require qt5-native.inc
require qt5-git.inc

# There are no LGPLv3-only licensed files in this component.
LICENSE = "BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
    file://LICENSE.LGPLv3;md5=b8c75190712063cde04e1f41b6fdad98 \
    file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

DEPENDS = "qtbase-native wayland-native"

SRC_URI += " \
    file://0001-Install-the-qtwaylandscanner-tool-to-the-native-side.patch \
"

do_configure() {
    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} ${OE_QMAKE_QTCONF} -r ${S}/src/qtwaylandscanner
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}

SRCREV = "c07dc0c5cd138db2f222c6c58bc5778532cecf42"
