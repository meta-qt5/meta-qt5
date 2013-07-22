require qt5.inc

DEPENDS += "qtdeclarative gstreamer gst-plugins-base alsa-lib "

PACKAGECONFIG ??= ""
PACKAGECONFIG[openal] = ",,openal-soft"

do_configure_prepend() {
    # disable openal test if it isn't enabled by PACKAGECONFIG
    sed -i 's/^qtCompileTest(openal)/OE_OPENAL_ENABLED:qtCompileTest(openal)/g' ${S}/qtmultimedia.pro
}

EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'openal', 'CONFIG+=OE_OPENAL_ENABLED', '', d)}"
