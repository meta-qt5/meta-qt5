require qt5.inc
require qt5-git.inc

# There are no LGPLv3-only licensed files in this component.
LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
    file://LICENSE.LGPLv3;md5=c4fe8c6de4eef597feec6e90ed62e962 \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

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

SRCREV = "3f15c1b17e55b5b118d11621f85fa74f7cc74ae6"
