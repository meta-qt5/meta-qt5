require qt5.inc

DEPENDS += "qtbase qtdeclarative"

PACKAGECONFIG ??= ""
PACKAGECONFIG[bluez4] = ",,bluez4"

do_configure_prepend() {
    # disable bluez test if it isn't enabled by PACKAGECONFIG
    sed -i 's/^qtCompileTest(bluez)/OE_BLUEZ_ENABLED:qtCompileTest(bluez)/g' ${S}/qtconnectivity.pro
}

EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'bluez4', 'CONFIG+=OE_BLUEZ_ENABLED', '', d)}"

LICENSE += "| GPL-2.0"
LIC_FILES_CHKSUM += "file://LICENSE.GPLv2;md5=e782f55badfa137e5e59c330f12cc8ed"
