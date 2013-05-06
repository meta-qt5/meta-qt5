require qt5-git.inc
require ${PN}.inc

PR = "${INC_PR}.0"

# qtsystems wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

SRCREV = "2dbaae64ccd0fa3646d68d77cbc9baac7d3bde2e"
