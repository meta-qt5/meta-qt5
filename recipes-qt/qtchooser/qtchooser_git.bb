DESCRIPTION = "Wrapper to select between Qt development binary versions"
HOMEPAGE = "http://macieira.org/qtchooser"
LICENSE = "LGPL-2.1 & Digia-Qt-LGPL-Exception-1.1 | GPL-3.0"
SRC_URI = "git://code.qt.io/cgit/qtsdk/qtchooser.git;branch=master \
           file://0001-Makefile-install-the-man-dir.patch"

LIC_FILES_CHKSUM = " \
    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de \
"
S = "${WORKDIR}/git"
SRCREV = "a1b6736e4f2daf86cd1626004d40ef57db85fbb5"
#PV = "39+git${SRCREV}"

inherit pkgconfig

do_compile() {
    oe_runmake LFLAGS='${LDFLAGS}'
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
    #install configure file
    install -d ${D}${sysconfdir}/xdg/qtchooser/
    install -m 0644 ${S}/tests/auto/qtchooser/testdata/config2/qtchooser/*.conf \
               ${D}${sysconfdir}/xdg/qtchooser/
    install -m 0644 ${S}/tests/auto/qtchooser/testdata/default/qtchooser/default.conf \
               ${D}${sysconfdir}/xdg/qtchooser/
}
