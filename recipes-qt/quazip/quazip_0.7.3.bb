SUMMARY = "Qt/C++ wrapper for ZIP/UNZIP package"
HOMEPAGE = "http://quazip.sourceforge.net/"
BUGTRACKER = "https://sourceforge.net/p/quazip/bugs/"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=910d778aab53617cbaf13c4e1810e289"
DEPENDS = "qtbase"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BP}.tar.gz \
           file://0001-Append-LIB_ARCH-to-lib.patch \
          "
SRC_URI[md5sum] = "2ba7dd8b1d6dd588374c9fab5c46e76e"
SRC_URI[sha256sum] = "2ad4f354746e8260d46036cde1496c223ec79765041ea28eb920ced015e269b5"

inherit qmake5

EXTRA_QMAKEVARS_PRE += "PREFIX=${prefix} LIB_ARCH=${@d.getVar('baselib').replace('lib', '')}"
EXTRA_QMAKEVARS_POST += "SUBDIRS=${BPN}"
