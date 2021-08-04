require python-pyqt5.inc

inherit pythonnative python-dir

DEPENDS += "sip sip-native python"

RDEPENDS:${PN} += "python-core python-sip"

