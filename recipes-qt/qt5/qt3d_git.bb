require qt5.inc
require qt5-git.inc

LICENSE = "LGPL-3.0-only | GPL-2.0-only | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=8211fde12cc8a4e2477602f5953f5b71 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.GPL;md5=c96076271561b0e3785dad260634eaa8 \
"

DEPENDS += "qtbase"
DEPENDS:class-target += "qtdeclarative qt3d-native"

# Patches from https://github.com/meta-qt5/qt3d/commits/b5.15
# 5.15.meta-qt5.2
SRC_URI += " \
    file://0001-Allow-a-tools-only-build.patch \
"
SRC_URI:append:riscv64 = " file://0001-renderers-opengl-Link-in-libatomic-on-riscv.patch"
SRC_URI:append:riscv32 = " file://0001-renderers-opengl-Link-in-libatomic-on-riscv.patch"

PACKAGECONFIG ??= ""
PACKAGECONFIG:class-native ??= "tools-only"
PACKAGECONFIG:class-nativesdk ??= "tools-only"
PACKAGECONFIG[tools-only] = ""
PACKAGECONFIG[system-assimp] = "-feature-system-assimp,-no-feature-system-assimp,assimp"
PACKAGECONFIG[qtgamepad] = ",,qtgamepad"

EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'tools-only', 'CONFIG+=tools-only QMAKE_USE_PRIVATE+=zlib', '', d)}"
EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'qtgamepad', 'CONFIG+=OE_QTGAMEPAD_ENABLED', '', d)}"

do_configure:prepend() {
    # disable qtgamepad test if it isn't enabled by PACKAGECONFIG
    sed -e 's/^\(qtHaveModule(gamepad)\)/OE_QTGAMEPAD_ENABLED:\1/' -i \
         ${S}/src/input/frontend/frontend.pri \
         ${S}/src/quick3d/imports/input/importsinput.pro
}

SRCREV = "34171b1d99f55fde1627df3c57eed50480ab2ae7"

BBCLASSEXTEND += "native nativesdk"
