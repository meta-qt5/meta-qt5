require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.2.1

SRCREV = "98dca3b54f52f08117c1e0d3a1b4826ed12ef23f"

# wayland-scanner and qtwaylandscanner must be in same path to work properly
do_install_append() {
    ln -sf ${D}${OE_QMAKE_PATH_QT_BINS}/qtwaylandscanner ${D}${bindir}/qtwaylandscanner
}
