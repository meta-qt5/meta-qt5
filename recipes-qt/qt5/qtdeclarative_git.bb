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

# Patches from https://github.com/meta-qt5/qtdeclarative/commits/b5.11
# 5.11.meta-qt5.4
SRC_URI += "file://0001-Always-use-commit-sha1-for-QML_COMPILE_HASH.patch"

DEPENDS += "qtbase"

PACKAGECONFIG ??= "qtxmlpatterns qml-debug qml-network ${@bb.utils.contains('DISTRO_FEATURES', 'qt5-static', 'static', '', d)}"
PACKAGECONFIG[qtxmlpatterns] = ",,qtxmlpatterns"
PACKAGECONFIG[qml-debug] = "-qml-debug,-no-qml-debug"
PACKAGECONFIG[qml-network] = "-qml-network, -no-qml-network"
PACKAGECONFIG[static] = ",,qtdeclarative-native"

do_configure_prepend() {
    # disable qtxmlpatterns test if it isn't enabled by PACKAGECONFIG
    sed -e 's/^\(qtHaveModule(xmlpatterns)\)/OE_QTXMLPATTERNS_ENABLED:\1/' -i ${S}/src/imports/imports.pro
    sed -e 's/^\(!qtHaveModule(xmlpatterns)\)/!OE_QTXMLPATTERNS_ENABLED|\1/' -i ${S}/tests/auto/quick/quick.pro
}

do_install_append_class-nativesdk() {
    # qml files not needed in nativesdk
    rm -rf ${D}${OE_QMAKE_PATH_QML}
}

EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'qtxmlpatterns', 'CONFIG+=OE_QTXMLPATTERNS_ENABLED', '', d)}"

SRCREV = "e3c0bb7811407bad1f65ea55639a4b1d1d39be15"

BBCLASSEXTEND =+ "native nativesdk"
