require qt5-git.inc
require ${PN}.inc

SRCREV = "e47474cf91be5fa1d12bf329526d938be8293638"

SRC_URI += " \
    file://0001-examples-wayland-include-server-buffer-only-when-bui.patch \
"

QT_VERSION ?= "5.4.0"

do_install_append() {
    # do install files created by qtwaylandscanner
    install ${B}/include/QtCompositor/${QT_VERSION}/QtCompositor/private/qwayland-server-*.h ${D}${OE_QMAKE_PATH_QT_HEADERS}/QtCompositor/${QT_VERSION}/QtCompositor/private
    install ${B}/include/QtCompositor/${QT_VERSION}/QtCompositor/private/*protocol*.h ${D}${OE_QMAKE_PATH_QT_HEADERS}/QtCompositor/${QT_VERSION}/QtCompositor/private
}
