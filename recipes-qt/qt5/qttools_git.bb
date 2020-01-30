require qt5.inc
require qt5-git.inc

HOMEPAGE = "http://www.qt.io"
LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0+ | LGPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

DEPENDS += "qtbase qtdeclarative qtxmlpatterns"
# Patches from https://github.com/meta-qt5/qttools/commits/b5.13
# 5.13.meta-qt5.1
SRC_URI += " \
    file://0001-add-noqtwebkit-configuration.patch \
    file://0002-linguist-tools-cmake-allow-overriding-the-location-f.patch \
    file://0003-src.pro-Add-option-noqdoc-to-disable-qdoc-builds.patch \
"

FILES_${PN}-tools += "${datadir}${QT_DIR_NAME}/phrasebooks"
FILES_${PN}-examples = "${datadir}${QT_DIR_NAME}/examples"

PACKAGECONFIG ??= ""
PACKAGECONFIG_append_toolchain-clang = " clang"

PACKAGECONFIG[qtwebkit] = ",,qtwebkit"
PACKAGECONFIG[clang] = ",,clang"

COMPATIBLE_HOST_toolchain-clang_riscv32 = "null"
COMPATIBLE_HOST_toolchain-clang_riscv64 = "null"

export YOCTO_ALTERNATE_EXE_PATH = "${STAGING_BINDIR}/llvm-config"

TOOLSTOBUILD += "linguist/lconvert linguist/lrelease linguist/lupdate pixeltool qtdiag qtpaths qtplugininfo"
TOOLSTOBUILD += "${@bb.utils.contains('PACKAGECONFIG', 'clang', 'qdoc', '', d)}"
TOOLSFORTARGET = "pixeltool qtdiag qtpaths qtplugininfo"
TOOLSFORHOST = "linguist ${@bb.utils.contains('PACKAGECONFIG', 'clang', 'qdoc', '', d)}"

EXTRA_QMAKEVARS_PRE += " \
    ${@bb.utils.contains('PACKAGECONFIG', 'qtwebkit', '', 'CONFIG+=noqtwebkit', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'clang', 'CONFIG+=disable_external_rpath', 'CONFIG+=noqdoc', d)} \
"
EXTRA_QMAKEVARS_PRE_append_class-native = " CONFIG+=config_clang_done CONFIG-=config_clang"
EXTRA_QMAKEVARS_PRE_append_class-nativesdk = " CONFIG+=config_clang_done CONFIG-=config_clang"
EXTRA_QMAKEVARS_PRE_append_class-target = "\
    ${@bb.utils.contains('PACKAGECONFIG', 'clang', 'CONFIG+=config_clang', 'CONFIG+=config_clang_done CONFIG-=config_clang', d)} \
"

SRCREV = "eac773c8dfd0e2166db53c88f5aa0c1e85933cac"

BBCLASSEXTEND = "native nativesdk"

