require qt5-git.inc
require ${PN}.inc

# qtsensors wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

PR = "${INC_PR}.0"

SRCREV = "a8bb2b1720372b79b1e7c03692252a3d6f0a7c0f"
