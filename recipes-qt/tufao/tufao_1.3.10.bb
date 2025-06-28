SUMMARY = "An asynchronous web framework for C++ built on top of Qt"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=4fbd65380cdd255951079008b364516c"
DEPENDS = "qtbase"

SRCREV = "ad505c32d0ba14c3c616de8ba4c7eb79396c20a6"
SRC_URI = "git://github.com/vinipsmaker/tufao.git;protocol=http;branch=1.x;protocol=https \
    file://build-Fix-mkspecs-and-CMake-module-install-directori.patch \
    file://0001-CMakeLists.txt-fix-build-with-Qt-5.11-don-t-use-qt5_.patch \
"

# This includes bugfixes from 1.x branch
PV:append = "+${SRCPV}"


inherit cmake_qt5

PACKAGES += "${PN}-mkspecs"

FILES:${PN}-mkspecs = "\
    ${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs \
"

FILES:${PN}-dev += " \
    ${OE_QMAKE_PATH_LIBS}/lib*${SOLIBSDEV} \
    ${OE_QMAKE_PATH_LIBS}/pkgconfig \
    ${OE_QMAKE_PATH_LIBS}/cmake/* \
    ${OE_QMAKE_PATH_LIBS}/*.prl \
    ${OE_QMAKE_PATH_LIBS}/*.la \
    ${OE_QMAKE_PATH_DATA}/* \
    ${OE_QMAKE_PATH_HEADERS}/* \
"
