DESCRIPTION = "This driver extends Qt's platform support (QPA) for Virtualbox guests. \
It uses the integrated pointer feature to create a smooth conversion from \
the host pointer to touchscreen events in the guest, without grabbing the \
host pointer."
SUMMARY = "Touchscreen driver for integrated mouse pointer in VirtualBox"
LICENSE = "LGPL-2.1-only & GPL-3.0-only"
LIC_FILES_CHKSUM = " \
    file://vboxtouch.cpp;beginline=1;endline=22;md5=ca51db8f7c0606c77f702dcee4cf31d9 \
    file://evdevmousehandler.cpp;beginline=1;endline=40;md5=9081062f6e7f74b6e62ad7ecee4a71be \
"

PV = "1.1.4+gitr${SRCPV}"

DEPENDS = "qtbase"

# Needed with gcc-5.2 https://gcc.gnu.org/bugzilla/show_bug.cgi?id=65801
CXXFLAGS += "-Wno-narrowing"

SRC_URI = "git://github.com/nemomobile/qt5-plugin-generic-vboxtouch.git;branch=master;protocol=https \
           file://0001-VirtualboxTouchScreenHandler-initialize-m_mouse.patch;patchdir=.. \
           file://0001-include-errno.h-for-errno-definition.patch;patchdir=.. \
           "
SRCREV = "3f2bdb5a1d346f02d5ab185522271ba2288a42bb"
S = "${WORKDIR}/git/vboxtouch"

inherit qmake5

FILES:${PN} += "${OE_QMAKE_PATH_PLUGINS}/generic/libvboxtouchplugin.so"
FILES:${PN}-dev += "${OE_QMAKE_PATH_LIBS}/cmake/*"

