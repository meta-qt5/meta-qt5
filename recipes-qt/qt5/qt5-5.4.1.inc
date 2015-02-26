# Copyright (C) 2012 O.S. Systems Software LTDA.
# Copyright (C) 2013 Martin Jansa <martin.jansa@gmail.com>

QT_VERSION ?= "${PV}"

# it's different for RC versions
QT_VERSION_DIR ?= "5.4"

SRC_URI += " \
    http://download.qt-project.org/official_releases/qt/${QT_VERSION_DIR}/${QT_VERSION}/submodules/${QT_MODULE}-opensource-src-${QT_VERSION}.tar.xz \
"

S = "${WORKDIR}/${QT_MODULE}-opensource-src-${QT_VERSION}"

LICENSE = "GFDL-1.3 & LGPL-2.1 | LGPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE.LGPLv21;md5=d87ae0d200af76dca730d911474cbe5b \
                    file://LICENSE.LGPLv3;md5=ffcfac38a32c9ebdb8ff768fa1702478 \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
                    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e"
