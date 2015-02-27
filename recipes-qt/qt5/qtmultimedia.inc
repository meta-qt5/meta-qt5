require qt5.inc

DEPENDS += "qtdeclarative"

PACKAGECONFIG ??= "${@bb.utils.contains('DISTRO_FEATURES', 'alsa', 'alsa', '', d)} \
                   ${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"
PACKAGECONFIG[alsa] = ",,alsa-lib"
PACKAGECONFIG[pulseaudio] = ",,pulseaudio"
PACKAGECONFIG[openal] = ",,openal-soft"
PACKAGECONFIG[gstreamer] = ",,gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-bad"
PACKAGECONFIG[gstreamer010] = ",,gstreamer gst-plugins-base gst-plugins-bad"

EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'alsa', '', 'CONFIG+=done_config_alsa', d)}"
EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'pulseaudio', '', 'CONFIG+=done_config_pulseaudio', d)}"
EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'openal', '', 'CONFIG+=done_config_openal', d)}"

# Handles GStreamer support
EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'gstreamer', 'GST_VERSION=1.0', '', d)}"
EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'gstreamer010', 'GST_VERSION=0.10', '', d)}"
# Disable GStreamer if completely disabled
EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains_any('PACKAGECONFIG', 'gstreamer gstreamer010', '', 'CONFIG+=done_config_gstreamer', d)}"

SRC_URI += "\
    file://0001-Initial-porting-effort-to-GStreamer-1.0.patch \
"
