LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780"

DEPENDS = "qtbase-native"

QT_MODULE = "qtjsbackend"

INC_PR = "r0"

inherit native

SRC_URI += "\
    file://module_qmake.conf \
    file://0001-Install-the-mkv8snapshot-tool-to-the-native-side.patch \
"

# Bitbake will not respect the make order set by qmake and at times it will try to compile
# parts of the source tree with out the 'mkv8snapshot' tool if it is enabled and that will fail
PARALLEL_MAKE = ""

export QT_CONF_PATH="${WORKDIR}/qt.conf"

do_configure() {
    # Avoid setting QMAKE_LINK from LD (since we want the linker to be g++)
    unset LD

    cat > ${WORKDIR}/qt.conf <<EOF
[Paths]
Prefix = ${prefix}
Binaries = ${bindir}
Headers = ${includedir}/qt5
Plugins = ${libdir}/qt5/plugins
Libraries = ${libdir}
Imports = ${datadir}/qt5/imports
Qml2Imports = ${libdir}/qt5/qml
Documentation=${docdir}/qt5
Data = ${datadir}/qt5
HostPrefix = ${STAGING_DATADIR}
HostData = ${STAGING_DATADIR}/qt5
HostBinaries = ${STAGING_BINDIR_NATIVE}
EOF

    # This should not be needed. Perhaps the lack of this file is an indication
    # of an error on the native recipe...
    cp ${WORKDIR}/module_qmake.conf ${S}/.qmake.conf

    ${STAGING_BINDIR_NATIVE}/qmake -d -r
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}
