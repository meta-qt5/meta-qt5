require ${PN}.inc
require qt5-git.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

PR = "${INC_PR}.1"

SRCREV = "5cb159395eccb1d96fb73a78e499eef30aacb46d"
