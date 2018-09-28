require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

DEPENDS += "qtbase qtdeclarative qtmultimedia"

SRCREV = "d87ebea3be105e8e5f46e096af5109b9d2eb7e0a"

# The same issue as in qtbase:
# http://errors.yoctoproject.org/Errors/Details/152641/
LDFLAGS_append_x86 = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"

PACKAGECONFIG ?= "qtquickcompiler"
