require qt5.inc
require qt5-git.inc
require qt5-ptest.inc

HOMEPAGE = "http://www.qt.io"
LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0+ | LGPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

DEPENDS += "qtbase qtdeclarative qtxmlpatterns chrpath-replacement-native"
EXTRANATIVEPATH += "chrpath-native"
# Patches from https://github.com/meta-qt5/qttools/commits/b5.12
# 5.12.meta-qt5.2
SRC_URI += " \
    file://0001-add-noqtwebkit-configuration.patch \
    file://0002-linguist-tools-cmake-allow-overriding-the-location-f.patch \
"

FILES_${PN}-tools += "${datadir}${QT_DIR_NAME}/phrasebooks"
FILES_${PN}-examples = "${datadir}${QT_DIR_NAME}/examples"

PACKAGECONFIG ??= ""
PACKAGECONFIG_append_toolchain-clang = " clang"

PACKAGECONFIG[qtwebkit] = ",,qtwebkit"
PACKAGECONFIG[clang] = ",,clang"

export YOCTO_ALTERNATE_EXE_PATH = "${STAGING_BINDIR}/llvm-config"

EXTRA_QMAKEVARS_PRE += " \
    ${@bb.utils.contains('PACKAGECONFIG', 'qtwebkit', '', 'CONFIG+=noqtwebkit', d)} \
"
EXTRA_QMAKEVARS_PRE_append_class-native = " CONFIG+=config_clang_done CONFIG-=config_clang"
EXTRA_QMAKEVARS_PRE_append_class-nativesdk = " CONFIG+=config_clang_done CONFIG-=config_clang"
EXTRA_QMAKEVARS_PRE_append_class-target = "\
    ${@bb.utils.contains('PACKAGECONFIG', 'clang', 'CONFIG+=config_clang', 'CONFIG+=config_clang_done CONFIG-=config_clang', d)} \
"

SRCREV = "78f52a4027da110bf14468b575c7262b4d28d65e"

BBCLASSEXTEND = "native nativesdk"

do_install_ptest() {
    mkdir -p ${D}${PTEST_PATH}
    t=${D}${PTEST_PATH}
    cp ${B}/tests/auto/qtdiag/tst_tdiag $t
}
do_install_append_toolchain-clang() {
    chrpath --delete ${D}${bindir}/qdoc
}
