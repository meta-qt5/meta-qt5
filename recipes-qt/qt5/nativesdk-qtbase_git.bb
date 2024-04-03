DESCRIPTION = "SDK version of Qt/[X11|Mac|Embedded]"
DEPENDS = "nativesdk-zlib qtbase-native"
SECTION = "libs"
HOMEPAGE = "http://qt-project.org"

LICENSE = "GFDL-1.3 & BSD-3-Clause & ( GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0-or-later | LGPL-3.0-only | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.QT-LICENSE-AGREEMENT;md5=38de3b110ade3b6ee2f0b6a95ab16f1a \
"

QT_MODULE = "qtbase"

inherit pkgconfig

require nativesdk-qt5.inc
require qt5-git.inc

# it's already included with newer oe-core, but include it here for dylan
FILESEXTRAPATHS =. "${FILE_DIRNAME}/qtbase:"

# common for qtbase-native, qtbase-nativesdk and qtbase
# Patches from https://github.com/meta-qt5/qtbase/commits/b5.15-shared
# 5.15.meta-qt5-shared.4
SRC_URI += "\
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-cmake-Use-OE_QMAKE_PATH_EXTERNAL_HOST_BINS.patch \
    file://0003-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0004-configure-bump-path-length-from-256-to-512-character.patch \
    file://0005-Disable-all-unknown-features-instead-of-erroring-out.patch \
    file://0006-Pretend-Qt5-wasn-t-found-if-OE_QMAKE_PATH_EXTERNAL_H.patch \
    file://0007-Delete-qlonglong-and-qulonglong.patch \
    file://0009-Add-OE-specific-specs-for-clang-compiler.patch \
    file://0010-linux-clang-Invert-conditional-for-defining-QT_SOCKL.patch \
    file://0011-tst_qlocale-Enable-QT_USE_FENV-only-on-glibc.patch \
    file://0012-Disable-ltcg-for-host_build.patch \
    file://0013-Qt5GuiConfigExtras.cmake.in-cope-with-variable-path-.patch \
    file://0014-corelib-Include-sys-types.h-for-uint32_t.patch \
    file://0015-Define-QMAKE_CXX.COMPILER_MACROS-for-clang-on-linux.patch \
    file://0016-tst_qpainter-FE_-macros-are-not-defined-for-every-pl.patch \
    file://0017-Define-__NR_futex-if-it-does-not-exist.patch \
    file://0018-Revert-Fix-workaround-in-pthread-destructor.patch \
    file://0019-tst_QPluginLoader-Simplify-creating-a-fake-pointer-i.patch \
    file://0021-rcc-Just-dcument-file-name-without-full-path-to-redu.patch \
    file://0022-testlib-don-t-track-the-build-or-source-directories.patch \
"

# common for qtbase-native and nativesdk-qtbase
# Patches from https://github.com/meta-qt5/qtbase/commits/b5.15-native
# 5.15.meta-qt5-native.4
SRC_URI += " \
    file://0023-Always-build-uic-and-qvkgen.patch \
    file://0024-Avoid-renameeat2-for-native-sdk-builds.patch \
"

# CMake's toolchain configuration of nativesdk-qtbase
SRC_URI += " \
    file://OEQt5Toolchain.cmake \
"

PACKAGE_DEBUG_SPLIT_STYLE = "debug-without-src"

FILES:${PN}-dev += " \
    ${OE_QMAKE_PATH_ARCHDATA}/mkspecs \
    ${OE_QMAKE_PATH_LIBS}/*.prl \
"

FILES:${PN} += " \
    ${SDKPATHNATIVE}/environment-setup.d \
    ${OE_QMAKE_PATH_PLUGINS} \
    ${OE_QMAKE_PATH_LIBS}/metatypes \
"

# qttools binaries are placed in a subdir of bin in order to avoid
# collisions with qt4. This would trigger debian.bbclass to rename the
# package, since it doesn't detect binaries in subdirs. Explicitly
# disable package auto-renaming for the tools-package.
DEBIAN_NOAUTONAME:${PN} = "1"

PACKAGECONFIG ?= ""
PACKAGECONFIG[gui] = "-gui -qpa offscreen,-no-gui,"
PACKAGECONFIG[imageformats] = "-qt-libpng -qt-libjpeg -gif -ico, -no-libpng -no-libjpeg -no-ico -no-gif,"
PACKAGECONFIG[openssl] = "-openssl,-no-openssl,openssl,libssl"

QT_CONFIG_FLAGS += " \
    -shared \
    -silent \
    -no-pch \
    -pkg-config \
    ${PACKAGECONFIG_CONFARGS} \
"

# qtbase is exception, as these are used as install path for sysroots
OE_QMAKE_PATH_HOST_DATA = "${libdir}${QT_DIR_NAME}"
OE_QMAKE_PATH_HOST_LIBS = "${libdir}"

