LICENSE = "Unknown & GPLv2 & GPLv3"

SRC_URI = "gitsm://code.qt.io/pyside/pyside-setup.git;protocol=http;branch=5.13"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "f5265a2ab8b19b181e7e7b8175a9598cf2a1fbc9"

S = "${WORKDIR}/git"

inherit distutils3

RDEPENDS_${PN} += "shiboken2"

inherit cmake_qt5 pkgconfig

# depends on meta-clang
TOOLCHAIN = "clang"

