require python-pyqt5.inc

inherit python3native python3-dir

DEPENDS += "sip3 sip3-native python3 qtdeclarative qtquickcontrols2 "

RDEPENDS_${PN} += "python3-core python3-sip3 qtdeclarative qtquickcontrols2 qtquickcontrols2-mkspecs"
