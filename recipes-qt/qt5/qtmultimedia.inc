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

# older copyright year than what e.g. qtbase is using now
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de \
                    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
                    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e"
