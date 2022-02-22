DESCRIPTION = "Qt 5 bindings for the ofono dbus API for Jolla's oFono extensions"
SECTION = "libs"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://src/qofonoext.cpp;;beginline=1;endline=14;md5=e78738e9230b2e0f55eb7f63e3444df5"

DEPENDS += "qtbase qtdeclarative qtxmlpatterns libqofono"

SRCREV = "ebe45e0fe46578c24e9fe241e84cd5ca0f097372"
SRC_URI = "git://github.com/sailfishos/libqofonoext.git;protocol=https;branch=master"
S = "${WORKDIR}/git"

PV = "1.027+gitr${SRCPV}"

inherit pkgconfig qmake5

do_install:append() {
    if ls ${D}${libdir}/pkgconfig/qofono-qt5.pc >/dev/null 2>/dev/null; then
        sed -i "s@-L${STAGING_LIBDIR}@-L\${libdir}@g" ${D}${libdir}/pkgconfig/qofono-qt5.pc
    fi
}

PACKAGES += "${PN}-tests"

FILES:${PN} += " \
    ${OE_QMAKE_PATH_QML}/org/nemomobile/ofono/qmldir \
    ${OE_QMAKE_PATH_QML}/org/nemomobile/ofono/plugins.qmltypes \
    ${OE_QMAKE_PATH_QML}/org/nemomobile/ofono/libqofonoextdeclarative.so \
"
