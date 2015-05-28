require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & Digia-Qt-LGPL-Exception-1.1 | LGPL-3.0) | GPL-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=cff17b12416c896e10ae2c17a64252e7 \
    file://LICENSE.LGPLv3;md5=c1939be5579666be947371bc8120425f \
    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.GPLv2;md5=e782f55badfa137e5e59c330f12cc8ed \
"

DEPENDS += "qtbase"
ALLOW_EMPTY_${PN} = "1"

SRC_URI += "file://0001-qtimageformats.pro-Make-the-dependencies-determinist.patch"

PACKAGECONFIG ?= "libtiff"
# Currently we don't have recipe for libmng, but lock it anyway so qtimageformats stay deterministic even when libmng is introduced
PACKAGECONFIG[jasper] = ",,jasper"
PACKAGECONFIG[libmng] = ",,libmng"
PACKAGECONFIG[libtiff] = ",,tiff"
PACKAGECONFIG[libwebp] = ",,libwebp"

EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'libmng', 'CONFIG+=OE_LIBMNG_ENABLED', '', d)}"
EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'jasper', 'CONFIG+=OE_JASPER_ENABLED', '', d)}"
EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'libtiff', 'CONFIG+=OE_LIBTIFF_ENABLED', '', d)}"
EXTRA_QMAKEVARS_PRE += "${@base_contains('PACKAGECONFIG', 'libwebp', 'CONFIG+=OE_LIBWEBP_ENABLED', '', d)}"

SRCREV = "98f83553e9fe2aa5fc54b32513af6ba3aa60a07b"
