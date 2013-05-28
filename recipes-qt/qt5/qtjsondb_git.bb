require qt5-git.inc
require ${PN}.inc

# qtjsondb wasn't released yet, last tag before this SRCREV isn't even 5.0.0-beta1, but lets use it for now
PV = "4.999+5.0.0-beta1+git${SRCPV}"

PR = "${INC_PR}.0"

QT_MODULE_BRANCH = "master"

SRCREV = "c7c1e61bb5beddc3f4502548fd9ee498f1534e67"
