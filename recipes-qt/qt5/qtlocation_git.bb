require qt5.inc
require qt5-git.inc

LICENSE = "Apache-2.0 & MIT & openssl & BSL-1.0 & GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0+ | LGPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.LGPLv3;md5=e0459b45c5c4840b353141a8bbed91f0 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://src/3rdparty/mapbox-gl-native/LICENSE.md;md5=0ab9025299bcee16858021d557f09449 \
"

DEPENDS += "qtbase qtxmlpatterns qtdeclarative qtquickcontrols"

PACKAGECONFIG ??= ""
# older geoclue 0.12.99 is needed
PACKAGECONFIG[geoclue] = ",,geoclue"
PACKAGECONFIG[gypsy] = "-feature-gypsy,-no-feature-gypsy,gconf gypsy"
PACKAGECONFIG[geoservices_mapboxgl] = "-feature-geoservices_mapboxgl,-no-feature-geoservices_mapboxgl"

EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

# The same issue as in qtbase:
# http://errors.yoctoproject.org/Errors/Details/152640/
LDFLAGS_append_x86 = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"

SRC_URI += " \
    ${QT_GIT}/qtlocation-mapboxgl.git;name=qtlocation-mapboxgl;branch=upstream/qt-staging;protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/mapbox-gl-native \
"

SRCREV_qtlocation = "ec00ff89bee900a1b8925e227a892bc57839cd60"
SRCREV_qtlocation-mapboxgl = "8c1be4ec01ef46bf453856531ebf53b48ce3dbe7"

SRCREV_FORMAT = "qtlocation_qtlocation-mapboxgl"
