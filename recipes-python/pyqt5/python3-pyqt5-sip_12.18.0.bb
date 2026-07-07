SUMMARY = "The sip module support for PyQt5"
HOMEPAGE = "https://pypi.org/project/PyQt5-sip/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bc996f4e03c98eae60de43496026f863"

PYPI_SRC_URI = "https://files.pythonhosted.org/packages/f3/31/5ef342de9faee0f3801088946ae103db9b9eaeba3d6a64fefd5ce74df244/pyqt5_sip-${PV}.tar.gz"
SRC_URI[sha256sum] = "71c37db75a0664325de149f43e2a712ec5fa1f90429a21dafbca005cb6767f94"

inherit pypi setuptools3

PYPI_PACKAGE = "PyQt5_sip"
S = "${UNPACKDIR}/pyqt5_sip-${PV}"

# Fix build with gcc-14
# http://errors.yoctoproject.org/Errors/Details/761512/
# siplib.c:3900:20: error: assignment to 'sipSimpleWrapper *' {aka 'struct _sipSimpleWrapper *'} from incompatible pointer type 'PyObject *' {aka 'struct _object *'} [-Wincompatible-pointer-types]
CFLAGS += "-Wno-error=incompatible-pointer-types"
