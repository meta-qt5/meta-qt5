DESCRIPTION = "Qt component for application lifecycle management"
LICENSE = "(GFDL-1.3 & The-Qt-Company-GPL-Exception-1.0 & (LGPL-3.0-only | GPL-2.0-or-later)) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = "file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504"

require qt5.inc
require qt5-git.inc

inherit pkgconfig

SRC_URI += "file://0001-Fix-build-with-disabled-installer.-Inspired-by-simil.patch"

DEPENDS += "qtbase qtdeclarative libyaml libarchive qtapplicationmanager-native"
RDEPENDS:${PN}:class-target = "libcrypto ${PN}-tools"

PACKAGECONFIG ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'multi-process', '', d)}"

PACKAGECONFIG[installer] = ",-config disable-installer,openssl"
PACKAGECONFIG[tools-only] = "-config tools-only"
PACKAGECONFIG[multi-process] = "-config force-multi-process,, qtwayland qtwayland-native"

EXTRA_QMAKEVARS_PRE += "${PACKAGECONFIG_CONFARGS}"

PACKAGECONFIG:class-native ??= "tools-only"
PACKAGECONFIG:class-nativesdk ??= "${PACKAGECONFIG:class-native}"

# AppMan built tested with the following commit from branch 5.15
SRCREV = "e3cd0205f08aa1ab780bd9c724cd6345fa9cb9e8"

BBCLASSEXTEND = "nativesdk native"
