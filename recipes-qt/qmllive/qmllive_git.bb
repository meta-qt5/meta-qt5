DESCRIPTION = "QmlLive is a live reloader environment for rapid UI development"
HOMEPAGE = "https://github.com/qtproject/qt-apps-qmllive"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE.GPL3;md5=75cd0dbc6f2d24e7eeb128ff59b74f4c"
DEPENDS = "qtbase qtdeclarative"

PV = "5.12+git${SRCPV}"

QT_GIT_PROJECT = "qt-apps"
SRC_URI = "${QT_GIT}/qmllive.git;branch=dev \
           file://0001-lib.pro-Append-LIB_ARCH-to-lib.patch \
          "
SRCREV = "0c7bf141b08aa9e757e91a4a05769257d043eab2"
S = "${WORKDIR}/git"

inherit pkgconfig qmake5

EXTRA_QMAKEVARS_PRE += "LIB_ARCH=${@d.getVar('baselib').replace('lib', '')}"
EXTRA_QMAKEVARS_POST = "QMAKE_RPATHDIR="

FILES:${PN}-dev += "${datadir}"
