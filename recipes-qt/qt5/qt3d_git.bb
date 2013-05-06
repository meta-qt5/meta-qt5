require ${PN}.inc
require qt5-git.inc

# last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

PR = "${INC_PR}.0"

QT_MODULE_BRANCH = "master"

SRCREV = "d723769d90331f4cde8dcb5aa3973e5c6bad8753"