# for qtbase configuration we need default settings
# since we cannot set empty set filename to a not existent file
deltask generate_qt_config_file

do_configure() {
    # Regenerate header files when they are included in source tarball
    # Otherwise cmake files don't set PRIVATE_HEADERS correctly
    rm -rf ${S}/include
    mkdir -p ${S}/.git || true

    ${S}/configure -v \
        -${QT_EDITION} -confirm-license \
        -sysroot ${STAGING_DIR_TARGET} \
        -no-gcc-sysroot \
        -system-zlib \
        -dbus-runtime \
        -no-accessibility \
        -no-cups \
        -no-sql-mysql \
        -no-sql-sqlite \
        -no-opengl \
        -no-xcb \
        -no-feature-bearermanagement \
        -no-icu \
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
        -no-compile-examples \
        -platform ${OE_QMAKE_PLATFORM_NATIVE} \
        -xplatform ${OE_QMAKE_PLATFORM} \
        ${QT_CONFIG_FLAGS}
}

do_install() {
    qmake5_base_do_install

    # remove things unused in nativesdk, we need the headers and libs
    rm -rf ${D}${datadir} \
           ${D}${libdir}/cmake \
           ${D}${libdir}/pkgconfig

    # Install CMake's toolchain configuration
    mkdir -p ${D}${datadir}/cmake/OEToolchainConfig.cmake.d/
    install -m 644 ${WORKDIR}/OEQt5Toolchain.cmake ${D}${datadir}/cmake/OEToolchainConfig.cmake.d/

    # Fix up absolute paths in scripts
    sed -i -e '1s,#!/usr/bin/python,#! ${USRBINPATH}/env python,' \
        ${D}${OE_QMAKE_PATH_QT_ARCHDATA}/mkspecs/features/uikit/devices.py
}

fakeroot do_generate_qt_environment_file() {
    mkdir -p ${D}${SDKPATHNATIVE}/environment-setup.d/
    script=${D}${SDKPATHNATIVE}/environment-setup.d/qt5.sh

    echo 'export PATH=${OE_QMAKE_PATH_HOST_BINS}:$PATH' > $script
    echo 'export OE_QMAKE_CFLAGS="$CFLAGS"' >> $script
    echo 'export OE_QMAKE_CXXFLAGS="$CXXFLAGS"' >> $script
    echo 'export OE_QMAKE_LDFLAGS="$LDFLAGS"' >> $script
    echo 'export OE_QMAKE_CC="$CC"' >> $script
    echo 'export OE_QMAKE_CXX="$CXX"' >> $script
    echo 'export OE_QMAKE_LINK="$CXX"' >> $script
    echo 'export OE_QMAKE_AR=$AR' >> $script
    echo 'export OE_QMAKE_OBJCOPY=$OBJCOPY' >> $script
    echo 'export OE_QMAKE_STRIP=$STRIP' >> $script
    echo 'export QT_CONF_PATH=${OE_QMAKE_PATH_HOST_BINS}/qt.conf' >> $script
    echo 'export OE_QMAKE_LIBDIR_QT=`qmake -query QT_INSTALL_LIBS`' >> $script
    echo 'export OE_QMAKE_INCDIR_QT=`qmake -query QT_INSTALL_HEADERS`' >> $script
    echo 'export OE_QMAKE_MOC=${OE_QMAKE_PATH_HOST_BINS}/moc' >> $script
    echo 'export OE_QMAKE_UIC=${OE_QMAKE_PATH_HOST_BINS}/uic' >> $script
    echo 'export OE_QMAKE_RCC=${OE_QMAKE_PATH_HOST_BINS}/rcc' >> $script
    echo 'export OE_QMAKE_QDBUSCPP2XML=${OE_QMAKE_PATH_HOST_BINS}/qdbuscpp2xml' >> $script
    echo 'export OE_QMAKE_QDBUSXML2CPP=${OE_QMAKE_PATH_HOST_BINS}/qdbusxml2cpp' >> $script
    echo 'export OE_QMAKE_QT_CONFIG=`qmake -query QT_INSTALL_LIBS`${QT_DIR_NAME}/mkspecs/qconfig.pri' >> $script
    echo 'export OE_QMAKE_PATH_HOST_BINS=${OE_QMAKE_PATH_HOST_BINS}' >> $script
    echo 'export QMAKESPEC=`qmake -query QT_INSTALL_LIBS`${QT_DIR_NAME}/mkspecs/linux-oe-g++' >> $script

    # Use relocable sysroot
    sed -i -e 's:${SDKPATHNATIVE}:$OECORE_NATIVE_SYSROOT:g' $script
}

do_generate_qt_environment_file[umask] = "022"
addtask generate_qt_environment_file after do_install before do_package

SRCREV = "4e158f6bfa7d0747d8da70b3b15a44b52e35bb8a"
