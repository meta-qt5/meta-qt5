DESCRIPTION = "Native version of Qt/[X11|Mac|Embedded]"
DEPENDS = "zlib-native dbus-native"
SECTION = "libs"
HOMEPAGE = "http://qt-project.org"

LICENSE = "GFDL-1.3 & BSD & ( GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial ) & ( GPL-2.0+ | LGPL-3.0 | The-Qt-Company-Commercial )"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.QT-LICENSE-AGREEMENT-4.0;md5=948f8877345cd66106f11031977a4625 \
"

require qt5-native.inc
require qt5-git.inc

# common for qtbase-native, qtbase-nativesdk and qtbase
# Patches from https://github.com/meta-qt5/qtbase/commits/b5.12-shared
# 5.12.meta-qt5-shared.7
SRC_URI += "\
    file://0001-Add-linux-oe-g-platform.patch \
    file://0002-cmake-Use-OE_QMAKE_PATH_EXTERNAL_HOST_BINS.patch \
    file://0003-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
    file://0004-configure-bump-path-length-from-256-to-512-character.patch \
    file://0005-Disable-all-unknown-features-instead-of-erroring-out.patch \
    file://0006-Pretend-Qt5-wasn-t-found-if-OE_QMAKE_PATH_EXTERNAL_H.patch \
    file://0007-Delete-qlonglong-and-qulonglong.patch \
    file://0008-Replace-pthread_yield-with-sched_yield.patch \
    file://0009-Add-OE-specific-specs-for-clang-compiler.patch \
    file://0010-linux-clang-Invert-conditional-for-defining-QT_SOCKL.patch \
    file://0011-tst_qlocale-Enable-QT_USE_FENV-only-on-glibc.patch \
    file://0012-mkspecs-common-gcc-base.conf-Use-I-instead-of-isyste.patch \
    file://0013-Disable-ltcg-for-host_build.patch \
    file://0014-Qt5GuiConfigExtras.cmake.in-cope-with-variable-path-.patch \
    file://0015-corelib-Include-sys-types.h-for-uint32_t.patch \
    file://0016-Define-QMAKE_CXX.COMPILER_MACROS-for-clang-on-linux.patch \
"

# common for qtbase-native and nativesdk-qtbase
# Patches from https://github.com/meta-qt5/qtbase/commits/b5.12-native
# 5.12.meta-qt5-native.7
SRC_URI += " \
    file://0017-Always-build-uic-and-qvkgen.patch \
    file://0018-Avoid-renameeat2-for-native-sdk-builds.patch \
"

# only for qtbase-native
SRC_URI += " \
    file://0019-Bootstrap-without-linkat-feature.patch \
"

CLEANBROKEN = "1"

XPLATFORM_toolchain-clang = "linux-oe-clang"
XPLATFORM ?= "linux-oe-g++"

QT_CONFIG_FLAGS = " \
    -sysroot ${STAGING_DIR_NATIVE} \
    -L${STAGING_LIBDIR_NATIVE} \
    -no-gcc-sysroot \
    -system-zlib \
    -qt-pcre \
    -no-libjpeg \
    -no-libpng \
    -no-gif \
    -no-accessibility \
    -no-cups \
    -no-gui \
    -no-sql-mysql \
    -no-sql-sqlite \
    -no-sql-psql \
    -no-opengl \
    -no-openssl \
    -no-xcb \
    -no-icu \
    -verbose \
    -release \
    -prefix ${OE_QMAKE_PATH_PREFIX} \
    -hostprefix ${OE_QMAKE_PATH_PREFIX} \
    -bindir ${OE_QMAKE_PATH_BINS} \
    -hostbindir ${OE_QMAKE_PATH_BINS} \
    -libdir ${OE_QMAKE_PATH_LIBS} \
    -hostlibdir ${OE_QMAKE_PATH_LIBS} \
    -headerdir ${OE_QMAKE_PATH_HEADERS} \
    -archdatadir ${OE_QMAKE_PATH_ARCHDATA} \
    -datadir ${OE_QMAKE_PATH_DATA} \
    -hostdatadir ${QMAKE_MKSPEC_PATH_NATIVE} \
    -docdir ${OE_QMAKE_PATH_DOCS} \
    -sysconfdir ${OE_QMAKE_PATH_SETTINGS} \
    -no-glib \
    -no-iconv \
    -silent \
    -nomake examples \
    -nomake tests \
    -no-rpath \
    -no-feature-linkat \
    -platform ${XPLATFORM} \
    ${PACKAGECONFIG_CONFARGS} \
"

# for qtbase configuration we need default settings
# since we cannot set empty set filename to a not existent file
deltask generate_qt_config_file

do_configure_prepend() {
    # Regenerate header files when they are included in source tarball
    # Otherwise cmake files don't set PRIVATE_HEADERS correctly
    rm -rf ${S}/include
    mkdir -p ${S}/.git || true

    # Avoid qmake error "Cannot read [...]/usr/lib/qt5/mkspecs/oe-device-extra.pri: No such file or directory"
    touch ${S}/mkspecs/oe-device-extra.pri

    MAKEFLAGS="${PARALLEL_MAKE}" ${S}/configure -${QT_EDITION} -confirm-license ${QT_CONFIG_FLAGS} || die "Configuring qt failed. QT_CONFIG_FLAGS was ${QT_CONFIG_FLAGS}"
}

do_install() {
    # Fix install paths for all
    find . -name "Makefile*" | xargs sed -i "s,(INSTALL_ROOT)${STAGING_DIR_NATIVE}${STAGING_DIR_NATIVE},(INSTALL_ROOT)${STAGING_DIR_NATIVE},g"

    oe_runmake install INSTALL_ROOT=${D}

    if [ -d ${D}${STAGING_DIR_NATIVE}${STAGING_DIR_NATIVE} ] ; then
        echo "Some files are installed in wrong directory ${D}${STAGING_DIR_NATIVE}"
        cp -ra ${D}${STAGING_DIR_NATIVE}${STAGING_DIR_NATIVE}/* ${D}${STAGING_DIR_NATIVE}
        rm -rf ${D}${STAGING_DIR_NATIVE}${STAGING_DIR_NATIVE}
        # remove empty dirs
        TMP=`dirname ${D}/${STAGING_DIR_NATIVE}${STAGING_DIR_NATIVE}`
        while test ${TMP} != ${D}${STAGING_DIR_NATIVE}; do
            rmdir ${TMP}
            TMP=`dirname ${TMP}`;
        done
    fi

    install -m 755 ${B}/bin/qfloat16-tables ${D}${OE_QMAKE_PATH_BINS}

    # since 5.9.2 something sets a very strange path to mkspec ("${_qt5Core_install_prefix}/../../../../../../../../../../usr/lib/qt5//mkspecs/linux-oe-g++")
    # override this until somebody finds a better way
    echo 'set(_qt5_corelib_extra_includes "${_qt5Core_install_prefix}/lib${QT_DIR_NAME}/mkspecs/linux-oe-g++")' > ${D}${libdir}/cmake/Qt5Core/Qt5CoreConfigExtrasMkspecDir.cmake
}

SRCREV = "b527725766df850fcad6b9078fea5e8da8085560"
