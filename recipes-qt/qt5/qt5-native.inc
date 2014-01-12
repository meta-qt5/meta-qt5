inherit native qmake5_base

# we don't want conflicts with qt4
OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"
OE_QMAKE_PATH_ARCHDATA = "${OE_QMAKE_PATH_QT_ARCHDATA}"
OE_QMAKE_PATH_DATA = "${OE_QMAKE_PATH_QT_DATA}"
OE_QMAKE_PATH_BINS = "${OE_QMAKE_PATH_QT_BINS}"
OE_QMAKE_PATH_TRANSLATIONS = "${OE_QMAKE_PATH_QT_TRANSLATIONS}"
OE_QMAKE_PATH_DOCS = "${OE_QMAKE_PATH_QT_DOCS}"
OE_QMAKE_PATH_SETTINGS = "${OE_QMAKE_PATH_QT_SETTINGS}"
OE_QMAKE_PATH_EXAMPLES = "${OE_QMAKE_PATH_QT_EXAMPLES}"
OE_QMAKE_PATH_TESTS = "${OE_QMAKE_PATH_QT_TESTS}"

# Regenerate header files when they are included in source tarball
# Otherwise cmake files don't set PRIVATE_HEADERS correctly
do_configure_prepend() {
    rm -rf ${S}/include
    mkdir -p ${S}/.git || true
}
