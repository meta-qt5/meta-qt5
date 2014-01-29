require qt5.inc

DEPENDS += "qtdeclarative alsa-lib"

PACKAGECONFIG ??= ""
PACKAGECONFIG[openal] = ",,openal-soft"
PACKAGECONFIG[gstreamer010] = ",,gstreamer gst-plugins-base"

do_configure_prepend() {
    # disable openal test if it isn't enabled by PACKAGECONFIG
    sed -i 's/^qtCompileTest(openal)/OE_OPENAL_ENABLED:qtCompileTest(openal)/g' ${S}/qtmultimedia.pro
    # disable gstreamer-0.10 test if it isn't enabled by PACKAGECONFIG
    sed -i 's/^\( *\)qtCompileTest(gstreamer) {/\1OE_GSTREAMER010_ENABLED:qtCompileTest(gstreamer) {/g' ${S}/qtmultimedia.pro
}

EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'openal', 'CONFIG+=OE_OPENAL_ENABLED', '', d)}"
EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'gstreamer010', 'CONFIG+=OE_GSTREAMER010_ENABLED', '', d)}"
