require qt5-${PV}.inc
require ${PN}.inc

SRC_URI[md5sum] = "a132295b896062d21ded0937a56d22a3"
SRC_URI[sha256sum] = "653b5e600b1cbf3275d0858415570d2a2611823a4651ee911e684dcae870d792"

SRC_URI += " \
    file://0001-examples-wayland-include-server-buffer-only-when-bui.patch \
"
QT_VERSION ?= "5.4.1"

do_install_append() {
    # do install files created by qtwaylandscanner
    install ${B}/include/QtCompositor/${QT_VERSION}/QtCompositor/private/qwayland-server-*.h ${D}${OE_QMAKE_PATH_QT_HEADERS}/QtCompositor/${QT_VERSION}/QtCompositor/private
    install ${B}/include/QtCompositor/${QT_VERSION}/QtCompositor/private/*protocol*.h ${D}${OE_QMAKE_PATH_QT_HEADERS}/QtCompositor/${QT_VERSION}/QtCompositor/private
}
