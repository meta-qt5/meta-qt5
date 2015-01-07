require qt5.inc

DEPENDS += "qtdeclarative"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"
PACKAGECONFIG[alsa] = ",,alsa-lib"
PACKAGECONFIG[pulseaudio] = ",,pulseaudio"
PACKAGECONFIG[openal] = ",,openal-soft"
PACKAGECONFIG[gstreamer010] = ",,gstreamer gst-plugins-base"

EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'alsa', '', 'CONFIG+=done_config_alsa', d)}"
EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'pulseaudio', '', 'CONFIG+=done_config_pulseaudio', d)}"
EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'openal', 'CONFIG+=OE_OPENAL_ENABLED', '', d)}"
EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'gstreamer010', 'CONFIG+=OE_GSTREAMER010_ENABLED', '', d)}"

SRC_URI += "\
    file://0001-Initial-porting-effort-to-GStreamer-1.0.patch \
    file://0002-qtmultimedia.pro-Respect-OE_GSTREAMER_ENABLED-OE_GST.patch \
"
