require qt5-git.inc
require ${PN}.inc

SRCREV = "98dca3b54f52f08117c1e0d3a1b4826ed12ef23f"

SRC_URI += " \
    file://0001-examples.pro-include-server-buffer-only-when-buildin.patch \
"

# this wasn't released, the PV is set just to signify that this SRCREV was tested together
# with 5.3.2 version of other modules
PV = "5.3.2"
DEFAULT_PREFERENCE = "1"

QT_VERSION ?= "5.3.0"

do_install_append() {
    # do install files created by qtwaylandscanner
    install ${B}/include/QtCompositor/${QT_VERSION}/QtCompositor/private/qwayland-server-*.h ${D}${OE_QMAKE_PATH_QT_HEADERS}/QtCompositor/${QT_VERSION}/QtCompositor/private
    install ${B}/include/QtCompositor/${QT_VERSION}/QtCompositor/private/*protocol*.h ${D}${OE_QMAKE_PATH_QT_HEADERS}/QtCompositor/${QT_VERSION}/QtCompositor/private
}
