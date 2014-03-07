require qt5-${PV}.inc
require ${PN}.inc

SRC_URI += "file://0015-Fix-linuxfb-argument-mmsize-parsing.patch"

SRC_URI[md5sum] = "fa005301a2000b92b61b63edc042567b"
SRC_URI[sha256sum] = "acdfd1aa2548ebea1d922e8e24e5c59f5fc3b2beae7c8003ba47d773bfcc94c0"
