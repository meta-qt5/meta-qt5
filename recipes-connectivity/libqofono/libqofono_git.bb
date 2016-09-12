DESCRIPTION = "Qt 5 bindings for the ofono dbus API"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS += "qtbase qtdeclarative"

SRCREV = "54435de3bed0b454ef4ea3ea32c7234fcd9f24c6"
SRC_URI = "git://git.merproject.org/mer-core/libqofono.git"
S = "${WORKDIR}/git"

PV = "0.87+gitr${SRCPV}"

inherit qmake5

do_install_append() {
    if ls ${D}${libdir}/pkgconfig/qofono-qt5.pc >/dev/null 2>/dev/null; then
        sed -i "s@-L${STAGING_LIBDIR}@-L\${libdir}@g" ${D}${libdir}/pkgconfig/qofono-qt5.pc
    fi
}

PACKAGES += "${PN}-tests"

FILES_${PN}-dbg += " \
    /lib/libqofono-qt5/tests/.debug \
    ${libdir}/qt5/qml/MeeGo/QOfono/.debug \
"
FILES_${PN}-tests = " \
    ${libdir}/libqofono-qt5/tests/tst_* \
    /opt/tests/libqofono-qt5 \
"
FILES_${PN} += " \
    ${libdir}/qt5/qml/MeeGo/QOfono/qmldir \
    ${libdir}/qt5/qml/MeeGo/QOfono/libQOfonoQtDeclarative.so \
"
FILES_${PN}-dev += " \
    ${datadir}/qt5/mkspecs \
    ${libdir}/libqofono-qt5.prl \
"
