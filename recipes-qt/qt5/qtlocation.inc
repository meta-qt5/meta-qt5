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
