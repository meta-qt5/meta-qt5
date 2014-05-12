require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.2.1

SRCREV = "a237778666666ab77c4e8e6b501cf0fbe7c9223e"

# wayland-scanner and qtwaylandscanner must be in same path to work properly
do_install_append() {
    ln -sf ${D}${OE_QMAKE_PATH_QT_BINS}/qtwaylandscanner ${D}${bindir}/qtwaylandscanner
}
