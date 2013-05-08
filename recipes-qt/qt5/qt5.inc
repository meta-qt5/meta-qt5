# Copyright (C) 2012 O.S. Systems Software LTDA.
# Copyright (C) 2013 Martin Jansa <martin.jansa@gmail.com>

inherit qmake5

# Qt5 is dependent on icu for localization
ICU = "icu "
ICU_powerpc = "pango"

require qt5_arch.inc

QT_MODULE ?= "${BPN}"

do_configure() {
    set_arch
    set_endian

    qmake5_base_do_configure
}

do_install() {
    qmake5_base_do_install
}

# we don't want conflicts with qt4
OE_QMAKE_PATH_HEADERS = "${includedir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_ARCHDATA = "${libdir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_DATA = "${datadir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_BINS = "${bindir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_TRANSLATIONS = "${datadir}/${QT_DIR_NAME}/translations"
OE_QMAKE_PATH_DOCS = "${docdir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_SETTINGS = "${sysconfdir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_EXAMPLES = "${datadir}/${QT_DIR_NAME}/examples"
OE_QMAKE_PATH_TESTS = "${datadir}/${QT_DIR_NAME}/tests"

PACKAGES =. "${PN}-qmlplugins-dbg ${PN}-tools-dbg ${PN}-plugins-dbg ${PN}-qmlplugins ${PN}-tools ${PN}-plugins ${PN}-mkspecs "

# extra -dbg packages
FILES_${PN}-qmlplugins-dbg = " \
    ${OE_QMAKE_PATH_QML}/*/.debug \
    ${OE_QMAKE_PATH_QML}/*/*/.debug \
    ${OE_QMAKE_PATH_QML}/*/*/*/.debug \
    ${OE_QMAKE_PATH_IMPORTS}/*/.debug \
    ${OE_QMAKE_PATH_IMPORTS}/*/*/.debug \
    ${OE_QMAKE_PATH_IMPORTS}/*/*/*/.debug \
"
FILES_${PN}-tools-dbg = " \
    ${OE_QMAKE_PATH_BINS}/.debug \
"
FILES_${PN}-plugins-dbg = " \
    ${OE_QMAKE_PATH_PLUGINS}/*/.debug/* \
"

# extra packages
# qmlplugins 1-3 levels of subdirs qmldir + *{.so,*.qmltypes,*.qml,*js}
FILES_${PN}-qmlplugins = " \
    ${OE_QMAKE_PATH_QML}/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_QML}/*/qmldir \
    ${OE_QMAKE_PATH_QML}/*/*.qmltypes \
    ${OE_QMAKE_PATH_QML}/*/*.qml \
    ${OE_QMAKE_PATH_QML}/*/*.js \
    ${OE_QMAKE_PATH_QML}/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_QML}/*/*/qmldir \
    ${OE_QMAKE_PATH_QML}/*/*/*.qmltypes \
    ${OE_QMAKE_PATH_QML}/*/*/*.qml \
    ${OE_QMAKE_PATH_QML}/*/*/*.js \
    ${OE_QMAKE_PATH_QML}/*/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_QML}/*/*/*/qmldir \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.qmltypes \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.qml \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.js \
    ${OE_QMAKE_PATH_IMPORTS}/*.qmltypes \
    ${OE_QMAKE_PATH_IMPORTS}/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_IMPORTS}/*/*.qmltypes \
    ${OE_QMAKE_PATH_IMPORTS}/*/qmldir \
    ${OE_QMAKE_PATH_IMPORTS}/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_IMPORTS}/*/*/*.qmltypes \
    ${OE_QMAKE_PATH_IMPORTS}/*/*/qmldir \
    ${OE_QMAKE_PATH_IMPORTS}/*/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_IMPORTS}/*/*/*/*.qmltypes \
    ${OE_QMAKE_PATH_IMPORTS}/*/*/*/qmldir \
"
FILES_${PN}-tools = " \
    ${OE_QMAKE_PATH_BINS}/* \
"
FILES_${PN}-plugins = " \
    ${OE_QMAKE_PATH_PLUGINS}/*/*${SOLIBSDEV} \
"
FILES_${PN}-mkspecs = "\
    ${OE_QMAKE_PATH_ARCHDATA}/mkspecs \
"

# modifications to normal packages
FILES_${PN} += " \
    ${OE_QMAKE_PATH_LIBS}/lib*${SOLIBS} \
    ${OE_QMAKE_PATH_LIBEXECS} \
"
FILES_${PN}-dev += " \
    ${OE_QMAKE_PATH_LIBS}/lib*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_LIBS}/pkgconfig \
    ${OE_QMAKE_PATH_LIBS}/cmake/* \
    ${OE_QMAKE_PATH_LIBS}/*.prl \
    ${OE_QMAKE_PATH_LIBS}/*.la \
    ${OE_QMAKE_PATH_DATA}/* \
    ${OE_QMAKE_PATH_HEADERS}/* \
"
FILES_${PN}-dbg += " \
    ${OE_QMAKE_PATH_LIBS}/.debug \
    ${OE_QMAKE_PATH_LIBEXECS}/.debug \
"
FILES_${PN}-staticdev += " \
    ${OE_QMAKE_PATH_LIBS}/*.a \
"
