require python-pyqtchart.inc

inherit pythonnative python-dir

DEPENDS += "sip sip-native python python-pyqt5"

RDEPENDS_${PN} += "python-core python-sip python-pyqt5"
