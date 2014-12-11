require qt5-${PV}.inc
require ${PN}.inc

SRC_URI[md5sum] = "ec05c84be8b8d582d5321f0656c44005"
SRC_URI[sha256sum] = "ab91a5c0d63f47de91310c8e56255ac1c8ec1b7f9c414cc7e040f72b28e4153b"

SRC_URI += " \
    file://0001-functions.prf-Don-t-match-QMAKE_EXT_CPP-or-QMAKE_EXT.patch \
    file://0002-functions.prf-Make-sure-we-only-use-the-file-name-to.patch \
    file://0003-functions.prf-allow-build-for-linux-oe-g-platform.patch \
    file://0001-chromium-base.gypi-include-atomicops_internals_x86_gcc.cc-whe.patch \
"
