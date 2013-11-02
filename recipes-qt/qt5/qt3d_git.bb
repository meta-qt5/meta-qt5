require qt5-git.inc
require ${PN}.inc

QT_MODULE_BRANCH = "master"

# last tag before this SRCREV is 5.0.0-beta1, but version says 5.3.0 already
SRCREV = "4820a197cebd5184ab39ef405eb78f6e69ffc5f9"

do_configure_prepend() {
    # Temporary hack to get qt3d build for Qt 5.1.0
    if ! grep -q MODULE_VERSION ${S}/.qmake.conf; then
        echo "MODULE_VERSION = 5.3.0" >> ${S}/.qmake.conf
    fi
}
