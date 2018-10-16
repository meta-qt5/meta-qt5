SUMMARY = "Qt Widget Extension for Technical Applications"
SECTION = "libs"
HOMEPAGE = "http://qwt.sourceforge.net/index.html"

# LGPLv2.1 + some exceptions
LICENSE = "QWTv1.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=dac2743472b0462ff3cfb4af42051c88"

DEPENDS = "qtbase qtsvg qttools"

inherit qmake5

SRC_URI = " \
    ${SOURCEFORGE_MIRROR}/qwt/qwt-${PV}.tar.bz2;name=qwt \
    file://0001-Remove-rpath-from-binaries-they-point-to-buuild-area.patch \
"
SRC_URI[qwt.md5sum] = "19d1f5fa5e22054d22ee3accc37c54ba"
SRC_URI[qwt.sha256sum] = "f3ecd34e72a9a2b08422fb6c8e909ca76f4ce5fa77acad7a2883b701f4309733"

S = "${WORKDIR}/qwt-${PV}"

EXTRA_QMAKEVARS_PRE += " \
    QWT_CONFIG+=QwtPkgConfig \
    QWT_CONFIG+=QwtExamples \
"

do_configure_prepend() {
    sed -i 's:/usr/local/qwt-$$QWT_VERSION:${prefix}:' ${S}/*.pri
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
