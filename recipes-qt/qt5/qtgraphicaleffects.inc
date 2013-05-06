require qt5module.inc

INC_PR = "r0"

DEPENDS += "qtdeclarative"

FILES_${PN} += " \
            ${libdir}/${QT_DIR_NAME}/qml/QtGraphicalEffects/qmldir \
            ${libdir}/${QT_DIR_NAME}/qml/QtGraphicalEffects/*.qml \
            ${libdir}/${QT_DIR_NAME}/qml/QtGraphicalEffects/private/*.qml \
"

