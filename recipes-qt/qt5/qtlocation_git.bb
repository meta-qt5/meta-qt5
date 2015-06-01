require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & Digia-Qt-LGPL-Exception-1.1 | LGPL-3.0) | GPL-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=cff17b12416c896e10ae2c17a64252e7 \
    file://LICENSE.LGPLv3;md5=c1939be5579666be947371bc8120425f \
    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.GPLv2;md5=e782f55badfa137e5e59c330f12cc8ed \
"

DEPENDS += "qtbase qt3d"
# qtsystems qtmultimedia

PACKAGECONFIG ??= ""
# older geoclue 0.x is needed
PACKAGECONFIG[geoclue] = "OE_GEOCLUE_ENABLED,,gconf geoclue"
PACKAGECONFIG[gypsy] = "OE_GYPSY_ENABLED,,gconf gypsy"

do_configure_prepend() {
    # disable geoclue tests if it isn't enabled by PACKAGECONFIG
    sed -i -e 's/^\(qtCompileTest(geoclue)\)/OE_GEOCLUE_ENABLED:\1/' ${S}/qtlocation.pro
    sed -i -e 's/^\(qtCompileTest(geoclue-satellite)\)/OE_GEOCLUE_ENABLED:\1/' ${S}/qtlocation.pro
    # disable gypsy test if it isn't enabled by PACKAGECONFIG
    sed -i -e 's/^\(qtCompileTest(gypsy)\)/OE_GYPSY_ENABLED:\1/' ${S}/qtlocation.pro
}

EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'geoclue', 'CONFIG+=OE_GEOCLUE_ENABLED', '', d)}"
EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'gypsy', 'CONFIG+=OE_GYPSY_ENABLED', '', d)}"

SRCREV = "3678bc8b3e128bf244ae263a44576a40b72e5876"
