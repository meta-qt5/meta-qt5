FILESEXTRAPATHS_prepend := "${THISDIR}/mesa-${PV}"
require recipes-graphics/mesa/mesa-common.inc
require mesa-${PV}.inc
require recipes-graphics/mesa/mesa-xlib.inc
PR = "${INC_PR}.1"

