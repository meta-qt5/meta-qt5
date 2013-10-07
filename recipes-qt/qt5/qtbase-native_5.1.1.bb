require qt5-${PV}.inc
require ${PN}.inc

do_install_append() {
    # for modules which are still using syncqt and call qtPrepareTool(QMAKE_SYNCQT, syncqt)
    # e.g. qt3d, qtjsondb, qtwayland
    ln -sf syncqt.pl ${D}${OE_QMAKE_PATH_QT_BINS}/syncqt
}

SRC_URI[md5sum] = "955d1e4da875f3872ef3208f21a757dd"
SRC_URI[sha256sum] = "d4620e0b1aff6d2b6f4d8066e6f8258e012a8b5507af7c03b661029a1ffa75c9"
