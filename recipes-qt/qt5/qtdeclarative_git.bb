require qt5-git.inc
require ${PN}.inc

SRC_URI += " \
    file://0001-qmltestexample-fix-link.patch \
    file://0003-Fix-wrong-calculation-of-viewPort-for-transitions.patch \
"

SRCREV = "f95fdacb3a12e4f0d37d3c32b34326f2bd1536de"
