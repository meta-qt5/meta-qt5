require qt5.inc

LICENSE = "GPLv2 & LGPLv2 & LGPLv2.1"
SECTION = "multimedia"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552 \
                    file://COPYING-LGPL-2;md5=5f30f0716dfdd0d91eb439ebec522ec2 \
                    file://COPYING-LGPL-2.1;md5=fbc093901857fcd118f065f900982c24"

DEPENDS += "qtbase qtdeclarative boost"

PACKAGECONFIG ??= "examples"
PACKAGECONFIG[examples] = "-DQTGSTREAMER_EXAMPLES=ON, -DQTGSTREAMER_EXAMPLES=OFF"
