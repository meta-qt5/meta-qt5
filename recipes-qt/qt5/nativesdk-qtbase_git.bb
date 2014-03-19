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
    file://0001-Fix-missing-plugin_types.patch \
    file://0001-Revert-eglfs-Print-the-chosen-config-in-debug-mode.patch \
"

# common with native version
SRC_URI += " \
    file://0008-Always-build-uic.patch \
"    

# specific for nativesdk version
SRC_URI += " \
    file://0009-configure-preserve-built-qmake-and-swap-with-native-.patch \
    file://0010-configure-bump-path-length-from-256-to-512-character.patch \
"

SRCREV = "207598fd8e69be34e8ba2c9db7720cb6003ea114"
