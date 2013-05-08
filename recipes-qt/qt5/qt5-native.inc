inherit native qmake5_base

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
