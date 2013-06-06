require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

PR = "${INC_PR}.1"

SRCREV = "5cb159395eccb1d96fb73a78e499eef30aacb46d"

do_configure_prepend() {
    # Temporary hack to get qtwayland build for Qt 5.0.2
    if ! grep -q MODULE_VERSION ${S}/.qmake.conf; then
        echo "MODULE_VERSION = 0.0.0" >> ${S}/.qmake.conf
    fi
}
