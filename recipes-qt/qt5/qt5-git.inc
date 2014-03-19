# Copyright (C) 2012 O.S. Systems Software LTDA.
# Copyright (C) 2013-2014 Martin Jansa <martin.jansa@gmail.com>

QT_MODULE_BRANCH ?= "stable"

# each module needs to define valid SRCREV
SRC_URI += " \
    git://qt.gitorious.org/qt/${QT_MODULE}.git;branch=${QT_MODULE_BRANCH} \
"

S = "${WORKDIR}/git"

PV = "5.2.1+5.3.0-beta1+git${SRCPV}"

FILESEXTRAPATHS =. "${FILE_DIRNAME}/${BPN}-git:"

DEFAULT_PREFERENCE = "-1"

LICENSE = "GFDL-1.3 & LGPL-2.1 | GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=2abca611d56c23eb99943bde21c80a0d \
                    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
                    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e"
