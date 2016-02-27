require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & Digia-Qt-LGPL-Exception-1.1 | LGPL-3.0) | GPL-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
    file://LICENSE.LGPLv3;md5=b8c75190712063cde04e1f41b6fdad98 \
    file://LICENSE.GPLv3;md5=40f9bf30e783ddc201497165dfb32afb \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.GPLv2;md5=05832301944453ec79e40ba3c3cfceec \
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

SRCREV = "cb800de1f42aaacdeb423d30adbcbf8975e32f2f"
