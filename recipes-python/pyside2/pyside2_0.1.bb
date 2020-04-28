LICENSE = "Unknown & GPLv2 & GPLv3"
# generated, likely needs review
LIC_FILES_CHKSUM = "file://COPYING;md5=15a1ca44f90f3ab457d6a4fe7c0f3a19"
#LIC_FILES_CHKSUM = "file://LICENSE.LGPLv3;md5=8211fde12cc8a4e2477602f5953f5b71"

SRC_URI = "gitsm://code.qt.io/pyside/pyside-setup.git;protocol=http;branch=5.13"


# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "f5265a2ab8b19b181e7e7b8175a9598cf2a1fbc9"

S = "${WORKDIR}/git/sources/pyside2"

inherit distutils3

DEPENDS = "qtbase shiboken2-generator-native shiboken2-shibokenmodule"
RDEPENDS_${PN} = "qtbase"

inherit cmake_qt5 pkgconfig 

OECMAKE_GENERATOR = "Unix Makefiles"

EXTRA_OECMAKE = " \
    -DCMAKE_BUILD_TYPE=Release \
    -DBUILD_TESTS=OFF \
    -DUSE_PYTHON_VERSION=3 \
    -DPYTHON_EXECUTABLE="${PYTHON}" \
"

# depends on meta-clang
TOOLCHAIN = "clang"

