require python-pyqt5.inc

inherit python3native python3-dir

DEPENDS += "sip3 sip3-native python3"

RDEPENDS_${PN} += "python3-core python3-sip3"
