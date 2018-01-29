SUMMARY = "Qt Creator is a new cross-platform Qt IDE"

# Note:
# The toolchain auto detection does not work completely yet. To compile/debug
# open menu 'Tools/Options and select 'Build & Run'. In tab 'Kits' select 'Desktop'
# 'Compiler/Manage...' and add local gcc'. At 'Debugger' select
# 'System GDB at /usr/bin/gdb.

HOMEPAGE = "https://qt-project.org/"
LICENSE = "LGPLv2.1 | GPLv3"
LIC_FILES_CHKSUM = " \
    file://LGPL_EXCEPTION.TXT;md5=f4748b0d1a72c5c8fb5dab2dd1f7fa46 \
    file://LICENSE.LGPLv21;md5=825920de5f6db2eeb1bebe625476346d \
    file://LICENSE.LGPLv3;md5=0786418af032b9e608909874f334a2d1 \
"

inherit qmake5

DEPENDS = "qtbase qtscript qtwebkit qtxmlpatterns qtx11extras qtdeclarative qttools qttools-native qtsvg qtquick1"
DEPENDS_append_libc-musl = " libexecinfo"

# Patches from https://github.com/meta-qt5/qtcreator/commits/b5.3.1
# 5.3.1.meta-qt5.1
SRC_URI = " \
    http://download.qt.io/official_releases/qtcreator/3.5/${PV}/qt-creator-opensource-src-${PV}.tar.gz \
    file://0001-Fix-Allow-qt-creator-to-build-on-arm-aarch32-and-aar.patch \
    file://0002-Fix-compilation-with-QT_NO_ACCESSIBILITY.patch \
    file://0003-Qmlpuppet-add-missing-includes.patch \
    file://qtcreator.desktop.in \
"

SRC_URI_append_libc-musl = " file://0004-Link-with-libexecinfo-on-musl.patch"


SRC_URI[md5sum] = "77aef7df837eba07c7ce6037ee504c05"
SRC_URI[sha256sum] = "5925ac818a08be919094e0f28fb4c5d8896765e0975d54d353e4c50f13d63e65"

S = "${WORKDIR}/qt-creator-opensource-src-${PV}"

EXTRA_QMAKEVARS_PRE += "IDE_LIBRARY_BASENAME=${baselib}${QT_DIR_NAME}"

LDFLAGS_append_libc-musl = " -lexecinfo "
do_configure_append() {
    # Find native tools
    sed -i 's:${STAGING_BINDIR}.*/lrelease:${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/lrelease:g' ${B}/share/qtcreator/translations/Makefile
    sed -i 's:${STAGING_BINDIR}.*/qdoc:${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/qdoc:g' ${B}/Makefile

    # see qtbase-native.inc
    # sed -i 's:QT_INSTALL_DOCS=${docdir}:QT_INSTALL_DOCS=${STAGING_DATADIR_NATIVE}${QT_DIR_NAME}/doc:g' ${B}/Makefile
}

do_compile_append() {
    # build docs
    #oe_runmake docs_online
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}${prefix}
    oe_runmake install_inst_qch_docs INSTALL_ROOT=${D}${prefix}
    # install desktop and ensure that qt-creator finds qmake
    install -d ${D}${datadir}/applications
    install -m 0644 ${WORKDIR}/qtcreator.desktop.in ${D}${datadir}/applications/qtcreator.desktop
    sed -i 's:@QT5_QMAKE@:${OE_QMAKE_PATH_QT_BINS}:g' ${D}${datadir}/applications/qtcreator.desktop
}

FILES_${PN} += " \
    ${datadir}/qtcreator \
    ${datadir}/icons \
    ${libdir}${QT_DIR_NAME}/qtcreator \
"
FILES_${PN}-dbg += " \
    ${libdir}${QT_DIR_NAME}/qtcreator/.debug \
    ${libdir}${QT_DIR_NAME}/qtcreator/plugins/.debug \
    ${libdir}${QT_DIR_NAME}/qtcreator/plugins/qmldesigner/.debug \
    ${libdir}${QT_DIR_NAME}/qtcreator/plugins/qbs/plugins/.debug \
"

FILES_${PN}-dev += " \
    ${libdir}${QT_DIR_NAME}/qtcreator/*${SOLIBSDEV} \
"

RDEPENDS_${PN} += "perl"
RCONFLICTS_${PN} = "qt-creator"

# To give best user experience out of the box..
RRECOMMENDS_${PN} += " \
    packagegroup-qt5-toolchain-target \
    binutils \
    ccache \
    make \
    gcc-symlinks g++-symlinks cpp-symlinks \
    gdb \
"
