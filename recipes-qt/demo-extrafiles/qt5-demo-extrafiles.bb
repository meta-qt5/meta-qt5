DESCRIPTION = "Extra files for qt5 demo"
LICENSE = "LGPL-2.0-only"
S="${WORKDIR}"
LIC_FILES_CHKSUM = "file://LICENSE;md5=88355dc91a186cc816d9f64757793895"

SRC_URI += "file://cinematicexperience.desktop \
            file://cinematicexperience.png \
            file://hellogl_es2.desktop \
            file://hellogl_es2.png \
            file://hellowindow.desktop \
            file://hellowindow.png \
            file://qt5everywheredemo.desktop \
            file://qt5everywheredemo.png \
            file://qt5nmapcarousedemo.desktop \
            file://qt5nmapcarousedemo.png \
            file://qt5nmapper.desktop \
            file://qt5nmapper.png \
            file://qtledbillboard.desktop \
            file://qtledbillboard.png \
            file://qtledcombo.desktop \
            file://qtledcombo.png \
            file://qtsmarthome.desktop \
            file://qtsmarthome.png \
            file://quitbattery.desktop \
            file://quitbattery.png \
            file://quitindicators.desktop \
            file://quitindicators.png \
            file://qt5basket.desktop \
            file://qt5basket.png \
            file://qt5nesting.desktop \
            file://qt5nesting.png \
            file://qt5solarsystem.desktop \
            file://qt5solarsystem.png \
            file://LICENSE"

inherit allarch

do_install () {
    install -d ${D}/${datadir}/pixmaps
    install -d ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/cinematicexperience.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/cinematicexperience.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/hellogl_es2.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/hellogl_es2.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/hellowindow.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/hellowindow.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/qt5everywheredemo.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/qt5everywheredemo.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/qt5nmapcarousedemo.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/qt5nmapcarousedemo.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/qt5nmapper.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/qt5nmapper.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/qtledbillboard.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/qtledbillboard.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/qtledcombo.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/qtledcombo.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/qtsmarthome.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/qtsmarthome.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/quitbattery.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/quitbattery.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/quitindicators.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/quitindicators.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/qt5basket.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/qt5basket.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/qt5nesting.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/qt5nesting.desktop ${D}/${datadir}/applications
    install -m 0644 ${WORKDIR}/qt5solarsystem.png ${D}/${datadir}/pixmaps
    install -m 0644 ${WORKDIR}/qt5solarsystem.desktop ${D}/${datadir}/applications
}
