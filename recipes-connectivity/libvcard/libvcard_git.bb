DESCRIPTION = "Qt vCard library"
SECTION = "libs"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f94eaed54ffa1718d593504bae740faf"

DEPENDS += "qtbase"

SRCREV = "4250e2468552ea01954244a2d9cd5d2b44b00042"
SRC_URI = "git://github.com/pol51/libvcard.git;protocol=https"

PV = "1.0+gitr${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake_qt5
