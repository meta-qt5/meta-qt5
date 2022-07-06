require recipes-qt/qt5/qt5.inc

SUMMARY = "Qt 5/6 Library for ConnMan"
HOMEPAGE = "https://github.com/sailfishos/libconnman-qt"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://libconnman-qt/clockmodel.h;endline=8;md5=ea9f724050803f15d2d900ce3c5dac88"
DEPENDS += "qtbase qtdeclarative"

VERSION = "1.2.46"
PV = "${VERSION}+git${SRCPV}"

SRCREV = "6786936cba9048ee943c0cd5d051c0b8fc70896f"
SRC_URI = "git://github.com/sailfishos/libconnman-qt.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_QMAKEVARS_PRE = "CONFIG+=no-module-prefix VERSION=${VERSION}"

RDEPENDS:${PN} += "connman"

do_install:append() {
    if ls ${D}${libdir}/pkgconfig/connman-qt5.pc >/dev/null 2>/dev/null; then
        sed -i "s@-L${STAGING_LIBDIR}@-L\${libdir}@g" ${D}${libdir}/pkgconfig/connman-qt5.pc
    fi
}
