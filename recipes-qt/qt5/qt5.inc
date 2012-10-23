# Copyright (C) 2012 O.S. Systems Software LTDA.

QT_MODULE ?= "${PN}"
QT_VERSION ?= "${PV}-beta1"

SRC_URI = "http://releases.qt-project.org/qt5.0/beta1/split_sources/${QT_MODULE}-opensource-src-${QT_VERSION}.tar.xz"

S = "${WORKDIR}/${QT_MODULE}-opensource-src-${QT_VERSION}"
