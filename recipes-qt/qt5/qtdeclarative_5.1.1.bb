require qt5-${PV}.inc
require ${PN}.inc

SRC_URI += " \
    file://0001-qmltestexample-fix-link.patch \
    file://0003-Fix-wrong-calculation-of-viewPort-for-transitions.patch \
"

SRC_URI[md5sum] = "486fc16ad7b7d0c1488ba5482536d66c"
SRC_URI[sha256sum] = "0ee989a5d45a94e927609b22e2413c7f2788a7b4a23af66ecfa15c31db2a9b31"

# /usr/lib/qt5/qml/QtQuick/Dialogs/images/*.png
FILES_${PN}-qmlplugins += " \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.png \
"
