require qt5-git.inc
require ${PN}.inc

# QtWebEngine has it's own version format currently and doesn't follow the major Qt
# versions (but it's release are at the same time as of major Qt releases). We're tracking
# the master branch which will lead to 1.0 when Qt 5.4 is released here.
PV = "0.9.99+git${SRCPV}"

QT_MODULE_BRANCH = "master"

# For now we have to define SRC_URI here again as qt5-git.inc points to
# qt.gitorious.org/qt/ but qtwebengine is still on qt.gitorious.org/qt-labs/ which will
# switch soon.
SRC_URI = " \
    git://gitorious.org/qt-labs/qtwebengine.git;name=qtwebengine;protocol=git;branch=${QT_MODULE_BRANCH} \
    git://gitorious.org/qt-labs/chromium.git;name=chromium;protocol=git;branch=${QT_MODULE_BRANCH};destsuffix=git/src/3rdparty \
    file://0001-Drop-build-time-only-dependency-on-x11-libraries.patch \
    file://0002-Use-ninja-supplied-by-environment-variable-NINJA_PAT.patch \
    file://0001-Strip-unwanted-echo-compiling-prefix-from-CC.patch \
"
SRCREV_qtwebengine = "21f6ce84ecca9a4ff2aa980b21d2e5174c78d14b"
SRCREV_chromium = "1f3cc8c2618979b557d60ef1ad984a49dca83bff"

SRCREV_FORMAT = "qtwebengine"
