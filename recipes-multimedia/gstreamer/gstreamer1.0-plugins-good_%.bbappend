inherit qmake5_paths

PACKAGECONFIG[qt5] = '-Dqt5=enabled \
                     ,-Dqt5=disabled \
                     ,gstreamer1.0-plugins-base qtbase qtdeclarative qtbase-native'
