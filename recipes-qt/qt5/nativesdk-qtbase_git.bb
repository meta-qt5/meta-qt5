require qt5-git.inc
require ${PN}.inc

# prepend this again, because ${PN}.inc prepneds ${PN}
FILESEXTRAPATHS =. "${FILE_DIRNAME}/${BPN}-git:"

SRC_URI = "\
    git://qt.gitorious.org/qt/${QT_MODULE}.git;branch=${QT_MODULE_BRANCH} \
"

# common with -native
SRC_URI += "\
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-Add-external-hostbindir-option.patch \
    file://0003-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0004-configureapp-Prefix-default-LIBDIRS-and-INCDIRS-with.patch \
    file://0005-Revert-eglfs-Print-the-chosen-config-in-debug-mode.patch \
    file://0001-Revert-Use-the-gcc-feature-in-simd.prf.patch \
"

# common with native version
SRC_URI += " \
    file://0006-Always-build-uic.patch \
"    

# specific for nativesdk version
SRC_URI += " \
    file://0007-configure-preserve-built-qmake-and-swap-with-native-.patch \
    file://0008-configure-bump-path-length-from-256-to-512-character.patch \
"

SRCREV = "267ba8b63e0fbf02cde4d2709397ed0e12f34ee2"
