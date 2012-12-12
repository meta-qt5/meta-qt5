require qt5.inc

SRC_URI += " \
  file://0001-qlibraryinfo-Allow-the-specification-of-where-to-loa.patch \
  file://0002-qmake-is-already-built-in-qt5-tools-native.patch \
  file://0003-Allow-building-a-separate-qmake-for-the-target.patch \
"

# to provide xcb/xcb_icccm.h, xcb/xcb_image.h, xcb/xcb_keysyms.h and dbus/dbus.h
# DEPENDS += "xcb-util-wm xcb-util-image xcb-util-keysyms dbus"
# reenable when we switch to "-qt-xcb" and build of 
# src/platformsupport/glxconvenience/ is resolved to be before build of
# src/plugins/platforms/xcb/

QT_MODULE_FLAGS = "-no-xcb"

INC_PR = "r0"

QT_BASE_NAME = "qt5"
QT_DIR_NAME = "qt5"

# Qt uses atomic instructions not supported in thumb mode
ARM_INSTRUCTION_SET = "arm"

inherit qmake5
