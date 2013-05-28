require qt5-git.inc
require ${PN}.inc

# qtsystems wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

PR = "${INC_PR}.0"

QT_MODULE_BRANCH = "master"

SRCREV = "701442ad6358b9f27978aafae82074124468f88c"
