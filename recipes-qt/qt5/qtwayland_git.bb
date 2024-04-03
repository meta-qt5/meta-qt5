require qt5.inc
require qt5-git.inc

inherit pkgconfig

DEPENDS += "qtbase qtdeclarative wayland wayland-native qtwayland-native"
DEPENDS:append:class-target = " libxkbcommon"

LICENSE = "GFDL-1.3 & BSD-3-Clause & ( GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0-or-later | LGPL-3.0-only | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

# Patches from https://github.com/meta-qt5/qtwayland/commits/b5.15
# 5.15.meta-qt5.1
SRC_URI += "file://0001-tst_seatv4-Include-array.patch \
            file://0001-linux-dmabuf-unstable-v1-Include-missing-array-heade.patch \
            file://0001-Fix-vulkan-buffer-formats-for-GLES2.patch \
            file://0001-qwaylandinputcontext-Include-missing-header-locale.h.patch \
           "

PACKAGECONFIG ?= " \
    wayland-client \
    wayland-server \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl wayland', 'wayland-egl', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xcomposite-egl xcomposite-glx', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'wayland-vulkan-server-buffer', '', d)} \
"
PACKAGECONFIG:class-native ?= ""
PACKAGECONFIG:class-nativesdk ?= ""
QMAKE_PROFILES:class-native = "${S}/src/qtwaylandscanner"
QMAKE_PROFILES:class-nativesdk = "${S}/src/qtwaylandscanner"
B:class-native = "${SEPB}/src/qtwaylandscanner"
B:class-nativesdk = "${SEPB}/src/qtwaylandscanner"

PACKAGECONFIG[wayland-client] = "-feature-wayland-client,-no-feature-wayland-client"
PACKAGECONFIG[wayland-server] = "-feature-wayland-server,-no-feature-wayland-server"
PACKAGECONFIG[xcomposite-egl] = "-feature-xcomposite-egl,-no-feature-xcomposite-egl,libxcomposite"
PACKAGECONFIG[xcomposite-glx] = "-feature-xcomposite-glx,-no-feature-xcomposite-glx,virtual/mesa"
PACKAGECONFIG[wayland-egl] = "-feature-wayland-egl,-no-feature-wayland-egl,virtual/egl"
PACKAGECONFIG[wayland-brcm] = "-feature-wayland-brcm,-no-feature-wayland-brcm,virtual/egl"
PACKAGECONFIG[wayland-drm-egl-server-buffer] = "-feature-wayland-drm-egl-server-buffer,-no-feature-wayland-drm-egl-server-buffer,libdrm virtual/egl"
PACKAGECONFIG[wayland-libhybris-egl-server-buffer] = "-feature-wayland-libhybris-egl-server-buffer,-no-feature-wayland-libhybris-egl-server-buffer,libhybris"
PACKAGECONFIG[wayland-vulkan-server-buffer] = "-feature-wayland-vulkan-server-buffer,-no-feature-wayland-vulkan-server-buffer,vulkan-headers"

EXTRA_QMAKEVARS_CONFIGURE += "${PACKAGECONFIG_CONFARGS}"

SRCREV = "163ea1a04d1b00c95373d69e8c907e5789082d3e"

BBCLASSEXTEND =+ "native nativesdk"

# The same issue as in qtbase:
# http://errors.yoctoproject.org/Errors/Details/152641/
LDFLAGS:append = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"
