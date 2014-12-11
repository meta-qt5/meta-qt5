require qt5-git.inc
require ${PN}.inc

QT_MODULE_BRANCH = "5.4"
QT_MODULE_BRANCH_CHROMIUM = "37.0.2062-based"

SRC_URI += " \
    git://gitorious.org/qt/qtwebengine-chromium.git;name=chromium;branch=${QT_MODULE_BRANCH_CHROMIUM};destsuffix=git/src/3rdparty \
    file://0001-functions.prf-Don-t-match-QMAKE_EXT_CPP-or-QMAKE_EXT.patch \
    file://0002-functions.prf-Make-sure-we-only-use-the-file-name-to.patch \
    file://0003-functions.prf-allow-build-for-linux-oe-g-platform.patch \
"
SRCREV_qtwebengine = "8eefc3a0658d1b10de12f43534cfd63af825ebc8"
SRCREV_chromium = "be4ec3fa64a4c6c2b641830a9811ab7847c7dd39"
SRCREV = "${SRCREV_qtwebengine}"

SRCREV_FORMAT = "qtwebengine"

S = "${WORKDIR}/git"
