require qt5-${PV}.inc
require ${PN}.inc

do_install_append() {
    # for modules which are still using syncqt and call qtPrepareTool(QMAKE_SYNCQT, syncqt)
    # e.g. qt3d, qtjsondb, qtwayland
    ln -sf syncqt.pl ${D}${OE_QMAKE_PATH_QT_BINS}/syncqt
}

SRC_URI[md5sum] = "0f8d14bb4039a2996c501a376ca7dae0"
SRC_URI[sha256sum] = "1fa8c591adab9d45e4f322edea9a55a517ebf1a006dd481eace0236623adc90a"
