DESCRIPTION = "A virtual keyboard for touch-screen based user interfaces"
HOMEPAGE = "https://wiki.maliit.org/Main_Page"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=5c917f6ce94ceb8d8d5e16e2fca5b9ad"

inherit qmake5 qmake5_paths

SRC_URI = "git://github.com/maliit/framework.git;branch=master \
    file://0001-Fix-MALIIT_INSTALL_PRF-to-allow-the-build-with-opene.patch \
    file://maliit-server.desktop \
"

SRCREV = "60b1b10de14f932420313c547ab801daf522d539"
PV = "0.99.0+git${SRCPV}"


PACKAGES =+ "${PN}-gtk"
GTKIMMODULES_PACKAGES = "${PN}-gtk"

DEPENDS = "qtdeclarative"

RRECOMMENDS_${PN} = "maliit-plugins-qt5"

FILES_${PN} += "\
    ${libdir}/*.so* \
    ${bindir} \
    ${datadir}/applications/maliit-server.desktop \
    ${datadir}/dbus-1 \
    ${OE_QMAKE_PATH_PLUGINS}/platforminputcontexts \
"

FILES_${PN}-dbg += "\
    ${libdir}/maliit-framework-tests \
    ${OE_QMAKE_PATH_PLUGINS}/platforminputcontexts/.debug \
"

FILES_${PN}-dev += "\
    ${includedir}/maliit \
    ${libdir}/pkgconfig \
    ${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs \
"

EXTRA_QMAKEVARS_PRE = "\
    PREFIX=${OE_QMAKE_PATH_PREFIX} \
    LIBDIR=${OE_QMAKE_PATH_LIBS} \
    DATADIR=${OE_QMAKE_PATH_DATA} \
    QT_INSTALL_PLUGINS=${OE_QMAKE_PATH_PLUGINS} \
    MALIIT_INSTALL_PRF=${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/features \
    SCHEMADIR=${sysconfdir}/gconf/schemas \
    CONFIG+=disable-gconf \
    CONFIG+=disable-gtk-cache-update \
    CONFIG+=local-install \
    CONFIG+=nosdk \
    CONFIG+=nodoc \
    CONFIG+=noxcb \
    CONFIG+=enable-dbus-activation \
    CONFIG+=qt5-inputcontext \
"

EXTRA_OEMAKE += "INSTALL_ROOT=${D}"

do_install_append() {
    #Fix absolute paths
    sed -i -e "s|/usr|${STAGING_DIR_TARGET}${prefix}|" ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/features/maliit-framework.prf
    sed -i -e "s|/usr|${STAGING_DIR_TARGET}${prefix}|" ${D}/${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/features/maliit-plugins.prf

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
