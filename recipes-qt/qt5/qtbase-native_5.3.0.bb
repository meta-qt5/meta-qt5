require qt5-${PV}.inc
require ${PN}.inc

do_install_append() {
    # for modules which are still using syncqt and call qtPrepareTool(QMAKE_SYNCQT, syncqt)
    # e.g. qt3d, qtwayland
    ln -sf syncqt.pl ${D}${OE_QMAKE_PATH_QT_BINS}/syncqt
}

SRC_URI[md5sum] = "4bc43a72e1b3d804171e5b52640e8d96"
SRC_URI[sha256sum] = "07320bc8bbb718c420e22486942985c79fb2e2743981a19954aa09cc8a7147ab"
