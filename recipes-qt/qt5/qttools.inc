require qt5.inc

DEPENDS += "qtbase qtdeclarative qtxmlpatterns"

SRC_URI += " \
    file://0002-assistant-help-fix-linking-of-dependent-libraries.patch \
    file://0003-add-noqtwebkit-configuration.patch \
"

FILES_${PN}-tools += "${datadir}/${QT_DIR_NAME}/phrasebooks"
FILES_${PN}-examples = "${datadir}/${QT_DIR_NAME}/examples"

PACKAGECONFIG ??= ""
PACKAGECONFIG[qtwebkit] = ",,qtwebkit"

EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'qtwebkit', '', 'CONFIG+=noqtwebkit', d)}"
