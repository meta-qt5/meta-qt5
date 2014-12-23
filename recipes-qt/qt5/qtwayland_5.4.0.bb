require qt5-${PV}.inc
require ${PN}.inc

SRC_URI[md5sum] = "736fddb5f2018d57c8439455df9ec4dc"
SRC_URI[sha256sum] = "601ef7f2e9145a084da34ff06dfa77577c4a792a639581d3bbb7417e8cd36f29"

SRC_URI += " \
    file://0001-examples-wayland-include-server-buffer-only-when-bui.patch \
"
QT_VERSION ?= "5.4.0"

do_install_append() {
    # do install files created by qtwaylandscanner
    install ${B}/include/QtCompositor/${QT_VERSION}/QtCompositor/private/qwayland-server-*.h ${D}${OE_QMAKE_PATH_QT_HEADERS}/QtCompositor/${QT_VERSION}/QtCompositor/private
    install ${B}/include/QtCompositor/${QT_VERSION}/QtCompositor/private/*protocol*.h ${D}${OE_QMAKE_PATH_QT_HEADERS}/QtCompositor/${QT_VERSION}/QtCompositor/private
}
