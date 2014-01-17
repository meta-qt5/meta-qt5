require qt5.inc

DEPENDS += "qtbase"

SRC_URI += " \
    file://0002-assistant-help-fix-linking-of-dependent-libraries.patch \
"

FILES_${PN}-examples = "${datadir}/${QT_DIR_NAME}/examples"
