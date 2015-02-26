require qt5-${PV}.inc
require ${PN}.inc

SRC_URI[md5sum] = "bf3ad162f85537d55310d0aef9ee4515"
SRC_URI[sha256sum] = "3242abcab5b5f2a80529d16cadd5003600d3f6720220d3aa38ec756e609faab0"

SRC_URI += " \
    file://0001-functions.prf-Don-t-match-QMAKE_EXT_CPP-or-QMAKE_EXT.patch \
    file://0002-functions.prf-Make-sure-we-only-use-the-file-name-to.patch \
    file://0003-functions.prf-allow-build-for-linux-oe-g-platform.patch \
    file://0001-chromium-base.gypi-include-atomicops_internals_x86_gcc.cc-whe.patch \
    file://0001-eLinux-build-ffmpegsumo-as-well.patch \
    file://0004-dont-fail-libcap-config-test.patch \
"
