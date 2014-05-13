require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.2.1

SRCREV = "f9ebbd6c618488f9b671f5504528ced3350754fb"

SRC_URI += " \
    file://0001-examples.pro-include-server-buffer-only-when-buildin.patch \
"

do_install_append() {
    # do install files created by qtwaylandscanner
    install ${B}/include/QtCompositor/5.3.0/QtCompositor/private/{qwayland-server-*,*protocol*}.h ${D}${includedir}/${QT_DIR_NAME}/QtCompositor/5.3.0/QtCompositor/private
}
