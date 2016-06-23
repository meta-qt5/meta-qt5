require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & LGPL-2.1 | GPL-3.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=4193e7f1d47a858f6b7c0f1ee66161de \
    file://LICENSE.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

DEPENDS += "qtbase qtdeclarative udev gconf"

inherit bluetooth

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'bluetooth', 'bluez', '', d)}"
PACKAGECONFIG[bluez] = "CONFIG+=OE_BLUEZ_ENABLED,,${BLUEZ}"

EXTRA_QMAKEVARS_PRE += "${PACKAGECONFIG_CONFARGS}"

do_configure_prepend() {
    # disable bluez test if it isn't enabled by PACKAGECONFIG
    sed -i 's/^    qtCompileTest(bluez)/    OE_BLUEZ_ENABLED:qtCompileTest(bluez)/g' ${S}/qtsystems.pro
}

do_install_append() {
    # Remove example.pro file as it is useless
    rm -f ${D}${OE_QMAKE_PATH_EXAMPLES}/examples.pro	
}

QT_MODULE_BRANCH = "dev"

# qtsystems wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.6

SRCREV = "434af789f0d56ca7a521ca2d9ec8cf3b1057fd37"
