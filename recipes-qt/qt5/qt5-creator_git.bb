SUMMARY = "Qt Creator is a new cross-platform Qt IDE"

# Note:
# The toolchain auto detection does not work completely yet. To compile/debug
# open menu 'Tools/Options and select 'Build & Run'. In tab 'Kits' select 'Desktop'
# 'Compiler/Manage...' and add local gcc'. At 'Debugger' select
# 'System GDB at /usr/bin/gdb.

HOMEPAGE = "https://qt-project.org/"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
"

inherit qmake5 pkgconfig mime-xdg

DEPENDS += "qtbase qtscript qtxmlpatterns qtx11extras qtdeclarative qttools qttools-native qtsvg chrpath-replacement-native zlib"
DEPENDS:append:libc-musl = " libexecinfo"

SRCREV = "39ac7b1bf6add473026687003311b702742b6b21"
PV = "4.15.2+git${SRCPV}"
# Patches from https://github.com/meta-qt5/qtcreator/commits/b4.9.2
# 4.9.2.meta-qt5.1
SRC_URI = " \
    git://code.qt.io/qt-creator/qt-creator.git;branch=4.15 \
    file://0001-app-Use-malloc_trim-only-on-glibc.patch \
    file://0002-clangformat-Fix-build-with-LLVM-13.patch \
    file://0003-clangformat-Fix-build-with-LLVM-13.patch \
"
SRC_URI:append:libc-musl = " file://0001-Link-with-libexecinfo-on-musl.patch"

S = "${WORKDIR}/git"

EXTRA_QMAKEVARS_PRE += " \
    IDE_LIBRARY_BASENAME=${baselib}${QT_DIR_NAME} \
    CONFIG+=disable_external_rpath \
"

EXTRANATIVEPATH += "chrpath-native"

PACKAGECONFIG ??= ""
PACKAGECONFIG:append:toolchain-clang = " clang"

# Important note: In case clang was added to qttools' PACKAGECONFIG, it has to
# be added here too - otherwise build fails trying to link native clang libraries
PACKAGECONFIG[clang] = ",,clang"

COMPATIBLE_HOST:toolchain-clang:riscv32 = "null"
COMPATIBLE_HOST:toolchain-clang:riscv64 = "null"

do_configure:append() {
    # Find native tools
    sed -i 's:${STAGING_BINDIR}.*/qdoc:${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/qdoc:g' ${B}/Makefile
    if [ -e ${B}/share/qtcreator/translations/Makefile ]; then
        sed -i 's:${STAGING_BINDIR}/lrelease:${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/lrelease:g' ${B}/share/qtcreator/translations/Makefile
        sed -i 's:${STAGING_BINDIR}/lupdate:${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/lupdate:g' ${B}/share/qtcreator/translations/Makefile
        sed -i 's:${STAGING_BINDIR}/xmlpatterns:${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/xmlpatterns:g' ${B}/share/qtcreator/translations/Makefile
        sed -i 's:${STAGING_BINDIR}/lconvert:${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/lconvert:g' ${B}/share/qtcreator/translations/Makefile
    fi
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}${prefix}
    if [ "${@bb.utils.contains("PACKAGECONFIG", "clang", "1", "0", d)}" = "1" ]; then
        # Remove RPATHs embedded in bins
        chrpath --delete ${D}${libdir}/qtcreator/plugins/libClang*
        chrpath --delete ${D}${libexecdir}/qtcreator/clang*
    fi
}

FILES:${PN} += " \
    ${datadir}/qtcreator \
    ${datadir}/metainfo \
    ${datadir}/icons \
    ${libdir}${QT_DIR_NAME}/qtcreator \
"

FILES:${PN}-dev += " \
    ${libdir}${QT_DIR_NAME}/qtcreator/*${SOLIBSDEV} \
"

RDEPENDS:${PN} += "perl python3"
RCONFLICTS:${PN} = "qt-creator"

# To give best user experience out of the box..
RRECOMMENDS:${PN} += " \
    packagegroup-qt5-toolchain-target \
    binutils \
    ccache \
    make \
    gcc-symlinks g++-symlinks cpp-symlinks \
    gdb \
    cmake \
    qtwebengine-dev \
    qtwebengine-mkspecs \
    qtwebengine-plugins \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'qtwebengine-qmlplugins', '', d)} \
"

# ERROR: qt5-creator-4.5.1-r0 do_package_qa: QA Issue: No GNU_HASH in the elf binary: '/OE/build/oe-core/tmp-glibc/work/core2-64-oe-linux/qt5-creator/4.5.1-r0/packages-split/qt5-creator/usr/lib/qt5/qtcreator/libqbscore.so.1.10.1'
INSANE_SKIP:${PN} += "ldflags"

inherit features_check
REQUIRED_DISTRO_FEATURES ?= "x11 opengl"

python() {
    if 'meta-python2' not in d.getVar('BBFILE_COLLECTIONS').split():
        raise bb.parse.SkipRecipe('Requires meta-python2 to be present.')
}
