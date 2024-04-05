SUMMARY = "The sip module support for PyQt5"
HOMEPAGE = "https://pypi.org/project/PyQt5-sip/"
LICENSE = "SIP | GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9cd437778ebd1c056a76b4ded73b3a6d"

SRC_URI[sha256sum] = "8fdc6e0148abd12d977a1d3828e7b79aae958e83c6cb5adae614916d888a6b10"

inherit pypi setuptools3

PYPI_PACKAGE = "PyQt5_sip"

# Fix build with gcc-14
# http://errors.yoctoproject.org/Errors/Details/761512/
# siplib.c:3900:20: error: assignment to 'sipSimpleWrapper *' {aka 'struct _sipSimpleWrapper *'} from incompatible pointer type 'PyObject *' {aka 'struct _object *'} [-Wincompatible-pointer-types]
CFLAGS += "-Wno-error=incompatible-pointer-types"
