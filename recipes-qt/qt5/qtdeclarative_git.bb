require qt5-git.inc
require ${PN}.inc

SRC_URI += " \
    file://0001-qmltestexample-fix-link.patch \
    file://0003-Fix-wrong-calculation-of-viewPort-for-transitions.patch \
"

SRCREV = "672354676d8e968e2523d1aeb450213a46b8b27c"
