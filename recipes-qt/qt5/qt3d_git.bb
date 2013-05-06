require qt5-git.inc
require ${PN}.inc

# last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

PR = "${INC_PR}.0"

QT_MODULE_BRANCH = "master"

SRCREV = "0158ce783a61bac3e4f4ff619b0601daf9174ce6"
