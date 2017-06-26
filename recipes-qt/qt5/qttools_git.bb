require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0+ | LGPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

inherit ptest

DEPENDS += "qtbase qtdeclarative qtxmlpatterns"

SRC_URI += " \
    file://run-ptest \
    file://0004-linguist-tools-cmake-allow-overriding-the-location-f.patch \
"

FILES_${PN}-tools += "${datadir}${QT_DIR_NAME}/phrasebooks"
FILES_${PN}-examples = "${datadir}${QT_DIR_NAME}/examples"

PACKAGECONFIG ??= ""
PACKAGECONFIG[qtwebkit] = ",,qtwebkit"

EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'qtwebkit', '', 'CONFIG+=noqtwebkit', d)}"

SRCREV = "dfda6f14b3e210a33b71ef1c6fa3c75a9c866cce"

BBCLASSEXTEND = "native nativesdk"

do_compile_ptest() {
    export PATH=${STAGING_DIR_NATIVE}/usr/include/qt5:$PATH
    cd ${S}/tests
    qmake -o Makefile tests.pro
    oe_runmake
}

do_install_ptest() {
    mkdir -p ${D}${PTEST_PATH}
    t=${D}${PTEST_PATH}
    cp ${S}/tests/auto/qtdiag/tst_tdiag $t
    cp ${S}/tests/auto/qtattributionsscanner/tst_qtattributionsscanner $t
}
