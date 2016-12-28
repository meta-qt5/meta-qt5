require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0+ | LGPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.LGPLv21;md5=4bfd28363f541b10d9f024181b8df516 \
    file://LICENSE.LGPLv3;md5=e0459b45c5c4840b353141a8bbed91f0 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
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

SRC_URI += "\
    file://0001-Initial-porting-effort-to-GStreamer-1.0.patch \
"

SRCREV = "e4aef963cbd32274669af6b66158f6f0ca4ba3ed"
