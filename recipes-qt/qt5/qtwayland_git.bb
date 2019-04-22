require qt5.inc
require qt5-git.inc

DEPENDS += "qtbase qtdeclarative wayland wayland-native qtwayland-native"
DEPENDS_append_class-target = " libxkbcommon"

LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0+ | LGPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
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

SRCREV = "9ce3088d226fddc18bcac170fa5cc5be3c29e371"

BBCLASSEXTEND =+ "native nativesdk"

# The same issue as in qtbase:
# http://errors.yoctoproject.org/Errors/Details/152641/
LDFLAGS_append_x86 = "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', ' -fuse-ld=bfd ', '', d)}"

# Since version 5.11.2 some private headers are not installed. Work around
# until fixed upstream. See https://bugreports.qt.io/browse/QTBUG-71340 for
# further details
QTWAYLAND_INSTALL_PRIVATE_HEADERS_MANUALLY ?= "1"
# First 6 characters before first + (e.g. 5.11.3-+git) or - (e.g. 5.11.3-2)
SHRT_VER ?= "${@d.getVar('PV').split('+')[0].split('-')[0]}"
do_install_append() {
    if [ -d "${B}/src/client" -a "${QTWAYLAND_INSTALL_PRIVATE_HEADERS_MANUALLY}" = "1" -a -d "${D}${includedir}/QtWaylandClient/${SHRT_VER}/QtWaylandClient/private/" ]; then
        for header in `find ${B}/src/client -name '*wayland-*.h'`; do
            header_base=`basename $header`
            dest="${D}${includedir}/QtWaylandClient/${SHRT_VER}/QtWaylandClient/private/$header_base"
            if [ ! -e "$dest" ]; then
                echo "Manual install: $header_base to $dest"
                install -m 644 "$header" "$dest"
            fi
        done
    fi
    if [ -d "${B}/src/compositor" -a "${QTWAYLAND_INSTALL_PRIVATE_HEADERS_MANUALLY}" = "1" -a -d "${D}${includedir}/QtCompositor/${SHRT_VER}/QtCompositor/private/" ]; then
        for header in `find ${B}/src/compositor -name '*wayland-*.h'`; do
            header_base=`basename $header`
            dest="${D}${includedir}/QtCompositor/${SHRT_VER}/QtCompositor/private/$header_base"
            if [ ! -e "$dest" ]; then
                echo "Manual install: $header_base to $dest"
                install -m 644 "$header" "$dest"
            fi
        done
    fi
}
