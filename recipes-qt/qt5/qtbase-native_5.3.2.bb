require qt5-${PV}.inc
require ${PN}.inc

do_install_append() {
    # for modules which are still using syncqt and call qtPrepareTool(QMAKE_SYNCQT, syncqt)
    # e.g. qt3d, qtwayland
    ln -sf syncqt.pl ${D}${OE_QMAKE_PATH_QT_BINS}/syncqt
}

SRC_URI[md5sum] = "563e2b10274171f1184b3fd7260b4991"
SRC_URI[sha256sum] = "9a16095ac46dae99d6ddab8bc07065fbe1c36501ed194a3191d07347d7826cb8"
