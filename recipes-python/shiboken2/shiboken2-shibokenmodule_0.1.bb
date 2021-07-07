require shiboken2.inc

DEPENDS += " clang shiboken2-generator-native"

SRC_URI += "file://shibokenmodule-cmake.patch;subdir=git/sources/shiboken2"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

FILES_${PN} += " ${PYTHON_SITEPACKAGES_DIR}/shiboken2"

do_install_append() {
    rm -rf "${D}/${PYTHON_SITEPACKAGES_DIR}/shiboken2_generator"
}

# ${STAGING_DIR_NATIVE}/usr/bin/shiboken2 is intalled by  shiboken2-generator-native
EXTRA_OECMAKE += " \
    -DSHIBOKEN2_GENERATOR_EXECUTABLE=${STAGING_DIR_NATIVE}/usr/bin/shiboken2 \
    -DPYTHON_EXECUTABLE=${PYTHON} \
"

