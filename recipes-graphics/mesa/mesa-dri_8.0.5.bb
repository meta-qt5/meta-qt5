FILESEXTRAPATHS_prepend := "${THISDIR}/mesa-${PV}"
require recipes-graphics/mesa/mesa-common.inc
require recipes-graphics/mesa/mesa-dri.inc
require mesa-${PV}.inc

PR = "${INC_PR}.30"

DEPENDS_append_x86 = " llvm2.8 "
export WANT_LLVM_RELEASE = "2.8"

DRIDRIVERS_append_x86 = ",radeon"
DRIDRIVERS_append_x86-64 = ",radeon"
GALLIUM_DRIVERS = "swrast"
GALLIUM_DRIVERS_x86 = "i915,r300,r600,swrast"
GALLIUM_DRIVERS_x86-64 = "i915,r300,r600,swrast"

EXTRA_OECONF := "${@oe_filter_out('--disable-gallium', '${EXTRA_OECONF}', d)}"
EXTRA_OECONF := "${@oe_filter_out('--without-gallium-drivers', '${EXTRA_OECONF}', d)}"

GALLIUM_SUPPORT = "--without-gallium-drivers --disable-gallium"
GALLIUM_SUPPORT_x86 = "--enable-gallium --with-gallium-drivers=${GALLIUM_DRIVERS}  --enable-gallium-egl --enable-gallium-gbm --enable-openvg"
GALLIUM_SUPPORT_x86-64 = "--enable-gallium --with-gallium-drivers=${GALLIUM_DRIVERS}  --enable-gallium-egl --enable-gallium-gbm --enable-openvg"

EXTRA_OECONF += "${GALLIUM_SUPPORT}"
