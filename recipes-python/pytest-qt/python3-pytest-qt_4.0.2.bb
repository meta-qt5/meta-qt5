SUMMARY = "pytest support for PyQt and PySide applications"
HOMEPAGE = "http://github.com/pytest-dev/pytest-qt"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=be0db96616c6ec3cabe975402c4c687f"

SRC_URI[sha256sum] = "dfc5240dec7eb43b76bcb5f9a87eecae6ef83592af49f3af5f1d5d093acaa93e"

inherit pypi setuptools3

DEPENDS = "python3-setuptools-scm-native"

RDEPENDS:${PN} += "python3-pytest"
