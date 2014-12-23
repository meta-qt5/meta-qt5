DESCRIPTION = "This driver extends Qt's platform support (QPA) for Virtualbox guests. \
It uses the integrated pointer feature to create a smooth conversion from \
the host pointer to touchscreen events in the guest, without grabbing the \
host pointer."
SUMMARY = "Touchscreen driver for integrated mouse pointer in VirtualBox"
LICENSE = "LGPL-2.1 & GPL-3.0"
LIC_FILES_CHKSUM = " \
    file://vboxtouch.cpp;beginline=1;endline=22;md5=ca51db8f7c0606c77f702dcee4cf31d9 \
    file://evdevmousehandler.cpp;beginline=1;endline=40;md5=9081062f6e7f74b6e62ad7ecee4a71be \
"

PV = "1.0+gitr${SRCPV}"

DEPENDS = "qtbase"

SRC_URI = "git://github.com/nemomobile/qt5-plugin-generic-vboxtouch.git"
SRCREV = "7d7a6dfd501c43687e7bb110610cac5f7c9abd4b"
S = "${WORKDIR}/git/vboxtouch"

inherit qmake5

FILES_${PN} += "${libdir}/qt5/plugins/generic/libvboxtouchplugin.so"
FILES_${PN}-dbg += "${libdir}/qt5/plugins/generic/.debug/"
