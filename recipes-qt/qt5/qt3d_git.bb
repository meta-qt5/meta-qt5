require qt5-git.inc
require ${PN}.inc

QT_MODULE_BRANCH = "dev"

# last tag before this SRCREV is 5.0.0-beta1, but version says 5.3.0 already
SRCREV = "d3338a9f7fcac109d7bc7f600d5974ff333782ad"

do_configure_prepend() {
    # Temporary hack to get qt3d build for Qt 5.1.0
    if ! grep -q MODULE_VERSION ${S}/.qmake.conf; then
        echo "MODULE_VERSION = 5.3.0" >> ${S}/.qmake.conf
    fi
}
