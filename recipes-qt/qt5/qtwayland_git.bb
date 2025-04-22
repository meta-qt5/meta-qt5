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

SRCREV = "7558876d0fca798be4371c0a2a448a478f74a187"

BBCLASSEXTEND =+ "native nativesdk"

# The same issue as in qtbase:
# http://errors.yoctoproject.org/Errors/Details/152641/
LDFLAGS:append = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"

# http://errors.yoctoproject.org/Errors/Details/852833/
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/fullscreen-shell-v1/qwayland-fullscreen-shell-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/fullscreen-shell-v1/qwayland-fullscreen-shell-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/xdg-shell-v6/qwayland-xdg-shell-unstable-v6.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/xdg-shell-v6/qwayland-xdg-shell-unstable-v6.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/xdg-shell/qwayland-xdg-decoration-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/xdg-shell/qwayland-xdg-shell.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/xdg-shell/qwayland-xdg-decoration-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/xdg-shell/qwayland-xdg-shell.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/ivi-shell/qwayland-ivi-application.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/ivi-shell/qwayland-ivi-controller.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/ivi-shell/qwayland-ivi-controller.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/ivi-shell/qwayland-ivi-application.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/wl-shell/qwayland-wayland.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/shellintegration/wl-shell/qwayland-wayland.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/linux-dmabuf-unstable-v1/qwayland-server-linux-dmabuf-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/linux-dmabuf-unstable-v1/qwayland-server-linux-dmabuf-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/xcomposite-glx/qwayland-server-wayland.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/xcomposite-glx/qwayland-server-xcomposite.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/xcomposite-glx/qwayland-server-wayland.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/xcomposite-glx/qwayland-server-xcomposite.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/vulkan-server/qwayland-server-qt-vulkan-server-buffer-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/vulkan-server/qwayland-server-qt-vulkan-server-buffer-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/dmabuf-server/qwayland-server-qt-dmabuf-server-buffer.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/dmabuf-server/qwayland-server-qt-dmabuf-server-buffer.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/xcomposite-egl/qwayland-server-wayland.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/xcomposite-egl/qwayland-server-xcomposite.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/xcomposite-egl/qwayland-server-wayland.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/xcomposite-egl/qwayland-server-xcomposite.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/shm-emulation-server/qwayland-server-shm-emulation-server-buffer.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/shm-emulation-server/qwayland-server-shm-emulation-server-buffer.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/wayland-eglstream-controller/qwayland-server-wl-eglstream-controller.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/compositor/wayland-eglstream-controller/qwayland-server-wl-eglstream-controller.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/client/xcomposite-glx/qwayland-xcomposite.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/client/xcomposite-glx/qwayland-xcomposite.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/client/vulkan-server/qwayland-qt-vulkan-server-buffer-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/client/vulkan-server/qwayland-qt-vulkan-server-buffer-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/client/dmabuf-server/qwayland-qt-dmabuf-server-buffer.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/client/dmabuf-server/qwayland-qt-dmabuf-server-buffer.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/client/xcomposite-egl/qwayland-xcomposite.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/client/xcomposite-egl/qwayland-xcomposite.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/client/shm-emulation-server/qwayland-shm-emulation-server-buffer.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/hardwareintegration/client/shm-emulation-server/qwayland-shm-emulation-server-buffer.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/platforms/qwayland-xcomposite-egl/qwayland-xcomposite.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/platforms/qwayland-xcomposite-egl/qwayland-xcomposite.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/platforms/qwayland-xcomposite-glx/qwayland-xcomposite.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/plugins/platforms/qwayland-xcomposite-glx/qwayland-xcomposite.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-qt-windowmanager.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-xdg-output-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-qt-texture-sharing-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-viewporter.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-qt-texture-sharing-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-scaler.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-hardware-integration.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-idle-inhibit-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-xdg-shell.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-viewporter.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-server-buffer-extension.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-scaler.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-text-input-unstable-v2.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-touch-extension.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-xdg-shell.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-qt-windowmanager.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-xdg-decoration-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-hardware-integration.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-wayland.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-xdg-shell-unstable-v6.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-xdg-shell-unstable-v6.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-xdg-output-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-server-buffer-extension.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-qt-key-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-ivi-application.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-ivi-application.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-text-input-unstable-v2.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-xdg-decoration-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-wayland.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-idle-inhibit-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-qt-key-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/compositor/qwayland-server-touch-extension.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-touch-extension.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-qt-windowmanager.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-qt-key-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-hardware-integration.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-wp-primary-selection-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-surface-extension.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-wayland.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-server-buffer-extension.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-xdg-output-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-qt-windowmanager.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-tablet-unstable-v2.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-server-buffer-extension.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-tablet-unstable-v2.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-touch-extension.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-hardware-integration.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-wayland.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-wp-primary-selection-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-surface-extension.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-xdg-output-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-text-input-unstable-v2.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-text-input-unstable-v2.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/client/qwayland-qt-key-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/imports/texture-sharing/qwayland-qt-texture-sharing-unstable-v1.h in package qtwayland-src contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/src/debug/qtwayland/5.15.16+git/src/imports/texture-sharing/qwayland-qt-texture-sharing-unstable-v1.cpp in package qtwayland-src contains reference to TMPDIR [buildpaths]
INSANE_SKIP:${PN}-src += "buildpaths"

# ERROR: QA Issue: File /usr/include/QtWaylandClient/5.15.16/QtWaylandClient/private/qwayland-qt-windowmanager.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandClient/5.15.16/QtWaylandClient/private/qwayland-qt-key-unstable-v1.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandClient/5.15.16/QtWaylandClient/private/qwayland-hardware-integration.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandClient/5.15.16/QtWaylandClient/private/qwayland-wp-primary-selection-unstable-v1.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandClient/5.15.16/QtWaylandClient/private/qwayland-wayland.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandClient/5.15.16/QtWaylandClient/private/qwayland-xdg-output-unstable-v1.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandClient/5.15.16/QtWaylandClient/private/qwayland-tablet-unstable-v2.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandClient/5.15.16/QtWaylandClient/private/qwayland-server-buffer-extension.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandClient/5.15.16/QtWaylandClient/private/qwayland-touch-extension.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandClient/5.15.16/QtWaylandClient/private/qwayland-surface-extension.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandClient/5.15.16/QtWaylandClient/private/qwayland-text-input-unstable-v2.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-qt-windowmanager.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-qt-texture-sharing-unstable-v1.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-scaler.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-viewporter.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-touch-extension.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-xdg-shell.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-xdg-decoration-unstable-v1.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-hardware-integration.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-wayland.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-xdg-shell-unstable-v6.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-xdg-output-unstable-v1.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-server-buffer-extension.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-ivi-application.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-text-input-unstable-v2.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-idle-inhibit-unstable-v1.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
# ERROR: QA Issue: File /usr/include/QtWaylandCompositor/5.15.16/QtWaylandCompositor/private/qwayland-server-qt-key-unstable-v1.h in package qtwayland-dev contains reference to TMPDIR [buildpaths]
INSANE_SKIP:${PN}-dev += "buildpaths"
