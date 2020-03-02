SUMMARY = "Python Qt Chart Bindings"
AUTHOR = "Adrian.Fiergolski@fastree3d.com"
SECTION = "devel/python"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "\
    file://LICENSE;md5=d32239bcb673463ab874e80d47fae504 \
"
SRC_URI = "\
    https://www.riverbankcomputing.com/static/Downloads/PyQtChart/${PV}/PyQtChart-${PV}.tar.gz \
"
SRC_URI[md5sum] = "d5d37bff46b690d6318e5e5f25dd5213"
SRC_URI[sha256sum] = "49960a1483527857b38c1527f9b6328d30bdcc84521f579c0a561a892f54130e"

S = "${WORKDIR}/PyQtChart-${PV}"

inherit qmake5
inherit python3native python3-dir

DEPENDS = "qtcharts"
DEPENDS += "sip3 sip3-native python3 python3-pyqt5"

export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

PARALLEL_MAKEINST = ""

DISABLED_FEATURES = "PyQt_Desktop_OpenGL PyQt_Accessibility PyQt_SessionManager"

do_configure_prepend() {
    cd ${S}
    echo "[PyQt 5]" > pyqt.cfg
    echo "py_platform = linux" >> pyqt.cfg
    echo "py_inc_dir = %(sysroot)/$includedir/python%(py_major).%(py_minor)${PYTHON_ABI}" >> pyqt.cfg
    echo "py_pylib_dir = %(sysroot)/${libdir}/python%(py_major).%(py_minor)" >> pyqt.cfg
    echo "pyqt_module_dir = ${D}/${libdir}/python%(py_major).%(py_minor)/site-packages" >> pyqt.cfg
    echo "py_sip_dir = ${STAGING_EXECPREFIXDIR}/share/sip" >> pyqt.cfg
    echo "sip_module = PyQt5.sip" >> pyqt.cfg
    echo "pyqt_disabled_features = ${DISABLED_FEATURES}" >> pyqt.cfg
    echo yes | ${PYTHON} configure.py --verbose --qmake  ${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/qmake --configuration pyqt.cfg --sysroot ${STAGING_DIR_HOST}
}

do_configure_append() {
    #Fix installation paths
    sed -i -e s:'$(INSTALL_ROOT)'${STAGING_EXECPREFIXDIR}:'$(INSTALL_ROOT)'${D}${exec_prefix}:g ${S}/Makefile
    sed -i -e s:'$(INSTALL_ROOT)'${STAGING_EXECPREFIXDIR}:'$(INSTALL_ROOT)'${D}${exec_prefix}:g ${S}/QtChart/Makefile
    #Skip installed.txt creation
    sed -i -e s:" install_distinfo ":" ": ${S}/Makefile
}

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    cd ${S}
    oe_runmake MAKEFLAGS='-j 1' install
}


FILES_${PN} += "${libdir}/${PYTHON_DIR}/site-packages ${datadir}/"

RDEPENDS_${PN} = "qtbase qtdeclarative qtquickcontrols2 qtquickcontrols2-mkspecs qtcharts"
RDEPENDS_${PN} += "python3-core python3-sip3 python3-pyqt5"
