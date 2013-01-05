FILESEXTRAPATHS_prepend := "${THISDIR}/mesa-${PV}"
require recipes-graphics/mesa/mesa-common.inc
require recipes-graphics/mesa/mesa-dri.inc
require mesa-${PV}.inc

PR = "${INC_PR}.24"
