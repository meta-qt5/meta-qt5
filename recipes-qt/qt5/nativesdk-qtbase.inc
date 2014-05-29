DESCRIPTION = "SDK version of Qt/[X11|Mac|Embedded]"
DEPENDS = "nativesdk-zlib nativesdk-dbus qtbase-native"
SECTION = "libs"
HOMEPAGE = "http://qt-project.org"

QT_MODULE = "qtbase"

require nativesdk-qt5.inc

# it's already included with newer oe-core, but include it here for dylan
FILESEXTRAPATHS =. "${FILE_DIRNAME}/qtbase:"

PR = "r1"

# common with -native and target version
SRC_URI += "\
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0003-Add-external-hostbindir-option.patch \
    file://0004-configureapp-Prefix-default-LIBDIRS-and-INCDIRS-with.patch \
    file://0005-qt_module-Fix-pkgconfig-replacement.patch \
    file://0006-configure-don-t-export-SYSTEM_VARIABLES-to-.qmake.va.patch \
    file://0007-configure.prf-Allow-to-add-extra-arguments-to-make.patch \
    file://0008-configure-make-pulseaudio-alsa-freetype-a-configurab.patch \
    file://0009-cmake-Use-OE_QMAKE_PATH_EXTERNAL_HOST_BINS-to-determ.patch \
    file://0015-Fix-missing-plugin_types.patch \
    file://0016-Revert-eglfs-Print-the-chosen-config-in-debug-mode.patch \
"

# common with -native
SRC_URI += " \
    file://0010-Always-build-uic.patch \
"

# specific for nativesdk version
SRC_URI += " \
    file://0011-configure-preserve-built-qmake-and-swap-with-native-.patch \
    file://0012-configure-bump-path-length-from-256-to-512-character.patch \
"

PACKAGES = "${PN}-tools-dbg ${PN}-tools-dev ${PN}-tools-staticdev ${PN}-tools"

PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"

FILES_${PN}-tools-dev = " \
    ${includedir} \
    ${FILES_SOLIBSDEV} ${libdir}/*.la \
    ${OE_QMAKE_PATH_ARCHDATA}/mkspecs \
"

FILES_${PN}-tools-staticdev = " \
    ${libdir}/libQt5Bootstrap.a \
"

FILES_${PN}-tools-dbg = " \
    ${libdir}/.debug \
    ${OE_QMAKE_PATH_BINS}/.debug \
"

FILES_${PN}-tools = " \
    ${libdir}/lib*${SOLIBS} \
    ${OE_QMAKE_PATH_BINS}/* \
"

# qttools binaries are placed in a subdir of bin in order to avoid
# collisions with qt4. This would trigger debian.bbclass to rename the
# package, since it doesn't detect binaries in subdirs. Explicitly
# disable package auto-renaming for the tools-package.
DEBIAN_NOAUTONAME_${PN}-tools = "1"

QT_CONFIG_FLAGS += " \
    -shared \
    -silent \
    -no-pch \
    -no-rpath \
    -pkg-config \
    ${EXTRA_OECONF} \
"

# qtbase is exception, as these are used as install path for sysroots
OE_QMAKE_PATH_HOST_DATA = "${libdir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_HOST_LIBS = "${libdir}"

do_generate_qt_config_file() {
    cat > ${QT_CONF_PATH} <<EOF
[Paths]
Prefix = ${OE_QMAKE_PATH_PREFIX}
Headers = ${OE_QMAKE_PATH_HEADERS}
Libraries = ${OE_QMAKE_PATH_LIBS}
ArchData = ${OE_QMAKE_PATH_ARCHDATA}
Data = ${OE_QMAKE_PATH_DATA}
Binaries = ${OE_QMAKE_PATH_BINS}
LibraryExecutables = ${OE_QMAKE_PATH_LIBEXECS}
Plugins = ${OE_QMAKE_PATH_PLUGINS}
Imports = ${OE_QMAKE_PATH_IMPORTS}
Qml2Imports = ${OE_QMAKE_PATH_QML}
Translations = ${OE_QMAKE_PATH_TRANSLATIONS}
Documentation = ${OE_QMAKE_PATH_DOCS}
Settings = ${OE_QMAKE_PATH_SETTINGS}
Examples = ${OE_QMAKE_PATH_EXAMPLES}
Tests = ${OE_QMAKE_PATH_TESTS}
HostBinaries = ${OE_QMAKE_PATH_HOST_BINS}
HostData = ${OE_QMAKE_PATH_HOST_DATA}
HostLibraries = ${OE_QMAKE_PATH_HOST_LIBS}
HostSpec = ${OE_QMAKESPEC}
TartgetSpec = ${OE_XQMAKESPEC}
ExternalHostBinaries = ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}
Sysroot =
EOF
}

do_generate_qt_config_file_append() {
    cat >> ${QT_CONF_PATH} <<EOF

[EffectivePaths]
Prefix=..
EOF
}

