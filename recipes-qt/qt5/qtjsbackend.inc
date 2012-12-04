LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase"

INC_PR = "r0"

inherit qmake5

SRC_URI += " \
    file://qmake.conf \
	file://0002-Make-sure-that-we-pick-up-the-mkv8snapshot-tool.patch \
	"

# Bitbake will not respect the make order set by qmake and at times it will try to compile
# parts of the source tree with out the 'mkv8snapshot' tool if it is enabled and that will fail
PARALLEL_MAKE = ""

export QT_CONF_PATH="${WORKDIR}/qt.conf"

do_configure () {
	# Avoid setting QMAKE_LINK from LD (since we want the linker to be g++)
	unset LD

	# This should not be needed. Perhaps the lack of this file is an indication
    # of an error on the native recipe...
    cp ${WORKDIR}/qmake.conf ${S}/.qmake.conf

    ${OE_QMAKE_QMAKE} -d
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}

FILES_${PN}-dev += "\
        ${STAGING_INCDIR}/qt5 \
        ${STAGING_DATADIR}/qt5 \
        ${libdir}/*.prl \
"
