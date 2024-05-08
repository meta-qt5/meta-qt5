SUMMARY = "The PEP 517 compliant PyQt build system"
HOMEPAGE = "https://pypi.org/project/PyQt-builder/"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://pyproject.toml;md5=62537c8c9cf72be020148e3adc658ce3;beginline=17;endline=17"

SRC_URI[sha256sum] = "47bbd2cfa5430020108f9f40301e166cbea98b6ef3e53953350bdd4c6b31ab18"

inherit pypi ${@'setuptools3' if (d.getVar('LAYERSERIES_CORENAMES') in ["dunfell"]) else 'python_setuptools_build_meta'} native

PYPI_PACKAGE = "PyQt-builder"

DEPENDS += "python3-setuptools-scm-native"
