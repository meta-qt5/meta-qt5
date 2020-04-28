LICENSE = "Unknown & GPLv2 & GPLv3"
# generated, likely needs review
LIC_FILES_CHKSUM = "file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
                    file://LICENSE.GPLv3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
                    file://LICENSE.COMMERCIAL;md5=c4722b66015d0f67c8cba8a874ffb2ea \
                    file://LICENSE.LGPLv3;md5=8211fde12cc8a4e2477602f5953f5b71 \
                    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
                    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://sources/pyside2-tools/LICENSE-lupdate;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://sources/pyside2-tools/LICENSE-rcc;md5=34337af480a8c452bfafe22a78fa20cb \
                    file://sources/pyside2-tools/LICENSE-uic;md5=657070d8ec161791cb1f59604c6a5e87 \
                    file://sources/shiboken2/COPYING.libsample;md5=15a1ca44f90f3ab457d6a4fe7c0f3a19 \
                    file://sources/shiboken2/COPYING.libshiboken;md5=15a1ca44f90f3ab457d6a4fe7c0f3a19 \
                    file://sources/shiboken2/COPYING;md5=34337af480a8c452bfafe22a78fa20cb \
                    file://sources/shiboken2/ApiExtractor/COPYING;md5=34337af480a8c452bfafe22a78fa20cb \
                    file://sources/shiboken2/libshiboken/embed/qt_python_license.txt;md5=72805479576a4498d9e94f8a9904a6a4 \
                    file://sources/pyside2/COPYING;md5=15a1ca44f90f3ab457d6a4fe7c0f3a19 \
                    file://sources/pyside2/doc/codesnippets/examples/dialogs/licensewizard/licensewizard.qrc;md5=d80837ba2fd9befb01ed460000400b1b \
                    file://sources/pyside2/doc/codesnippets/examples/dialogs/licensewizard/licensewizard.h;md5=f94e596bec27ab847c00600618dde57e \
                    file://sources/pyside2/PySide2/licensecomment.txt;md5=928051111bd61c7e069b675f64917ef3 \
                    file://sources/patchelf/COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRC_URI = "gitsm://code.qt.io/pyside/pyside-setup.git;protocol=http;branch=5.13"


# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "f5265a2ab8b19b181e7e7b8175a9598cf2a1fbc9"

S = "${WORKDIR}/git"

inherit distutils3

DEPENDS = "qtbase shiboken2-native"

inherit cmake_qt5 pkgconfig

OECMAKE_GENERATOR = "Unix Makefiles"

# depends on meta-clang
TOOLCHAIN = "clang"

