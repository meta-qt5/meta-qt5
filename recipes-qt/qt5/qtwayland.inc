require qt5.inc

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

FILES_${PN}-plugins += " \
    ${OE_QMAKE_PATH_PLUGINS}/*/*/*${SOLIBSDEV} \
"

FILES_${PN}-plugins-dbg += " \
    ${OE_QMAKE_PATH_PLUGINS}/*/*/.debug/* \
"
