require python-pyqt5.inc

inherit pythonnative python-dir

DEPENDS += "sip sip-native python"

RDEPENDS_${PN} += "python-core python-sip"

PNBLACKLIST[python-pyqt5] = "Sip fails in do_configure sip: QOpenGLFramebufferObject is undefined"
