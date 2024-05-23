DESCRIPTION = "Extra files for qt5 demo"
LICENSE = "LGPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=88355dc91a186cc816d9f64757793895"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

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
    install -m 0644 ${UNPACKDIR}/cinematicexperience.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/cinematicexperience.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/hellogl_es2.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/hellogl_es2.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/hellowindow.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/hellowindow.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/qt5everywheredemo.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/qt5everywheredemo.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/qt5nmapcarousedemo.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/qt5nmapcarousedemo.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/qt5nmapper.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/qt5nmapper.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/qtledbillboard.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/qtledbillboard.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/qtledcombo.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/qtledcombo.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/qtsmarthome.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/qtsmarthome.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/quitbattery.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/quitbattery.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/quitindicators.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/quitindicators.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/qt5basket.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/qt5basket.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/qt5nesting.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/qt5nesting.desktop ${D}/${datadir}/applications
    install -m 0644 ${UNPACKDIR}/qt5solarsystem.png ${D}/${datadir}/pixmaps
    install -m 0644 ${UNPACKDIR}/qt5solarsystem.desktop ${D}/${datadir}/applications
}
