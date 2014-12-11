require qt5-git.inc
require ${PN}.inc

# prepend this again, because ${PN}.inc prepneds ${PN}
FILESEXTRAPATHS =. "${FILE_DIRNAME}/${BPN}-git:"

SRC_URI = "\
    git://qt.gitorious.org/qt/${QT_MODULE}.git;branch=${QT_MODULE_BRANCH} \
"

# common for qtbase-native, qtbase-nativesdk and qtbase
SRC_URI += "\
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0003-Add-external-hostbindir-option.patch \
    file://0004-configureapp-Prefix-default-LIBDIRS-and-INCDIRS-with.patch \
    file://0005-qt_module-Fix-pkgconfig-and-libtool-replacements.patch \
    file://0006-Revert-eglfs-Print-the-chosen-config-in-debug-mode.patch \
    file://0007-qeglplatformintegration-Undefine-CursorShape-from-X..patch \
    file://0008-configure-bump-path-length-from-256-to-512-character.patch \
    file://0009-eglfs-fix-egl-error-for-platforms-only-supporting-on.patch \
    file://0010-QOpenGLPaintDevice-sub-area-support.patch \
"

# common for qtbase-native and nativesdk-qtbase
SRC_URI += " \
    file://0011-Always-build-uic.patch \
"

# specific for nativesdk-qtbase
SRC_URI += " \
    file://0012-configure-preserve-built-qmake-and-swap-with-native-.patch \
"

# CMake's toolchain configuration of nativesdk-qtbase
SRC_URI += " \
    file://OEQt5Toolchain.cmake \
"

SRCREV = "cc23ebace8b7f343aca6e10f9ebd31b87c17c782"
