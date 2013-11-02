require qt5-git.inc
require ${PN}.inc

# common with -native
SRC_URI = "\
    git://qt.gitorious.org/qt/${QT_MODULE}.git;branch=${QT_MODULE_BRANCH} \
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0003-Add-external-hostbindir-option.patch \
    file://0004-qt_functions-temporary-remove-isEmpty-check.patch \
    file://0005-configureapp-Prefix-default-LIBDIRS-and-INCDIRS-with.patch \
    file://0006-qt_module-Fix-pkgconfig-replacement.patch \
    file://0007-qt_module-Fix-paths-in-.prl-files.patch \
    file://0008-wayland-scanner-disable-silent-rules.patch \
    file://0009-configure-don-t-export-SYSTEM_VARIABLES-to-.qmake.va.patch \
    file://0010-configure.prf-Allow-to-add-extra-arguments-to-make.patch \
    file://0011-configure-make-pulseaudio-a-configurable-option.patch \
    file://0012-configure-make-alsa-a-configurable-option.patch \
    file://0013-configure-make-freetype-a-configurable-option.patch \
    file://0014-Use-OE_QMAKE_PATH_EXTERNAL_HOST_BINS-to-determine-pa.patch \
"

SRC_URI += "\
    file://0015-qmake-is-already-built-in-qtbase-native.patch \
    file://0016-Allow-building-a-separate-qmake-for-the-target.patch \
    file://0017-enables-tslib-device-to-be-read-from-env-variable.patch \
    file://0018-qtbase-allow-build-of-examples.patch \
    file://0019-QOpenGLPaintDevice-sub-area-support.patch \
"

SRCREV = "313a74cc4a9a5d200b2059d3d8767fe1a274c50d"
