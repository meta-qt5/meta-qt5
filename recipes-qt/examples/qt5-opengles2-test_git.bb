SUMMARY = "Qt5 OpenGL ES 2.0 Test Application"
DESCRIPTION = "This application is used to test OpenGL ES 2.0 rendering in \ \
a simple QWindow, plus multi-touch input, window orientation, \
window focus handling and some other game-related features."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/main.cpp;beginline=1;endline=26;md5=93b83ece006c9e76b9fca80c3aecb169"

PV = "1.0.4+gitr${SRCPV}"

DEPENDS = "qtbase qtsensors"

# Depends on gles2 enabled and that's not default configuration
EXCLUDE_FROM_WORLD = "1"

SRC_URI = "git://github.com/thp/qt5-opengles2-test.git"
SRCREV = "938390507054ed1258345f70ed55770d2fe56176"
S = "${WORKDIR}/git"

inherit qmake5
