require qt5-${PV}.inc
require ${PN}.inc

do_install_append() {
    # for modules which are still using syncqt and call qtPrepareTool(QMAKE_SYNCQT, syncqt)
    # e.g. qt3d, qtwayland
    ln -sf syncqt.pl ${D}${OE_QMAKE_PATH_QT_BINS}/syncqt
}

SRC_URI[md5sum] = "fa005301a2000b92b61b63edc042567b"
SRC_URI[sha256sum] = "acdfd1aa2548ebea1d922e8e24e5c59f5fc3b2beae7c8003ba47d773bfcc94c0"
