require qt5.inc
require qt5-git.inc

LICENSE = "(GPL-3.0-only & BSD-3-Clause) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

DEPENDS += "qtbase qtdeclarative qtquickcontrols2"

SRC_URI += " \
    git://code.qt.io/qt/qtquick3d-assimp.git;name=assimp;branch=qt6_assimp;protocol=https;destsuffix=git/src/3rdparty/assimp/src \
"

PACKAGECONFIG ??= ""
PACKAGECONFIG[system-assimp] = "-system-quick3d-assimp,-qt-quick3d-assimp,assimp"

EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

FILES:${PN}-qmlplugins += " \
  ${OE_QMAKE_PATH_QML}/QtQuick3D/Helpers/meshes/*.mesh \
"

SRCREV_qtquick3d = "a78f1c57dfb3459be43c856d6bda16f3dfdc3b15"
SRCREV_assimp = "8f0c6b04b2257a520aaab38421b2e090204b69df"

SRCREV_FORMAT = "qtquick3d_assimp"
