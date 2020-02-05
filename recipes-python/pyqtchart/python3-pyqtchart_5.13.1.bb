require python-pyqtchart.inc

inherit python3native python3-dir

DEPENDS += "sip3 sip3-native python3 python3-pyqt5"

RDEPENDS_${PN} += "python3-core python3-sip3 python3-pyqt5"
