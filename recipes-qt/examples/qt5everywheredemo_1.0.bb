SUMMARY = "Qt5 everywhere demo"
DESCRIPTION = "Quick tour of Qt 5.0, primarily focusing on its graphical capabilities."
LICENSE = "BSD"
HOMEPAGE = "https://qt.gitorious.org/qt-labs"
LIC_FILES_CHKSUM = "file://qml/QtDemo/main.qml;endline=39;md5=7d80863906a4bc8ffca77fd869e335a9"

DEPENDS = "qtdeclarative qtgraphicaleffects qtsvg qtmultimedia"

SRCREV = "35d72a2eba7456a2efc5eb8b77afbc00f69ba0ac"
QT_GIT_PROJECT = "qt-labs"
SRC_URI = "${QT_GIT}/qt5-everywhere-demo"

S = "${WORKDIR}/git/QtDemo"

require recipes-qt/qt5/qt5.inc

do_install() {
    install -d ${D}${datadir}/${P}
    install -m 0755 ${B}/QtDemo ${D}${datadir}/${P}
    cp -R --no-dereference --preserve=mode,links ${S}/qml ${D}${datadir}/${P}
}

FILES_${PN} += "${datadir}"

RDEPENDS_${PN} = "qtdeclarative-qmlplugins qtgraphicaleffects-qmlplugins"
