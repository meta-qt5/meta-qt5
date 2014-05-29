require qt5.inc

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

# older copyright year than what e.g. qtbase is using now
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de \
                    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
                    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e"
