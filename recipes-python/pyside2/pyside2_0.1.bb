LICENSE = "Unknown & GPLv2 & GPLv3"

# generated, likely needs review
LIC_FILES_CHKSUM = "file://COPYING.libshiboken;md5=15a1ca44f90f3ab457d6a4fe7c0f3a19 \
                    file://COPYING;md5=34337af480a8c452bfafe22a78fa20cb \
                    file://COPYING.libsample;md5=15a1ca44f90f3ab457d6a4fe7c0f3a19 \
                    file://ApiExtractor/COPYING;md5=34337af480a8c452bfafe22a78fa20cb \
                    file://libshiboken/embed/qt_python_license.txt;md5=72805479576a4498d9e94f8a9904a6a4"


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

