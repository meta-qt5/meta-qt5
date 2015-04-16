require qt5-git.inc
require ${PN}.inc

QT_MODULE_BRANCH = "5.4"
QT_MODULE_BRANCH_CHROMIUM = "37.0.2062-based"

SRC_URI += " \
    git://gitorious.org/qt/qtwebengine-chromium.git;name=chromium;branch=${QT_MODULE_BRANCH_CHROMIUM};destsuffix=git/src/3rdparty \
    file://0001-functions.prf-Don-t-match-QMAKE_EXT_CPP-or-QMAKE_EXT.patch \
    file://0002-functions.prf-Make-sure-we-only-use-the-file-name-to.patch \
    file://0003-functions.prf-allow-build-for-linux-oe-g-platform.patch \
    file://0001-chromium-base.gypi-include-atomicops_internals_x86_g.patch \
"
SRCREV_qtwebengine = "b0c48cb3a0c9630899a357fb8a01ba13ccad5395"
SRCREV_chromium = "140893bef70011645c686f5fabe45018dd2e392a"
SRCREV = "${SRCREV_qtwebengine}"

SRCREV_FORMAT = "qtwebengine"

S = "${WORKDIR}/git"
