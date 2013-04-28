require qt5.inc

# FIXME!!!
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase qtjsbackend-native"

INC_PR = "r0"

inherit qmake5

SRC_URI += " \
    file://0002-v8.pro-respect-external-host-bindir-when-set.patch \
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
    # everything except HostData and HostBinaries is prefixed with sysroot value,
    # but we cannot remove sysroot override, because that's useful for pkg-config etc
    cp -ra ${D}${STAGING_DIR_TARGET}/* ${D}
    rm -rf ${D}${STAGING_DIR_TARGET}
    # remove empty dirs
    TMP=`dirname ${D}/${STAGING_DIR_TARGET}`
    while test ${TMP} != ${D}; do
        rmdir ${TMP}
        TMP=`dirname ${TMP}`;
    done
}
