require qt5-git.inc
require ${PN}.inc

SRCREV = "1e32e71403a6a9cb117343464fbc34564598e831"

SRC_URI += " \
    file://0001-examples-wayland-include-server-buffer-only-when-bui.patch \
"

QT_VERSION ?= "5.4.2"

do_install_append() {
    # do install files created by qtwaylandscanner
    install ${B}/include/QtCompositor/${QT_VERSION}/QtCompositor/private/qwayland-server-*.h ${D}${OE_QMAKE_PATH_QT_HEADERS}/QtCompositor/${QT_VERSION}/QtCompositor/private
    install ${B}/include/QtCompositor/${QT_VERSION}/QtCompositor/private/*protocol*.h ${D}${OE_QMAKE_PATH_QT_HEADERS}/QtCompositor/${QT_VERSION}/QtCompositor/private
}
