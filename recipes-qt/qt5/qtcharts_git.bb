require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

DEPENDS += "qtbase qtdeclarative qtmultimedia"

# Use f7fb6e5b3c321c824d597363504cae6cf7d37c43
# instead of ff7e0b5106a847541e13decfd8cbc57985d001b7 because
# v5.9.8 tag wasn't merged to 5.9 branch yet
SRCREV = "f7fb6e5b3c321c824d597363504cae6cf7d37c43"

# The same issue as in qtbase:
# http://errors.yoctoproject.org/Errors/Details/152641/
LDFLAGS_append_x86 = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"
