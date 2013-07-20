require qt5-git.inc
require ${PN}.inc

# qtjsondb wasn't released yet, last tag before this SRCREV isn't even 5.0.0-beta1, but lets use it for now
PV = "4.999+5.0.0-beta1+git${SRCPV}"

QT_MODULE_BRANCH = "master"

SRCREV = "27a2b5d9a0f081f71c26a1716721411606f8dedf"

do_configure_prepend() {
    # Temporary hack to get qtjsondb build for Qt 5.1.0
    if ! grep -q MODULE_VERSION ${S}/.qmake.conf; then
        echo "MODULE_VERSION = 5.1.0" >> ${S}/.qmake.conf
    fi
}

do_configure_append() {
    # work around for this issue:
    # http://www.mail-archive.com/ci-reports@qt-project.org/msg09692.html
    sed -i '/^INCPATH/s#-I\.$#-I. -I../../include/QtJsonDbPartition#g' ${B}/src/client/Makefile
}
