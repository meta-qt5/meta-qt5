require qt5.inc

DEPENDS = "qtbase qtjsbackend"

INC_PR = "r0"

inherit qmake5

do_configure_append () {
    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} -r ${S}
}

FILES_${PN}-qmlplugins-dbg = " \
    ${libdir}/${QT_DIR_NAME}/qml/*/.debug \
    ${libdir}/${QT_DIR_NAME}/qml/*/*/.debug \
    ${libdir}/${QT_DIR_NAME}/qml/*/*/*/.debug \
"
FILES_${PN}-qmltooling-dbg = " \
    ${libdir}/${QT_DIR_NAME}/plugins/qmltooling/.debug/* \
"
FILES_${PN}-qmlplugins = " \
    ${libdir}/${QT_DIR_NAME}/qml/* \
"
FILES_${PN}-qmltooling = " \
    ${libdir}/${QT_DIR_NAME}/plugins/qmltooling/* \
"

PACKAGES .= " ${PN}-qmlplugins-dbg ${PN}-qmltooling-dbg ${PN}-qmlplugins ${PN}-qmltooling"
