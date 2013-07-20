require qt5-git.inc
require ${PN}.inc

# last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

QT_MODULE_BRANCH = "master"

SRCREV = "d517d39d5491bc95b1dddc3ff5cb880bd3bd0058"

do_configure_prepend() {
    # Temporary hack to get qt3d build for Qt 5.1.0
    if ! grep -q MODULE_VERSION ${S}/.qmake.conf; then
        echo "MODULE_VERSION = 5.1.0" >> ${S}/.qmake.conf
    fi
}
