require qt5module.inc

DEPENDS += "qtdeclarative gstreamer alsa-lib "

INC_PR = "r0"

PACKAGES += "${PN}-qmlplugins-dbg ${PN}-qmlplugins"

FILES_${PN}-qmlplugins = " \
    ${OE_QMAKE_PATH_QML}/QtMultimedia/qmldir \
    ${OE_QMAKE_PATH_QML}/QtMultimedia/plugins.qmltypes \
    ${OE_QMAKE_PATH_QML}/QtMultimedia/Video.qml \
    ${OE_QMAKE_PATH_QML}/QtMultimedia/libdeclarative_multimedia.so \
"

FILES_${PN}-qmlplugins-dbg = " \
    ${OE_QMAKE_PATH_QML}/QtMultimedia/.debug \
"
