require qt5-${PV}.inc
require ${PN}.inc

do_install_append() {
    # for modules which are still using syncqt and call qtPrepareTool(QMAKE_SYNCQT, syncqt)
    # e.g. qt3d, qtwayland
    ln -sf syncqt.pl ${D}${OE_QMAKE_PATH_QT_BINS}/syncqt
}

SRC_URI[md5sum] = "572c9953847d391f2d33b420bbcdca46"
SRC_URI[sha256sum] = "7b5a138d30d7c0228a51084407d5210f6d1acfbee2f95b87f189872cfcd3a569"
