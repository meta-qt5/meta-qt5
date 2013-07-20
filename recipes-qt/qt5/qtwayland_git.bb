require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1,
# but we'll use 5.0.2+git to indicate this version is compatible with 5.0.2
PV = "5.0.2+git${SRCPV}"

# drop when bumping SRCREV
PR = "r2"

SRCREV = "5cb159395eccb1d96fb73a78e499eef30aacb46d"

SRC_URI += " \
    file://0004-EGL-Specify-vec2d-precision-qualifier-in-fragment-sh.patch \
"

do_configure_prepend() {
    # Temporary hack to get qtwayland build for Qt 5.0.2
    if ! grep -q MODULE_VERSION ${S}/.qmake.conf; then
        echo "MODULE_VERSION = 0.0.0" >> ${S}/.qmake.conf
    fi
}
