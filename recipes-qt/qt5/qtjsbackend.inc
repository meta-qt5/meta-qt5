require qt5.inc

# FIXME!!!
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase qtjsbackend-native"

INC_PR = "r0"

inherit qmake5

SRC_URI += " \
    file://0002-Make-sure-that-we-pick-up-the-mkv8snapshot-tool.patch \
"

# Bitbake will not respect the make order set by qmake and at times it will try to compile
# parts of the source tree with out the 'mkv8snapshot' tool if it is enabled and that will fail
PARALLEL_MAKE = ""

do_configure () {
    # Avoid setting QMAKE_LINK from LD (since we want the linker to be g++)
    unset LD

    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} -r ${S}
}

do_install_append () {
    mv ${D}${QMAKE_MKSPEC_PATH}/mkspecs ${D}${libdir}/mkspecs
    TMP=`dirname ${D}/${QMAKE_MKSPEC_PATH}/mkspecs`
    while test ${TMP} != ${D}; do
        rmdir ${TMP}
        TMP=`dirname ${TMP}`;
    done
}
