require qt5.inc
require qt5-git.inc

LICENSE = "(GPL-3.0 & BSD) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

DEPENDS += "qtbase qtdeclarative qtquickcontrols2"

SRC_URI += " \
    git://github.com/assimp/assimp.git;name=assimp;branch=master;protocol=https;destsuffix=git/src/3rdparty/assimp/src \
"

PACKAGECONFIG ??= ""
PACKAGECONFIG[system-assimp] = "-system-assimp,-qt-assimp,assimp"

EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

FILES_${PN}-qmlplugins += " \
  ${OE_QMAKE_PATH_QML}/QtQuick3D/Helpers/meshes/*.mesh \
"

SRCREV_qtquick3d = "a0197a18cebfb718c1787fcd8779bcf237f97137"
SRCREV_assimp = "5c900d689a5db5637b98f665fc1e9e9c9ed416b9"

SRCREV_FORMAT = "qtquick3d_assimp"
