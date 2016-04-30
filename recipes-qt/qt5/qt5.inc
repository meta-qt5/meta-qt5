# Copyright (C) 2012, 2014 O.S. Systems Software LTDA.
# Copyright (C) 2013 Martin Jansa <martin.jansa@gmail.com>

inherit qmake5

PACKAGECONFIG_OPENSSL ?= "openssl"

PACKAGECONFIG[examples] = ""
EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'examples', 'QT_BUILD_PARTS+=examples', '', d)}"

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

OE_QMAKE_RECURSIVE = ""

# If Qt5 (qtbase) is machine specific, then everything will be,
# because the (initial) qtbase configuration becomes part of Qt5/qmake
python __anonymous() {
    barch = d.getVar("BUILD_ARCH", True) or ''
    tarch = d.getVar("TARGET_ARCH", True) or ''
    # do not do anything if we are building a native package
    if barch != tarch:
        tarch = d.getVar("QT_PACKAGES_ARCH", True) or ''
        if tarch:
            d.setVar("PACKAGE_ARCH", tarch)
}

# Regenerate header files when they are included in source tarball
# Otherwise cmake files don't set PRIVATE_HEADERS correctly
do_configure_prepend() {
    rm -rf ${S}/include
    mkdir -p ${S}/.git || true
}

# Many examples come with libraries installed outside of standard libdir,
# suppress QA check complaining
INSANE_SKIP_${PN}-dbg += "libdir"
INSANE_SKIP_${PN}-examples += "libdir"
INSANE_SKIP_${PN}-examples-dbg += "libdir"
INSANE_SKIP_${PN}-examples-dev += "libdir dev-elf"

PACKAGES =. "${PN}-qmlplugins-dbg ${PN}-tools-dbg ${PN}-plugins-dbg ${PN}-qmldesigner ${PN}-qmlplugins ${PN}-tools ${PN}-plugins ${PN}-mkspecs ${PN}-examples-dev ${PN}-examples-staticdev ${PN}-examples-dbg ${PN}-examples "

ALLOW_EMPTY_${PN} = "1"
ALLOW_EMPTY_${PN}-dbg = "1"

RRECOMMENDS_${PN} = " \
    ${PN}-plugins \
    ${PN}-qmlplugins \
    "
RRECOMMENDS_${PN}_class-native = ""

RRECOMMENDS_${PN}-dev = " \
    ${PN} \
    ${PN}-mkspecs \
    ${PN}-qmldesigner \
    "

RRECOMMENDS_${PN}-dbg = " \
    ${PN}-plugins-dbg \
    ${PN}-qmlplugins-dbg \
    "

# extra -dbg packages
FILES_${PN}-qmlplugins-dbg = " \
    ${OE_QMAKE_PATH_QML}/*/.debug \
    ${OE_QMAKE_PATH_QML}/*/*/.debug \
    ${OE_QMAKE_PATH_QML}/*/*/*/.debug \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/.debug \
    ${OE_QMAKE_PATH_IMPORTS}/*/.debug \
    ${OE_QMAKE_PATH_IMPORTS}/*/*/.debug \
    ${OE_QMAKE_PATH_IMPORTS}/*/*/*/.debug \
"
FILES_${PN}-tools-dbg = " \
    ${OE_QMAKE_PATH_BINS}/.debug \
"
FILES_${PN}-plugins-dbg = " \
    ${OE_QMAKE_PATH_PLUGINS}/*/.debug/* \
    ${OE_QMAKE_PATH_PLUGINS}/*/*/.debug/* \
    ${OE_QMAKE_PATH_PLUGINS}/*/*/*/.debug/* \
"

# extra packages
FILES_${PN}-qmldesigner += " \
    ${OE_QMAKE_PATH_QML}/*/designer \
    ${OE_QMAKE_PATH_QML}/*/*/designer \
"

# qmlplugins 1-4 levels of subdirs qmldir + *{.so,*.qmltypes,*.qml,*js}
FILES_${PN}-qmlplugins = " \
    ${OE_QMAKE_PATH_QML}/*.qmltypes \
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
    ${OE_QMAKE_PATH_QML}/*/*/*/images \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.qmltypes \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.qml \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.js \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.png \
    ${OE_QMAKE_PATH_QML}/*/*/*/*.metainfo \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/qmldir \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*.qmltypes \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*.qml \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*.js \
    ${OE_QMAKE_PATH_QML}/*/*/*/*/*/*.png \
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
    ${OE_QMAKE_PATH_PLUGINS}/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_PLUGINS}/*/*/*/*${SOLIBSDEV} \
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
    ${OE_QMAKE_PATH_HEADERS}/* \
"
FILES_${PN}-dbg += " \
    ${OE_QMAKE_PATH_LIBS}/.debug \
    ${OE_QMAKE_PATH_LIBEXECS}/.debug \
"
FILES_${PN}-staticdev += " \
    ${OE_QMAKE_PATH_LIBS}/*.a \
"
FILES_${PN}-examples = " \
    ${OE_QMAKE_PATH_EXAMPLES}/* \
"
FILES_${PN}-examples-dev = " \
    ${OE_QMAKE_PATH_EXAMPLES}/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/*/*/*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/*/*/*/*${SOLIBSDEV} \
"
FILES_${PN}-examples-dbg = " \
    ${OE_QMAKE_PATH_EXAMPLES}/.debug \
    ${OE_QMAKE_PATH_EXAMPLES}/*/.debug \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/.debug \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/.debug \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/.debug \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/*/.debug \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/*/*/.debug \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/*/*/*/.debug \
"
FILES_${PN}-examples-staticdev = " \
    ${OE_QMAKE_PATH_EXAMPLES}/*.a \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*.a \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*.a \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*.a \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/*.a \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/*/*.a \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/*/*/*.a \
    ${OE_QMAKE_PATH_EXAMPLES}/*/*/*/*/*/*/*/*.a \
"

PATH_prepend = "${STAGING_DIR_NATIVE}${OE_QMAKE_PATH_QT_BINS}:"
