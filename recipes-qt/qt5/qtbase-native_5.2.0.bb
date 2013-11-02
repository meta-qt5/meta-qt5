require qt5-${PV}.inc
require ${PN}.inc

do_install_append() {
    # for modules which are still using syncqt and call qtPrepareTool(QMAKE_SYNCQT, syncqt)
    # e.g. qt3d, qtwayland
    ln -sf syncqt.pl ${D}${OE_QMAKE_PATH_QT_BINS}/syncqt
}

SRC_URI[md5sum] = "c94bbaf1bb7f0f4a32d2caa7501416e1"
SRC_URI[sha256sum] = "51556cd2562a6d4bbb70ffcc93e8ef83ec79b170753aac6e4b195957c61cb628"
