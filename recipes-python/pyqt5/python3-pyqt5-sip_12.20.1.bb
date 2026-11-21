SUMMARY = "The sip module support for PyQt5"
HOMEPAGE = "https://pypi.org/project/PyQt5-sip/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bc996f4e03c98eae60de43496026f863"

PYPI_SRC_URI = "https://files.pythonhosted.org/packages/e0/5d/778b6bb5d65c32e40ab8bd2b40e218108a31e323c5e8c026895da7833444/pyqt5_sip-${PV}.tar.gz"
SRC_URI[sha256sum] = "b94f0c7cf8f6335adb90aab1981756c94e1250854dbcc209271ba8c058c4ddbb"

inherit pypi python_setuptools_build_meta

PYPI_PACKAGE = "PyQt5_sip"
S = "${UNPACKDIR}/pyqt5_sip-${PV}"

# Fix build with gcc-14
# http://errors.yoctoproject.org/Errors/Details/761512/
# siplib.c:3900:20: error: assignment to 'sipSimpleWrapper *' {aka 'struct _sipSimpleWrapper *'} from incompatible pointer type 'PyObject *' {aka 'struct _object *'} [-Wincompatible-pointer-types]
CFLAGS += "-Wno-error=incompatible-pointer-types"
