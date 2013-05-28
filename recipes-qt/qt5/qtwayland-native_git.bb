require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

SRCREV = "ede872db1cdfdc2810c2dd29edd5fb6e1cdac0f5"
