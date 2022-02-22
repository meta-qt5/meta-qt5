SUMMARY = "Qt Kiosk Browser"
DESCRIPTION = "Provides a simple but highly configurable browser for use on Kiosk devices"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1ebbd3e34237af26da5dc08a4e440464"
LICENSE = "GPL-3.0-only"

DEPENDS = "qtwebengine"
python() {
    if 'meta-python2' not in d.getVar('BBFILE_COLLECTIONS').split():
        raise bb.parse.SkipRecipe('qtwebengine dependency requires meta-python2 to be present.')
}

SRC_URI = " \
    git://github.com/OSSystems/qt-kiosk-browser;protocol=https;branch=master \
    file://${BPN}.conf \
"

PV = "0.0+git${SRCPV}"
SRCREV = "d42b7d9050b5445e2b9430022a46d5d583581631"

S = "${WORKDIR}/git"

inherit qmake5

EXTRA_QMAKEVARS_PRE += "PREFIX=${prefix}"

do_install:append() {
    install -Dm 0644 ${WORKDIR}/${PN}.conf ${D}${sysconfdir}/${PN}.conf
}

RDEPENDS:${PN} += " \
    liberation-fonts \
    qtdeclarative-qmlplugins \
    qtquickcontrols-qmlplugins \
    qtvirtualkeyboard \
    qtwebengine-qmlplugins \
"
COMPATIBLE_MACHINE = "(-)"
COMPATIBLE_MACHINE:x86 = "(.*)"
COMPATIBLE_MACHINE:x86-64 = "(.*)"
COMPATIBLE_MACHINE:armv6 = "(.*)"
COMPATIBLE_MACHINE:armv7a = "(.*)"
COMPATIBLE_MACHINE:armv7ve = "(.*)"
COMPATIBLE_MACHINE:aarch64 = "(.*)"
