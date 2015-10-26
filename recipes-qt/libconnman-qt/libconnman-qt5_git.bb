require recipes-qt/qt5/qt5.inc

SUMMARY = "Qt Library for ConnMan"
HOMEPAGE = "https://git.merproject.org/mer-core/libconnman-qt"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://libconnman-qt/clockmodel.h;endline=8;md5=ea9f724050803f15d2d900ce3c5dac88"
DEPENDS += "qtbase qtdeclarative"
PV = "1.0.97+git${SRCPV}"

SRCREV = "4cca434935fa4d70d6841a2c20717eecb0beafa8"
SRC_URI = "git://git.merproject.org/mer-core/libconnman-qt.git;protocol=https"

S = "${WORKDIR}/git"

inherit pkgconfig

RDEPENDS_${PN} += "connman"
