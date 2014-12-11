require qt5-${PV}.inc
require ${PN}.inc

do_install_append() {
    # for modules which are still using syncqt and call qtPrepareTool(QMAKE_SYNCQT, syncqt)
    # e.g. qt3d, qtwayland
    ln -sf syncqt.pl ${D}${OE_QMAKE_PATH_QT_BINS}/syncqt
}

SRC_URI[md5sum] = "eaaa72a5cb25713ca8d17f3a8d149765"
SRC_URI[sha256sum] = "daea240ba5e77bc2d78ec21a2cb664eed83b3d4ad409b6277a6f7d4c0c8e91d1"
