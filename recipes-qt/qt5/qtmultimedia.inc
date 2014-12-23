require qt5.inc

DEPENDS += "qtdeclarative alsa-lib"

PACKAGECONFIG ??= ""
PACKAGECONFIG[openal] = ",,openal-soft"
PACKAGECONFIG[gstreamer010] = ",,gstreamer gst-plugins-base"

EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'openal', 'CONFIG+=OE_OPENAL_ENABLED', '', d)}"
EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'gstreamer010', 'CONFIG+=OE_GSTREAMER010_ENABLED', '', d)}"

SRC_URI += "\
    file://0001-Initial-porting-effort-to-GStreamer-1.0.patch \
    file://0002-qtmultimedia.pro-Respect-OE_GSTREAMER_ENABLED-OE_GST.patch \
"

LICENSE += "| GPL-2.0"
LIC_FILES_CHKSUM += "file://LICENSE.GPLv2;md5=e782f55badfa137e5e59c330f12cc8ed"
