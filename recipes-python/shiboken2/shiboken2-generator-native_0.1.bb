
inherit native
require shiboken2.inc
DEPENDS += " clang-native"

FILES_${PN} += " ${PYTHON_SITEPACKAGES_DIR}/shiboken2_generator"

SRC_URI += "file://cmake-do-not-build-shibokenmodule-as-it-depends-on-generator.patch;subdir=git/sources/shiboken2"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"


