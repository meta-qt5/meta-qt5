require qt5.inc
require qt5-git.inc

# There are no LGPLv3-only licensed files in this component.
LICENSE = "BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
    file://LICENSE.LGPLv3;md5=b8c75190712063cde04e1f41b6fdad98 \
    file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

# wayland-native is already in wayland DEPENDS, but add it here
# explicitly, because it's native wayland-scanner we're looking for
# libxkbcommon isn't mandatory make it easier to remove by .bbappend
# (e.g. for building qtwayland with danny which doesn't have libxkbcommon in oe-core).
XKB_DEPENDS = "libxkbcommon xproto"
DEPENDS += "qtbase qtdeclarative wayland wayland-native qtwayland-native ${XKB_DEPENDS}"

QT_WAYLAND_CONFIG ?= "wayland-compositor"
QT_WAYLAND_DEFINES ?= ""

EXTRA_QMAKEVARS_PRE += "CONFIG+=${QT_WAYLAND_CONFIG}"
EXTRA_QMAKEVARS_PRE += "DEFINES+=${QT_WAYLAND_DEFINES}"

FILES_${PN}-plugins += " \
    ${OE_QMAKE_PATH_PLUGINS}/*/*/*${SOLIBSDEV} \
"

FILES_${PN}-plugins-dbg += " \
    ${OE_QMAKE_PATH_PLUGINS}/*/*/.debug/* \
"

SRC_URI += " \
    file://0001-examples-wayland-include-server-buffer-only-when-bui.patch \
"

SRCREV = "74b1ec474d1d48242893dcaf58b8a35f155f3fc3"
