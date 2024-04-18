require qt5.inc
require qt5-git.inc

LICENSE = "Apache-2.0 & MIT & OpenSSL & BSL-1.0 & GFDL-1.3 & BSD-3-Clause & ( GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0-or-later | LGPL-3.0-only | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://src/3rdparty/mapbox-gl-native/LICENSE.md;md5=0ab9025299bcee16858021d557f09449 \
"

DEPENDS += "qtbase qtxmlpatterns qtdeclarative qtquickcontrols"

PACKAGECONFIG ??= ""
PACKAGECONFIG[geoclue] = ",,,geoclue"
PACKAGECONFIG[gypsy] = "-feature-gypsy,-no-feature-gypsy,gconf gypsy"
PACKAGECONFIG[geoservices_here] = "-feature-geoservices_here,-no-feature-geoservices_here"
PACKAGECONFIG[geoservices_mapbox] = "-feature-geoservices_mapbox,-no-feature-geoservices_mapbox"
PACKAGECONFIG[geoservices_esri] = "-feature-geoservices_esri,-no-feature-geoservices_esri"
PACKAGECONFIG[geoservices_itemsoverlay] = "-feature-geoservices_itemsoverlay,-no-feature-geoservices_itemsoverlay"
PACKAGECONFIG[geoservices_osm] = "-feature-geoservices_osm,-no-feature-geoservices_osm"
PACKAGECONFIG[geoservices_mapboxgl] = "-feature-geoservices_mapboxgl,-no-feature-geoservices_mapboxgl"

EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

# The same issue as in qtbase:
# http://errors.yoctoproject.org/Errors/Details/152640/
LDFLAGS:append:x86 = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"

QT_MODULE_BRANCH_MAPBOXGL = "upstream/qt-staging"

SRC_URI += " \
    ${QT_GIT}/qtlocation-mapboxgl.git;name=qtlocation-mapboxgl;branch=${QT_MODULE_BRANCH_MAPBOXGL};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/mapbox-gl-native \
    file://0001-mapbox-gl-explicitly-include-cstdint-header.patch;patchdir=src/3rdparty/mapbox-gl-native \
    file://gcc14-const.patch;patchdir=src/3rdparty/mapbox-gl-native/deps/rapidjson/1.1.0 \
"

SRCREV_qtlocation = "f6953a8d604b906b170fb4610e124e7c31260a18"
SRCREV_qtlocation-mapboxgl = "d3101bbc22edd41c9036ea487d4a71eabd97823d"

SRCREV_FORMAT = "qtlocation_qtlocation-mapboxgl"
