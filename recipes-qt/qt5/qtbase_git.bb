require qt5-git.inc
require ${PN}.inc

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

# target specific
SRC_URI += "\
    file://0006-qmake-don-t-build-it-in-configure-but-allow-to-build.patch \
    file://0007-eglfs-fix-egl-error-for-platforms-only-supporting-on.patch \
    file://0008-qeglplatformintegration-Undefine-CursorShape-from-X..patch \
"

SRCREV = "267ba8b63e0fbf02cde4d2709397ed0e12f34ee2"
