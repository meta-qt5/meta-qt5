# Copyright (C) 2012 O.S. Systems Software LTDA.
# Copyright (C) 2013-2014 Martin Jansa <martin.jansa@gmail.com>

QT_MODULE_BRANCH ?= "5.4"

# each module needs to define valid SRCREV
SRC_URI = " \
    ${QT_GIT}/qt/${QT_MODULE}.git;branch=${QT_MODULE_BRANCH} \
"

S = "${WORKDIR}/git"

PV = "5.4.1+git${SRCPV}"
