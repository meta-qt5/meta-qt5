SUMMARY = "The PEP 517 compliant PyQt build system"
HOMEPAGE = "https://pypi.org/project/PyQt-builder/"
LICENSE = "SIP | GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9cd437778ebd1c056a76b4ded73b3a6d"

SRC_URI[sha256sum] = "a90553703897eb41e27c2f1abd31fb9ed304c32ec3271b380015b54ea9762ddd"

inherit pypi setuptools3 native

PYPI_PACKAGE = "PyQt-builder"
