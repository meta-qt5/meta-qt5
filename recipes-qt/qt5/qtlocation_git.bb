require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0) | GPL-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=4bfd28363f541b10d9f024181b8df516 \
    file://LICENSE.LGPLv3;md5=e0459b45c5c4840b353141a8bbed91f0 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.GPLv2;md5=c96076271561b0e3785dad260634eaa8 \
"

DEPENDS += "qtbase qtxmlpatterns qtdeclarative qtquickcontrols"

SRC_URI += "file://0001-Make-mapbox-gl-build-configurable.patch"

PACKAGECONFIG ??= ""
# older geoclue 0.12.99 is needed
PACKAGECONFIG[geoclue] = ",,geoclue"
PACKAGECONFIG[gypsy] = "-feature-gypsy,-no-feature-gypsy,gconf gypsy"
PACKAGECONFIG[mapboxgl] = ""

EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'mapboxgl', 'CONFIG+=mapboxgl', '', d)}"
EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

SRC_URI += " \
    ${QT_GIT}/qtlocation-mapboxgl.git;name=qtlocation-mapboxgl;branch=upstream/qt-staging;protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/mapbox-gl-native \
    "

SRCREV_qtlocation = "dc70a1ffdcb6ab5753773917642b4d89e68d21e7"
SRCREV_qtlocation-mapboxgl = "9ecbe3642fb4a53b558598239b59bf1d0224c25b"

SRCREV_FORMAT = "qtlocation_qtlocation-mapboxgl"
