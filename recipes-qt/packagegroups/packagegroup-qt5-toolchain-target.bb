# Copyright (C) 2014 O.S. Systems Software LTDA.

DESCRIPTION = "Target packages for Qt5 SDK"
LICENSE = "MIT"

inherit packagegroup

PACKAGEGROUP_DISABLE_COMPLEMENTARY = "1"

# Requires Ruby to work
USE_RUBY = " \
    qtquick1-dev \
    qtquick1-mkspecs \
    qtquick1-plugins \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtquick1-qmlplugins', '', d)} \
    qttranslations-qtquick1 \
    qtwebkit-dev \
    qtwebkit-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtwebkit-qmlplugins', '', d)} \
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
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-dev', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-mkspecs', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qt3d-qmlplugins', '', d)} \
    qtbase-dev \
    qtbase-fonts \
    qtbase-mkspecs \
    qtbase-plugins \
    qtbase-staticdev \
    qttranslations-qt \
    qttranslations-qtbase \
    qttranslations-qtconfig \
    qttranslations-qthelp \
    qtconnectivity-dev \
    qtconnectivity-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtconnectivity-qmlplugins', '', d)} \
    qttranslations-qtconnectivity \
    qtdeclarative-dev \
    qtdeclarative-mkspecs \
    qtdeclarative-plugins \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtdeclarative-qmlplugins', '', d)} \
    qtdeclarative-staticdev \
    qttranslations-qmlviewer \
    qttranslations-qtdeclarative \
    qtenginio-dev \
    qtenginio-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtenginio-qmlplugins', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtgraphicaleffects-qmlplugins', '', d)} \
    qtimageformats-dev \
    qtimageformats-plugins \
    qtlocation-dev \
    qtlocation-mkspecs \
    qtlocation-plugins \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtlocation-qmlplugins', '', d)} \
    qttranslations-qtlocation \
    qtmultimedia-dev \
    qtmultimedia-mkspecs \
    qtmultimedia-plugins \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtmultimedia-qmlplugins', '', d)} \
    qttranslations-qtmultimedia \
    qtscript-dev \
    qtscript-mkspecs \
    qttranslations-qtscript \
    qtsensors-dev \
    qtsensors-mkspecs \
    qtsensors-plugins \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtsensors-qmlplugins', '', d)} \
    qtserialport-dev \
    qtserialport-mkspecs \
    qtsvg-dev \
    qtsvg-mkspecs \
    qtsvg-plugins \
    qtsystems-dev \
    qtsystems-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtsystems-qmlplugins', '', d)} \
    qttools-dev \
    qttools-mkspecs \
    qttools-staticdev \
    qttools-tools \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '${USE_WAYLAND}', '', d)} \
    ${USE_RUBY} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '${USE_X11}', '', d)} \
    qtwebsockets-dev \
    qtwebsockets-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtwebsockets-qmlplugins', '', d)} \
    qttranslations-qtwebsockets \
    qtwebchannel-dev \
    qtwebchannel-mkspecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtwebchannel-qmlplugins', '', d)} \
    qtxmlpatterns-dev \
    qtxmlpatterns-mkspecs \
    qttranslations-qtxmlpatterns \
"

RRECOMMENDS_${PN} += " \
    qtquickcontrols-qmlplugins \
    qttools-plugins \
"
