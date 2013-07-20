require qt5-git.inc
require ${PN}.inc

# qtlocation wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

# drop when bumping SRCREV
PR = "r1"

QT_MODULE_BRANCH = "master"

SRCREV = "f28408346243cf090326f4738fd838219c21e00f"
