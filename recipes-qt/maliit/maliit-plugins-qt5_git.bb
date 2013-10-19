DESCRIPTION = "Plugins for a virtual keyboard for touch-screen based user interfaces"
HOMEPAGE = "https://wiki.maliit.org/Main_Page"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f29b21caa8e460097bfad9c026a33621"

inherit qmake5

# Set path of qt5 headers as qmake5_base.bbclass sets this to just ${includedir}
# but
# actually it is ${includedir}/qt5
OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

DEPENDS = "maliit-framework-qt5"

RDEPENDS_${PN} += "qtsvg-plugins"

SRC_URI = "git://github.com/maliit/plugins.git;branch=master"

SRCREV = "c6a348592607248a771a3dde5a0e33dc3c433a2a"
PV = "0.99.0+git${SRCPV}"

EXTRA_QMAKEVARS_PRE = "\
    PREFIX=${prefix} \
    MALIIT_INSTALL_PRF=${QMAKE_MKSPEC_PATH}/mkspecs/features \
    MALIIT_PLUGINS_DATA_DIR=${datadir} \
    LIBDIR=${libdir} \
    CONFIG+=nodoc \
"

FILES_${PN} += "\
    ${libdir}/maliit \
    ${datadir} \
"

FILES_${PN}-dbg += "${libdir}/maliit/plugins/.debug"

S= "${WORKDIR}/git"

EXTRA_OEMAKE += "INSTALL_ROOT=${D}"
