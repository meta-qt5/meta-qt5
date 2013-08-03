# Select which version you prefer by defining QT5_VERSION and including this file
# possible values now "5.0.2", "5.1.0", "5.1.0+git%"

QT5_VERSION ?= "5.1.0"

PREFERRED_VERSION_qtbase-native = "${QT5_VERSION}"
PREFERRED_VERSION_qtbase = "${QT5_VERSION}"
PREFERRED_VERSION_qtdeclarative = "${QT5_VERSION}"
PREFERRED_VERSION_qtjsbackend = "${QT5_VERSION}"
PREFERRED_VERSION_qtjsbackend-native = "${QT5_VERSION}"
PREFERRED_VERSION_qtgraphicaleffects = "${QT5_VERSION}"
PREFERRED_VERSION_qtimageformats = "${QT5_VERSION}"
PREFERRED_VERSION_qtmultimedia = "${QT5_VERSION}"
PREFERRED_VERSION_qtquick1 = "${QT5_VERSION}"
PREFERRED_VERSION_qtsensors = "${QT5_VERSION}"
PREFERRED_VERSION_qtscript = "${QT5_VERSION}"
PREFERRED_VERSION_qtsvg = "${QT5_VERSION}"
PREFERRED_VERSION_qtwebkit = "${QT5_VERSION}"
PREFERRED_VERSION_qtxmlpatterns = "${QT5_VERSION}"

# append % only if it isn't included already
# 5.1.0+git%% won't work and it's exactly where we need to overcome DEFAULT_PREFERENCE with PREFERRED_VERSION
QT5_VERSION_WAYLAND = "${@base_contains('QT5_VERSION', '5.1.0+git%', '${QT5_VERSION}', '${QT5_VERSION}%', d)}"
PREFERRED_VERSION_qtwayland = "${QT5_VERSION_WAYLAND}"
