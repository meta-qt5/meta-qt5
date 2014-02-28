require qt5.inc

SRC_URI += " \
    file://0001-qmltestexample-fix-link.patch \
    file://0003-Fix-wrong-calculation-of-viewPort-for-transitions.patch \
"

DEPENDS += "qtbase"

PACKAGECONFIG ??= "qtxmlpatterns"
PACKAGECONFIG[qtxmlpatterns] = ",,qtxmlpatterns"

do_configure_prepend() {
    # disable qtxmlpatterns test if it isn't enabled by PACKAGECONFIG
    sed -e 's/^\(qtHaveModule(xmlpatterns)\)/OE_QTXMLPATTERNS_ENABLED:\1/' -i ${S}/src/imports/imports.pro
    sed -e 's/^\(!qtHaveModule(xmlpatterns)\)/!OE_QTXMLPATTERNS_ENABLED|\1/' -i ${S}/tests/auto/quick/quick.pro
}

EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'qtxmlpatterns', 'CONFIG+=OE_QTXMLPATTERNS_ENABLED', '', d)}"
