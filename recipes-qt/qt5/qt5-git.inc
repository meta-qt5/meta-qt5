# Copyright (C) 2012 O.S. Systems Software LTDA.
# Copyright (C) 2013-2014 Martin Jansa <martin.jansa@gmail.com>

QT_MODULE_BRANCH ?= "5.4"

# each module needs to define valid SRCREV
SRC_URI += " \
    ${QT_GIT}/qt/${QT_MODULE}.git;branch=${QT_MODULE_BRANCH} \
"

S = "${WORKDIR}/git"

PV = "5.4.1+git${SRCPV}"

FILESEXTRAPATHS =. "${FILE_DIRNAME}/${BPN}-git:"

DEFAULT_PREFERENCE = "-1"

LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & Digia-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = "file://LICENSE.LGPLv21;md5=cff17b12416c896e10ae2c17a64252e7 \
                    file://LICENSE.LGPLv3;md5=c1939be5579666be947371bc8120425f \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
                    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e"
