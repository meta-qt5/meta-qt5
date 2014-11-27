require qt5.inc

DEPENDS += "qtbase qtdeclarative udev bluez4 gconf"

LICENSE = "BSD & LGPL-2.1 & GFDL-1.3"

do_install_append() {
    # Remove example.pro file as it is useless
    rm -f ${D}${OE_QMAKE_PATH_EXAMPLES}/examples.pro	
}
