require qt5-git.inc
require ${PN}.inc

QT_MODULE_BRANCH = "1.0"
QT_MODULE_BRANCH_CHROMIUM = "33.0.1750.170-based"

SRC_URI += " \
    git://gitorious.org/qt/qtwebengine-chromium.git;name=chromium;branch=${QT_MODULE_BRANCH_CHROMIUM};destsuffix=git/src/3rdparty \
    file://0001-functions.prf-Don-t-match-QMAKE_EXT_CPP-or-QMAKE_EXT.patch \
    file://0002-functions.prf-Try-to-add-_moc-suffix.patch \
    file://0001-chromium-Drop-build-time-only-dependency-on-x11-libr.patch \
    file://0002-chromium-Strip-unwanted-echo-compiling-prefix-from-C.patch \
    file://0003-chromium-base.gypi-include-atomicops_internals_x86_gcc.cc-whe.patch \
"
SRCREV_qtwebengine = "14fcf0d3193e2ee2bf904a305c9f34ff8d0e5ce7"
SRCREV_chromium = "1f3cc8c2618979b557d60ef1ad984a49dca83bff"
SRCREV = "${SRCREV_qtwebengine}"

SRCREV_FORMAT = "qtwebengine"
