require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

PR = "${INC_PR}.0"

SRCREV = "d48747b266298904ddc199bf1d9e422cd66e261a"
