SUMMARY = "Qt Creator is a new cross-platform Qt IDE"

# Note:
# The toolchain auto detection does not work completely yet. To compile/debug
# open menu 'Tools/Options and select 'Build & Run'. In tab 'Kits' select 'Desktop'
# 'Compiler/Manage...' and add local gcc'. At 'Debugger' select
# 'System GDB at /usr/bin/gdb.

HOMEPAGE = "https://qt-project.org/"
LICENSE = "LGPLv2.1 | GPLv3"
LIC_FILES_CHKSUM = " \
    file://LGPL_EXCEPTION.TXT;md5=eb6c371255e1262c55ae9b652a90b528 \
    file://LICENSE.LGPLv21;md5=243b725d71bb5df4a1e5920b344b86ad \
    file://LICENSE.LGPLv3;md5=c1939be5579666be947371bc8120425f \
"

inherit qmake5

DEPENDS = "qtbase qtscript qtwebkit qtxmlpatterns qtx11extras qtdeclarative qttools qttools-native qtsvg qtquick1"

SRC_URI = " \
    http://download.qt.io/official_releases/qtcreator/3.3/${PV}/qt-creator-opensource-src-${PV}.tar.gz \
    file://0001-Fix-Allow-qt-creator-to-build-on-arm-aarch32-and-aar.patch \
    file://qtcreator.desktop.in \
"
SRC_URI[md5sum] = "5e33988908282c779f2e6e4dca2bba3e"
SRC_URI[sha256sum] = "27a5c8815fab95f959134047f8315686de4de6f99b0bedfd46b5dedae390525a"

S = "${WORKDIR}/qt-creator-opensource-src-${PV}"

EXTRA_QMAKEVARS_PRE += "IDE_LIBRARY_BASENAME=${baselib}/${QT_DIR_NAME}"

do_configure_prepend() {
    # causes gcc infinite loop with 4.9.x for arm targets similar to
    # https://gcc.gnu.org/bugzilla/show_bug.cgi?id=61033
    export DO_NOT_BUILD_QMLDESIGNER=1
}

do_configure_append() {
    # Find native tools
    sed -i 's:${STAGING_BINDIR}.*/lrelease:${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/lrelease:g' ${B}/share/qtcreator/translations/Makefile
    sed -i 's:${STAGING_BINDIR}.*/qdoc:${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/qdoc:g' ${B}/Makefile

    # see qtbase-native.inc
    # sed -i 's:QT_INSTALL_DOCS=${docdir}:QT_INSTALL_DOCS=${STAGING_DATADIR_NATIVE}/${QT_DIR_NAME}/doc:g' ${B}/Makefile
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
    sed -i 's:@QT5_QMAKE@:${bindir}/${QT_DIR_NAME}:g' ${D}${datadir}/applications/qtcreator.desktop
}

FILES_${PN} += " \
    ${datadir}/qtcreator \
    ${datadir}/icons \
    ${libdir}/${QT_DIR_NAME}/qtcreator \
"
FILES_${PN}-dbg += " \
    ${libdir}/${QT_DIR_NAME}/qtcreator/.debug \
    ${libdir}/${QT_DIR_NAME}/qtcreator/plugins/.debug \
    ${libdir}/${QT_DIR_NAME}/qtcreator/plugins/qbs/plugins/.debug \
"

FILES_${PN}-dev += " \
    ${libdir}/${QT_DIR_NAME}/qtcreator/*${SOLIBSDEV} \
"

RDEPENDS_${PN} += "perl"
RCONFLICTS_${PN} = "qt-creator"

# To give best user experience out of the box..
RRECOMMENDS_${PN} += " \
    packagegroup-qt5-toolchain-target \
    binutils \
    ccache \
    gcc-symlinks \
    gdb \
"
