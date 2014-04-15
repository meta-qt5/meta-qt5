# Copyright (C) 2014 O.S. Systems Software LTDA.

TOOLCHAIN_HOST_TASK += "nativesdk-packagegroup-qt5-toolchain-host"
TOOLCHAIN_TARGET_TASK += "packagegroup-qt5-toolchain-target"

# This allow reuse of Qt paths
inherit qmake5_paths

toolchain_create_sdk_env_script_append () {
    echo 'export PATH=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}:$PATH' >> $script
    echo 'export OE_QMAKE_CFLAGS="$CFLAGS"' >> $script
    echo 'export OE_QMAKE_CXXFLAGS="$CXXFLAGS"' >> $script
    echo 'export OE_QMAKE_LDFLAGS="$LDFLAGS"' >> $script
    echo 'export OE_QMAKE_CC=$CC' >> $script
    echo 'export OE_QMAKE_CXX=$CXX' >> $script
    echo 'export OE_QMAKE_LINK=$CXX' >> $script
    echo 'export OE_QMAKE_AR=$AR' >> $script
    echo 'export OE_QMAKE_LIBDIR_QT=${SDKTARGETSYSROOT}${OE_QMAKE_PATH_LIBS}' >> $script
    echo 'export OE_QMAKE_INCDIR_QT=${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QT_HEADERS}' >> $script
    echo 'export OE_QMAKE_MOC=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/moc' >> $script
    echo 'export OE_QMAKE_UIC=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/uic' >> $script
    echo 'export OE_QMAKE_RCC=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/rcc' >> $script
    echo 'export OE_QMAKE_QDBUSCPP2XML=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/qdbuscpp2xml' >> $script
    echo 'export OE_QMAKE_QDBUSXML2CPP=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/qdbusxml2cpp' >> $script
    echo 'export OE_QMAKE_QT_CONFIG=${SDKTARGETSYSROOT}${OE_QMAKE_PATH_LIBS}/${QT_DIR_NAME}/mkspecs/qconfig.pri' >> $script
    echo 'export QMAKESPEC=${SDKTARGETSYSROOT}${OE_QMAKE_PATH_LIBS}/${QT_DIR_NAME}/mkspecs/linux-oe-g++' >> $script
    echo 'export QT_CONF_PATH=${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/qt.conf' >> $script

    # make a symbolic link to mkspecs for compatibility with QTCreator
    (cd ${SDK_OUTPUT}/${SDKPATHNATIVE}; \
         ln -sf ${SDKTARGETSYSROOT}${libdir}/${QT_DIR_NAME}/mkspecs mkspecs;)

    # Generate a qt.conf file to be deployed with the SDK
    qtconf=${SDK_OUTPUT}/${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}/qt.conf
    touch $qtconf
    echo '[Paths]' >> $qtconf
    echo 'Prefix = ${SDKTARGETSYSROOT}' >> $qtconf
    echo 'Headers = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QT_HEADERS}' >> $qtconf
    echo 'Libraries = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_LIBS}' >> $qtconf
    echo 'ArchData = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QT_ARCHDATA}' >> $qtconf
    echo 'Data = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QT_DATA}' >> $qtconf
    echo 'Binaries = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QT_BINS}' >> $qtconf
    echo 'LibraryExecutables = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_LIBEXECS}' >> $qtconf
    echo 'Plugins = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_PLUGINS}' >> $qtconf
    echo 'Imports = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_IMPORTS}' >> $qtconf
    echo 'Qml2Imports = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QML}' >> $qtconf
    echo 'Translations = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QT_TRANSLATIONS}' >> $qtconf
    echo 'Documentation = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QT_DOCS}' >> $qtconf
    echo 'Settings = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QT_SETTINGS}' >> $qtconf
    echo 'Examples = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QT_EXAMPLES}' >> $qtconf
    echo 'Tests = ${SDKTARGETSYSROOT}${OE_QMAKE_PATH_QT_TESTS}' >> $qtconf
    echo 'HostPrefix = ${SDKPATHNATIVE}' >> $qtconf
    echo 'HostBinaries = ${SDKPATHNATIVE}${OE_QMAKE_PATH_HOST_BINS}' >> $qtconf
}

FEATURE_PACKAGES_qtcreator-debug = "packagegroup-qt5-qtcreator-debug"
