require python3-pyqt5.inc

SRC_URI[md5sum] = "ae2c68e38b9b36fdf5f932419353a2b3"
SRC_URI[sha256sum] = "755121a52b3a08cb07275c10ebb96576d36e320e572591db16cfdbc558101594"

PYQT_MODULES += "QtNetwork QtNetwork QtQml ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'QtQuick QtWidgets QtQuickWidgets', '', d)}"
