inherit qmake5_paths

PACKAGECONFIG[qt5] = '--enable-qt \
                      --with-moc="${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/moc" \
                      --with-uic="${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/uic" \
                      --with-rcc="${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/rcc" \
                     ,--disable-qt,gstreamer1.0-plugins-base qtbase qtdeclarative qtbase-native'
