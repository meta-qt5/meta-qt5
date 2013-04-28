# Select which version you prefer by defining QT5_VERSION and including this file
# possible values now "5.0.0", "5.0.1", "5.0.2", "5.0.2+git%"

QT5_VERSION ?= "5.0.1"

PREFERRED_VERSION_qtbase-native = "${QT5_VERSION}"
PREFERRED_VERSION_qtbase = "${QT5_VERSION}"
PREFERRED_VERSION_qtdeclarative = "${QT5_VERSION}"
PREFERRED_VERSION_qtjsbackend = "${QT5_VERSION}"
PREFERRED_VERSION_qtjsbackend-native = "${QT5_VERSION}"
