require qt5-git.inc
require ${PN}.inc

QT_MODULE_BRANCH = "5.4"
QT_MODULE_BRANCH_CHROMIUM = "37.0.2062-based"

SRC_URI += " \
    git://gitorious.org/qt/qtwebengine-chromium.git;name=chromium;branch=${QT_MODULE_BRANCH_CHROMIUM};destsuffix=git/src/3rdparty \
    file://0001-functions.prf-Don-t-match-QMAKE_EXT_CPP-or-QMAKE_EXT.patch \
    file://0002-functions.prf-Make-sure-we-only-use-the-file-name-to.patch \
    file://0003-functions.prf-allow-build-for-linux-oe-g-platform.patch \
    file://0004-configure.prf-don-t-fail-when-libcap-test-fails.patch \
    file://0001-chromium-base.gypi-include-atomicops_internals_x86_g.patch \
"
SRCREV_qtwebengine = "4eceed9ac0b646238e76d77569a619d4dc515ba5"
SRCREV_chromium = "f9c03801de86b5e9da2b915a9e490c2f2254fecf"
SRCREV = "${SRCREV_qtwebengine}"

SRCREV_FORMAT = "qtwebengine"

S = "${WORKDIR}/git"
