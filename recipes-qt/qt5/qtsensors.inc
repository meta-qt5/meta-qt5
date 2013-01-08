require qt5module.inc

DEPENDS += "qtdeclarative"

INC_PR = "r0"

FILES_${PN}-plugins += " \
            ${libdir}/${QT_DIR_NAME}/qml/QtSensors/qmldir \
            ${libdir}/${QT_DIR_NAME}/qml/QtSensors/plugins.qmltypes \
            ${libdir}/${QT_DIR_NAME}/qml/QtSensors/*${SOLIBSDEV} \
"
FILES_${PN}-plugins-dbg += " \
            ${libdir}/${QT_DIR_NAME}/qml/QtSensors/.debug/* \
"
