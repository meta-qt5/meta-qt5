LICENSE = "GFDL-1.3 & BSD & ( GPL-2.0+ | LGPL-3.0 ) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.GPLv2;md5=c96076271561b0e3785dad260634eaa8 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.LGPLv3;md5=e0459b45c5c4840b353141a8bbed91f0 \
"

require qt5.inc
require qt5-git.inc

DEPENDS += "qtbase qtwebengine"

COMPATIBLE_MACHINE = "(-)"
COMPATIBLE_MACHINE_x86 = "(.*)"
COMPATIBLE_MACHINE_x86-64 = "(.*)"
COMPATIBLE_MACHINE_armv6 = "(.*)"
COMPATIBLE_MACHINE_armv7a = "(.*)"
COMPATIBLE_MACHINE_armv7ve = "(.*)"
COMPATIBLE_MACHINE_aarch64 = "(.*)"

# Use a3b5e85c924008e1968975825a0e61b040d6d88b
# instead of 6ea20e8930b02928a20173c1718ba5efd7b24dec because
# v5.9.8 tag wasn't merged to 5.9 branch yet
SRCREV = "a3b5e85c924008e1968975825a0e61b040d6d88b"
