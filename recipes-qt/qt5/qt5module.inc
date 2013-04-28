require qt5.inc

DEPENDS += "qtbase"

inherit qmake5

do_configure_append () {
    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} -r ${S}
}
