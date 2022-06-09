require recipes-qt/qt5/qt5.inc

SUMMARY = "Qt Library for ConnMan"
HOMEPAGE = "https://github.com/sailfishos/libconnman-qt"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://libconnman-qt/clockmodel.h;endline=8;md5=ea9f724050803f15d2d900ce3c5dac88"
DEPENDS += "qtbase qtdeclarative"
PV = "1.2.45+git${SRCPV}"

SRCREV = "1b1ef5693218b3e4f398a320b91ab84ea38d87d7"
SRC_URI = "git://github.com/sailfishos/libconnman-qt.git;protocol=https;branch=master \
    file://0001-Add-missing-declarations-for-operator-overloads.patch \
"

S = "${WORKDIR}/git"

inherit pkgconfig

EXTRA_QMAKEVARS_PRE = "CONFIG+=no-module-prefix "

RDEPENDS:${PN} += "connman"

do_install:append() {
    if ls ${D}${libdir}/pkgconfig/connman-qt5.pc >/dev/null 2>/dev/null; then
        sed -i "s@-L${STAGING_LIBDIR}@-L\${libdir}@g" ${D}${libdir}/pkgconfig/connman-qt5.pc
    fi
}
