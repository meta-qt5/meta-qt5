require qt5.inc

SRC_URI += " \
  file://0001-Allowing-the-customization-of-the-paths.patch \
  file://0002-qmake-is-already-built-in-qt5-tools-native.patch \
  file://0003-Allow-building-a-separate-qmake-for-the-target.patch \
"
#file://0001-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch

# to provide xcb/xcb_icccm.h, xcb/xcb_image.h, xcb/xcb_keysyms.h
DEPENDS += "xcb-util-wm xcb-util-image xcb-util-keysyms"

QT_MODULE_FLAGS ?= "-system-xcb"

INC_PR = "r0"

QT_BASE_NAME = "qt5"
QT_DIR_NAME = "qt5"

export QMAKESPEC = "${STAGING_DATADIR}/${QT_DIR_NAME}/mkspecs/${TARGET_OS}-oe-g++"

# Qt uses atomic instructions not supported in thumb mode
ARM_INSTRUCTION_SET = "arm"

inherit qmake5
