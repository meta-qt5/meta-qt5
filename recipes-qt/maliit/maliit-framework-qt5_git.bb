DESCRIPTION = "A virtual keyboard for touch-screen based user interfaces"
HOMEPAGE = "https://wiki.maliit.org/Main_Page"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=5c917f6ce94ceb8d8d5e16e2fca5b9ad"

inherit qmake5

SRC_URI = "git://github.com/maliit/framework.git;branch=master \
    file://0001-Fix-MALIIT_INSTALL_PRF-to-allow-the-build-with-opene.patch \
    file://maliit-server.desktop \
"

SRCREV = "17fdf8699c53ddfb2c15df8e11d46804e782fec5"
PV = "0.99.0+git${SRCPV}"


PACKAGES =+ "${PN}-gtk"
GTKIMMODULES_PACKAGES = "${PN}-gtk"

DEPENDS = "qtdeclarative"

# FIXME: Do we need something like this with qt5?
#RDEPENDS_${PN} = "qt4-plugin-inputmethod-imsw-multi libqtsvg4"

RRECOMMENDS_${PN} = "maliit-plugins-qt5"

FILES_${PN} += "\
    ${libdir}/*.so* \
    ${bindir} \
    ${datadir}/applications/maliit-server.desktop \
    ${datadir}/dbus-1 \
"

FILES_${PN}-dbg += "\
    ${libdir}/maliit-framework-tests \
"

FILES_${PN}-dev += "\
    ${includedir}/maliit \
    ${libdir}/pkgconfig \
    ${libdir}/qt5/mkspecs \
"

EXTRA_QMAKEVARS_PRE = "\
    PREFIX=${prefix} \
    LIBDIR=${libdir} \
    DATADIR=${datadir} \
    QT_IM_PLUGIN_PATH=${libdir}/qt4/plugins/inputmethods \
    MALIIT_INSTALL_PRF=${QMAKE_MKSPEC_PATH}/mkspecs/features \
    SCHEMADIR=${sysconfdir}/gconf/schemas \
    CONFIG+=disable-gconf \
    CONFIG+=disable-gtk-cache-update \
    CONFIG+=local-install \
    CONFIG+=nosdk \
    CONFIG+=nodoc \
    CONFIG+=noxcb \
    CONFIG+=enable-dbus-activation \
"

EXTRA_OEMAKE += "INSTALL_ROOT=${D}"

do_install_append() {
    #Fix absolute paths
    sed -i -e "s|/usr|${STAGING_DIR_TARGET}${prefix}|" ${D}/${libdir}/${QT_DIR_NAME}/mkspecs/features/maliit-framework.prf
    sed -i -e "s|/usr|${STAGING_DIR_TARGET}${prefix}|" ${D}/${libdir}/${QT_DIR_NAME}/mkspecs/features/maliit-plugins.prf

    install -d ${D}${datadir}/applications
    install -m 644 ${WORKDIR}/maliit-server.desktop ${D}${datadir}/applications
}

pkg_postinst_${PN} () {
#!/bin/sh
# should run online
if [ "x$D" != "x" ]; then
    exit 1
fi
echo "export QT_IM_MODULE=Maliit" >> /etc/xprofile
ln -s /usr/share/applications/maliit-server.desktop /etc/xdg/autostart/maliit-server.desktop
}

pkg_postrm_${PN} () {
#!/bin/sh
# should run online
if [ "x$D" = "x" ]; then
    exit 1
fi
if [ -e "/etc/xprofile" ]; then
    sed -i -e "g|export QT_IM_MODULE=Maliit|d" /etc/xprofile
fi
rm -f /etc/xdg/autostart/maliit-server.desktop
}

S = "${WORKDIR}/git"
