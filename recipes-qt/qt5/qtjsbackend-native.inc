# doesn't have GFDL-1.3 like qtbase, LICENSE files are missing in 5.0.0 and 5.0.1, this is for 5.0.2 and git
LICENSE = "LGPL-2.1 | GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de \
                    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
"

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

    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} -r ${S}
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}
