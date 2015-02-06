# Copyright (C) 2014 O.S. Systems Software LTDA.

DESCRIPTION = "Target packages for Qt5 SDK"
LICENSE = "MIT"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

# Requires meta-ruby to work
USE_RUBY = " \
    qtquick1-dev \
    qtquick1-mkspecs \
    qtquick1-plugins \
    qtquick1-qmlplugins \
    qtwebkit-dev \
    qtwebkit-mkspecs \
    qtwebkit-qmlplugins \
"

# Requires Wayland to work
USE_WAYLAND = " \
    qtwayland-dev \
    qtwayland-mkspecs \
    qtwayland-plugins \
    qtwayland-tools \
"

# Requires X11 to work
USE_X11 = " \
    qtx11extras-dev \
    qtx11extras-mkspecs \
"

RDEPENDS_${PN} += " \
    packagegroup-core-standalone-sdk-target \
    libsqlite3-dev \
    qt3d-dev \
    qt3d-mkspecs \
    qt3d-qmlplugins \
    qtbase-dev \
    qtbase-fonts \
    qtbase-mkspecs \
    qtbase-plugins \
    qtbase-staticdev \
    qtconnectivity-dev \
    qtconnectivity-mkspecs \
    qtconnectivity-qmlplugins \
    qtdeclarative-dev \
    qtdeclarative-mkspecs \
    qtdeclarative-plugins \
    qtdeclarative-qmlplugins \
    qtdeclarative-staticdev \
    qtenginio-dev \
    qtenginio-mkspecs \
    qtenginio-qmlplugins \
    qtgraphicaleffects-qmlplugins \
    qtimageformats-dev \
    qtimageformats-plugins \
    qtlocation-dev \
    qtlocation-mkspecs \
    qtlocation-plugins \
    qtlocation-qmlplugins \
    qtmultimedia-dev \
    qtmultimedia-mkspecs \
    qtmultimedia-plugins \
    qtmultimedia-qmlplugins \
    qtscript-dev \
    qtscript-mkspecs \
    qtsensors-dev \
    qtsensors-mkspecs \
    qtsensors-plugins \
    qtsensors-qmlplugins \
    qtserialport-dev \
    qtserialport-mkspecs \
    qtsvg-dev \
    qtsvg-mkspecs \
    qtsvg-plugins \
    qtsystems-dev \
    qtsystems-mkspecs \
    qtsystems-qmlplugins \
    qttools-dev \
    qttools-mkspecs \
    qttools-plugins \
    qttools-staticdev \
    qttools-tools \
    ${@base_contains('DISTRO_FEATURES', 'wayland', '${USE_WAYLAND}', '', d)} \
    ${@base_contains('BBFILE_COLLECTIONS', 'ruby-layer', '${USE_RUBY}', '', d)} \
    ${@base_contains('DISTRO_FEATURES', 'x11', '${USE_X11}', '', d)} \
    qtwebsockets-dev \
    qtwebsockets-mkspecs \
    qtwebsockets-qmlplugins \
    qtwebchannel-dev \
    qtwebchannel-mkspecs \
    qtwebchannel-qmlplugins \
    qtxmlpatterns-dev \
    qtxmlpatterns-mkspecs \
"

RRECOMMENDS_${PN} += " \
    qtquickcontrols-qmlplugins \
"
