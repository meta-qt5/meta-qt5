require qt5.inc

# doesn't have GFDL-1.3 like qtbase, LICENSE files are missing in 5.0.0 and 5.0.1, this is for 5.0.2 and git
LICENSE = "LGPL-2.1 | GPL-3.0"
LIC_FILES_CHKSUM ?= "file://LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de \
                     file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                     file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
"

DEPENDS = "qtbase qtjsbackend-native"

INC_PR = "r0"

inherit qmake5

SRC_URI += " \
    file://0002-v8.pro-respect-external-host-bindir-when-set.patch \
"

do_configure_append () {
    ${OE_QMAKE_QMAKE} ${OE_QMAKE_DEBUG_OUTPUT} -r ${S}
}
