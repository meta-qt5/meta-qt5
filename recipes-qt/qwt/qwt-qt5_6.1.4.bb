SUMMARY = "Qt Widget Extension for Technical Applications"
SECTION = "libs"
HOMEPAGE = "http://qwt.sourceforge.net/index.html"

# LGPLv2.1 + some exceptions
LICENSE = "QWTv1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=dac2743472b0462ff3cfb4af42051c88"

DEPENDS = "qtbase qtsvg qttools"

COMPATIBLE_HOST_toolchain-clang_riscv32 = "null"
COMPATIBLE_HOST_toolchain-clang_riscv64 = "null"

inherit qmake5

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/qwt/qwt-${PV}.tar.bz2;name=qwt \
    file://0001-Remove-rpath-from-binaries-they-point-to-buuild-area.patch \
    file://0001-missing-qpainterpath.h-include-added-needed-with-Qt-.patch \
"
SRC_URI[qwt.md5sum] = "4fb1852f694420e3ab9c583526edecc5"
SRC_URI[qwt.sha256sum] = "1529215329e51fc562e0009505a838f427919a18b362afff441f035b2d9b5bd9"

S = "${WORKDIR}/qwt-${PV}"

EXTRA_QMAKEVARS_PRE += " \
    QWT_CONFIG+=QwtPkgConfig \
    QWT_CONFIG+=QwtExamples \
"

do_configure_prepend() {
    sed -i \
        -e 's:/usr/local/qwt-$$QWT_VERSION:${prefix}:' \
        -e 's:^QWT_INSTALL_LIBS.*:QWT_INSTALL_LIBS = ${libdir}:' \
        ${S}/*.pri
    export QWT_INSTALL_LIBS=${libdir}
}

do_install_append() {
    # seems out of tree build confuses installation of examples
    # so install them manually
    install -d ${D}${bindir}/
    cp ${B}/examples/bin/* ${D}${bindir}/
}


PACKAGES_prepend = "${PN}-examples ${PN}-features ${PN}-plugins "
FILES_${PN}-examples = "${bindir}/*"
FILES_${PN}-features = "${prefix}/features"
FILES_${PN}-plugins = "${prefix}/plugins/designer/*.so"
FILES_${PN}-doc += "${prefix}/doc"

INSANE_SKIP_${PN}-plugins += "libdir"
INSANE_SKIP_${PN}-dbg += "libdir"

RPROVIDES_${PN}-dev = "libqwt-qt5-dev"
