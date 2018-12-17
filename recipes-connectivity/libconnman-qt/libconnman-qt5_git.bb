require recipes-qt/qt5/qt5.inc

SUMMARY = "Qt Library for ConnMan"
HOMEPAGE = "https://git.merproject.org/mer-core/libconnman-qt"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://libconnman-qt/clockmodel.h;endline=8;md5=ea9f724050803f15d2d900ce3c5dac88"
DEPENDS += "qtbase qtdeclarative"
PV = "1.2.7+git${SRCPV}"

SRCREV = "ad7fef1c35a3e897913965f73b879a14d65043dd"
SRC_URI = "git://git.merproject.org/mer-core/libconnman-qt.git;protocol=https \
    file://0001-Don-t-use-MeeGo-as-prefix-in-order-to-make-this-a-co.patch \
    file://0001-Fix-library-path-for-multilib-setups.patch \
"

S = "${WORKDIR}/git"

inherit pkgconfig

RDEPENDS_${PN} += "connman"

do_install_append() {
    if ls ${D}${libdir}/pkgconfig/connman-qt5.pc >/dev/null 2>/dev/null; then
        sed -i "s@-L${STAGING_LIBDIR}@-L\${libdir}@g" ${D}${libdir}/pkgconfig/connman-qt5.pc
    fi
}
