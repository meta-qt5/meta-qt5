# Copyright (C) 2014 O.S. Systems Software LTDA.

DESCRIPTION = "Target packages for Qt5 SDK"
LICENSE = "MIT"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

# Requires meta-ruby to work
USE_RUBY = " \
    qtwebkit-mkspecs \
    qtwebkit-dev \
"

RDEPENDS_${PN} += " \
    packagegroup-core-standalone-sdk-target \
    libsqlite3-dev \
    qtbase-mkspecs \
    qtscript-mkspecs \
    qtxmlpatterns-mkspecs \
    qtdeclarative-mkspecs \
    qtsensors-mkspecs \
    qt3d-mkspecs \
    qtlocation-mkspecs \
    qtsvg-mkspecs \
    qtbase-dev \
    qtdeclarative-dev \
    qtscript-dev \
    qt3d-dev \
    qtlocation-dev \
    qtsensors-dev \
    qtsvg-dev \
    qtxmlpatterns-dev \
    qtdeclarative-dev \
    qtdeclarative-plugins \
    qtdeclarative-qmlplugins \
    qtgraphicaleffects-dev \
    ${@base_contains('BBFILE_COLLECTIONS', 'ruby-layer', '${USE_RUBY}', '', d)} \
"
