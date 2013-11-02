require qt5.inc

LICENSE = "BSD & LGPL-2.1 | GPL-3.0"
LIC_FILES_CHKSUM = "file://${S}/src/compositor/compositor_api/qwaylandsurfacenode.cpp;endline=40;md5=e38eccd7e9a6a149c9e31b6fb47daac1 \
                    file://${S}/src/compositor/compositor_api/qwaylandcompositor.cpp;endline=39;md5=8897abe415f27031e4237f09db7b9a36 \
"

# wayland-native is already in wayland DEPENDS, but add it here
# explicitly, because it's native wayland-scanner we're looking for
# libxkbcommon isn't mandatory make it easier to remove by .bbappend
# (e.g. for building qtwayland with danny which doesn't have libxkbcommon in oe-core).
XKB_DEPENDS = "libxkbcommon xproto"
DEPENDS += "qtbase qtdeclarative wayland wayland-native qtwayland-native ${XKB_DEPENDS}"

QT_WAYLAND_CONFIG ?= "wayland-compositor"
QT_WAYLAND_DEFINES ?= ""
QT_WAYLAND_BUILD_PARTS ?= "examples"

EXTRA_QMAKEVARS_PRE += "CONFIG+=${QT_WAYLAND_CONFIG}"
EXTRA_QMAKEVARS_PRE += "DEFINES+=${QT_WAYLAND_DEFINES}"
EXTRA_QMAKEVARS_PRE += "QT_BUILD_PARTS+=${QT_WAYLAND_BUILD_PARTS}"
