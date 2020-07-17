DESCRIPTION = "Qt 5 bindings for the ofono dbus API"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS += "qtbase qtdeclarative qtxmlpatterns"

SRCREV = "4eec0c726844b8293eeab7312c96956a77d40e90"
SRC_URI = "git://git.merproject.org/mer-core/libqofono.git \
           file://0001-also-emit-modemRemoved-and-modemAdded.patch \
"
S = "${WORKDIR}/git"

PV = "0.100+gitr${SRCPV}"

inherit qmake5

do_install_append() {
    if ls ${D}${libdir}/pkgconfig/qofono-qt5.pc >/dev/null 2>/dev/null; then
        sed -i "s@-L${STAGING_LIBDIR}@-L\${libdir}@g" ${D}${libdir}/pkgconfig/qofono-qt5.pc
    fi
}

PACKAGES += "${PN}-tests"

FILES_${PN}-tests = " \
    ${libdir}/libqofono-qt5/tests/tst_* \
    /opt/examples/libqofono-qt5/ \
    /opt/tests/libqofono-qt5 \
"
FILES_${PN} += " \
    ${OE_QMAKE_PATH_QML}/MeeGo/QOfono/qmldir \
    ${OE_QMAKE_PATH_QML}/MeeGo/QOfono/plugins.qmltypes \
    ${OE_QMAKE_PATH_QML}/MeeGo/QOfono/libQOfonoQtDeclarative.so \
"
FILES_${PN}-dev += " \
    ${OE_QMAKE_PATH_ARCHDATA}/mkspecs \
    ${libdir}/libqofono-qt5.prl \
    ${datadir}/qt5/mkspecs \
"
