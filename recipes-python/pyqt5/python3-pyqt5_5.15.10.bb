require python3-pyqt5.inc

PYQT_MODULES += " \
    QtNetwork \
    QtQml \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'QtQuick QtWidgets QtQuickWidgets', '', d)} \
"
