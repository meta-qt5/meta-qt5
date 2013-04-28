LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase-native"

QT_MODULE = "qtjsbackend"

INC_PR = "r0"

inherit native qmake5_base

SRC_URI += "\
    file://0001-Install-the-mkv8snapshot-tool-to-the-native-side.patch \
"

SEPB = "${WORKDIR}/build"
B = "${SEPB}"

do_configure() {
    # Similar logic is in autotools.bbclass
    if [ -d ${B} -a "${S}" != "${B}" ] ; then
        # Existing separate build directory, exists, remove
        rm -rf "${B}/*"
    fi

    # Avoid setting QMAKE_LINK from LD (since we want the linker to be g++)
    unset LD

    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} -r ${S}
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}
