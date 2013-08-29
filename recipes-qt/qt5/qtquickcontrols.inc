require qt5.inc

FILES_${PN}-qmlplugins += " \
   ${OE_QMAKE_PATH_QML}/*/*/*/*/*/*.png \
"

DEPENDS += "qtscript"
