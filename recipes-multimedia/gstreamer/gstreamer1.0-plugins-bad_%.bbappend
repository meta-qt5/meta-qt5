inherit qmake5_paths

PACKAGECONFIG[qt5] = '--enable-qt \
                      --with-moc="${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/moc" \
                      --with-uic="${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/uic" \
                      --with-rcc="${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/rcc" \
                     ,--disable-qt,qtbase qtdeclarative qtbase-native'

# The GStreamer Qt5 plugin needs desktop OpenGL or OpenGL ES to work, so make sure it is enabled
python() {
    cur_packageconfig = d.getVar('PACKAGECONFIG',True).split()
    if 'qt5' in cur_packageconfig and not (('opengl' in cur_packageconfig) or ('gles2' in cur_packageconfig)):
        gl_packageconfig = d.getVar('PACKAGECONFIG_GL',True)
        d.appendVar('PACKAGECONFIG', ' ' + gl_packageconfig)
}