# qtbase is exception, we need to use mkspecs from ${S}
QMAKE_MKSPEC_PATH = "${B}"

# qtbase is exception, configure script is using our get(X)QEvalMakeConf and setBootstrapEvalVariable functions to read it from shell
export OE_QMAKE_COMPILER
export OE_QMAKE_CC
export OE_QMAKE_CFLAGS
export OE_QMAKE_CXX
export OE_QMAKE_CXXFLAGS
export OE_QMAKE_LINK
export OE_QMAKE_LDFLAGS
export OE_QMAKE_AR
export OE_QMAKE_STRIP

# another exception is that we need to run bin/qmake, because EffectivePaths are relative to qmake location
OE_QMAKE_QMAKE_ORIG = "${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/qmake"
OE_QMAKE_QMAKE = "bin/qmake"

do_configure() {
    # we need symlink in path relative to source, because
    # EffectivePaths:Prefix is relative to qmake location
    # Also, configure expects qmake-native to swap with real one
    if [ ! -e ${B}/bin/qmake-native ]; then
        mkdir ${B}/bin
        ln -sf ${OE_QMAKE_QMAKE_ORIG} ${B}/bin/qmake-native
    fi

    ${S}/configure -v \
        -dont-process \
        -opensource -confirm-license \
        -sysroot ${STAGING_DIR_NATIVE} \
        -no-gcc-sysroot \
        -system-zlib \
        -no-libjpeg \
        -no-libpng \
        -no-gif \
        -no-accessibility \
        -no-cups \
        -no-nis \
        -no-gui \
        -no-qml-debug \
        -no-sql-mysql \
        -no-sql-sqlite \
        -no-opengl \
        -no-openssl \
        -no-xcb \
        -verbose \
        -release \
        -prefix ${OE_QMAKE_PATH_PREFIX} \
        -bindir ${OE_QMAKE_PATH_BINS} \
        -libdir ${OE_QMAKE_PATH_LIBS} \
        -datadir ${OE_QMAKE_PATH_DATA} \
        -sysconfdir ${OE_QMAKE_PATH_SETTINGS} \
        -docdir ${OE_QMAKE_PATH_DOCS} \
        -headerdir ${OE_QMAKE_PATH_HEADERS} \
        -archdatadir ${OE_QMAKE_PATH_ARCHDATA} \
        -libexecdir ${OE_QMAKE_PATH_LIBEXECS} \
        -plugindir ${OE_QMAKE_PATH_PLUGINS} \
        -importdir ${OE_QMAKE_PATH_IMPORTS} \
        -qmldir ${OE_QMAKE_PATH_QML} \
        -translationdir ${OE_QMAKE_PATH_TRANSLATIONS} \
        -testsdir ${OE_QMAKE_PATH_TESTS} \
        -hostbindir ${OE_QMAKE_PATH_HOST_BINS} \
        -hostdatadir ${OE_QMAKE_PATH_HOST_DATA} \
        -external-hostbindir ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS} \
        -no-glib \
        -no-iconv \
        -silent \
        -nomake examples \
        -nomake tests \
        -nomake libs \
        -no-compile-examples \
        -no-rpath \
        -platform ${OE_QMAKESPEC} \
        -xplatform linux-oe-g++ \
        ${QT_CONFIG_FLAGS}

    bin/qmake ${OE_QMAKE_DEBUG_OUTPUT} ${S} -o Makefile || die "Configuring qt with qmake failed. EXTRA_OECONF was ${EXTRA_OECONF}"
}

do_install() {
    # Fix install paths for all
    find -name "Makefile*" | xargs sed -i "s,(INSTALL_ROOT)${STAGING_DIR_NATIVE}${STAGING_DIR_NATIVE},(INSTALL_ROOT)${STAGING_DIR_NATIVE},g"

    oe_runmake install INSTALL_ROOT=${D}

    # replace the native qmake installed above with nativesdk version
    rm -rf ${D}${OE_QMAKE_PATH_HOST_BINS}/qmake
    install -m 755 ${B}/bin/qmake-real ${D}${OE_QMAKE_PATH_HOST_BINS}/qmake

    # for modules which are still using syncqt and call qtPrepareTool(QMAKE_SYNCQT, syncqt)
    # e.g. qt3d, qtwayland
    ln -sf syncqt.pl ${D}${OE_QMAKE_PATH_QT_BINS}/syncqt

    # remove things unused in nativesdk, we need the headers, Qt5Core
    # and Qt5Bootstrap.
    rm -rf ${D}${datadir} \
           ${D}/${OE_QMAKE_PATH_PLUGINS} \
           ${D}${libdir}/cmake \
           ${D}${libdir}/pkgconfig
    find ${D}${libdir} -maxdepth 1 -name 'lib*' -and \
                                   -not -name 'libQt5Core.so*' -and \
                                   -not -name 'libQt5Bootstrap.a' \
                                   -exec rm '{}' ';'
}
