require qt5.inc
require qt5-git.inc

inherit pkgconfig

LICENSE = "GPL-3.0-only | LGPL-3.0-only | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=c4fe8c6de4eef597feec6e90ed62e962 \
    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
"

DEPENDS += "qtbase qtxmlpatterns qtdeclarative"

PACKAGECONFIG ??= "sdl2"
PACKAGECONFIG[sdl2] = "-feature-sdl2,-no-feature-sdl2,libsdl2"

EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

SRCREV = "44255e2ae53a14e9a3fb671da0782ec5d396220a"
