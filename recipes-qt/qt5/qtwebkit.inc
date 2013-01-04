require qt5module.inc

DEPENDS += "qtdeclarative icu ruby-native sqlite3"

INC_PR = "r0"

do_configure_append() {
    # Fix rpaths for QtWebProcess
    find -name "Makefile*" | xargs sed -i "s|-Wl,-rpath,${SEPB}/lib||g"
}

PACKAGES += "${PN}-qmlplugins-dbg ${PN}-qmlplugins"

FILES_${PN}-qmlplugins = " \
    ${OE_QMAKE_PATH_QML}/QtWebKit/libqmlwebkitplugin.so \
    ${OE_QMAKE_PATH_QML}/QtWebKit/qmldir \
    ${OE_QMAKE_PATH_QML}/QtWebKit/experimental/libqmlwebkitexperimentalplugin.so \
    ${OE_QMAKE_PATH_QML}/QtWebKit/experimental/qmldir \
"

FILES_${PN}-qmlplugins-dbg = " \
    ${OE_QMAKE_PATH_QML}/QtWebKit/experimental/.debug \
    ${OE_QMAKE_PATH_QML}/QtWebKit/.debug \
"
