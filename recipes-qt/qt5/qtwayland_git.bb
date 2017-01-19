require qt5.inc
require qt5-git.inc

DEPENDS += "qtbase qtdeclarative wayland wayland-native qtwayland-native"

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

PACKAGECONFIG ?= " \
    wayland-client \
    wayland-server \
    wayland-egl \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcomposite-egl xcomposite-glx', '', d)} \
"
PACKAGECONFIG_class-native ?= ""
PACKAGECONFIG_class-nativesdk ?= ""
QMAKE_PROFILES_class-native = "${S}/src/qtwaylandscanner"
QMAKE_PROFILES_class-nativesdk = "${S}/src/qtwaylandscanner"
B_class-native = "${SEPB}/src/qtwaylandscanner"
B_class-nativesdk = "${SEPB}/src/qtwaylandscanner"

PACKAGECONFIG[wayland-client] = "-feature-wayland-client,-no-feature-wayland-client"
PACKAGECONFIG[wayland-server] = "-feature-wayland-server,-no-feature-wayland-server"
PACKAGECONFIG[xcomposite-egl] = "-feature-xcomposite-egl,-no-feature-xcomposite-egl,libxcomposite"
PACKAGECONFIG[xcomposite-glx] = "-feature-xcomposite-glx,-no-feature-xcomposite-glx,virtual/mesa"
PACKAGECONFIG[wayland-egl] = "-feature-wayland-egl,-no-feature-wayland-egl,virtual/egl"
PACKAGECONFIG[wayland-brcm] = "-feature-wayland-brcm,-no-feature-wayland-brcm,virtual/egl"
PACKAGECONFIG[drm-egl-server] = "-feature-drm-egl-server,-no-feature-drm-egl-server,libdrm virtual/egl"
PACKAGECONFIG[libhybris-egl-server] = "-feature-libhybris-egl-server,-no-feature-libhybris-egl-server,libhybris"

EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

SRCREV = "e26696524ceb58f4fe0cb7202d873240b6a9478d"

BBCLASSEXTEND =+ "native nativesdk"
