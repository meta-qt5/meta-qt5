FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_class-nativesdk = " \
    file://OEQt5.cmake \
"

# Install a file with the cmake toolchain to provide the path to the Qt-tools in the SDK
# (OE_QMAKE_PATH_EXTERNAL_HOST_BINS)
do_install_append_class-nativesdk() {
    mkdir -p ${D}${datadir}/cmake/OEToolchainConfig.cmake.d/
    install -m 644 ${WORKDIR}/OEQt5.cmake ${D}${datadir}/cmake/OEToolchainConfig.cmake.d/
}
