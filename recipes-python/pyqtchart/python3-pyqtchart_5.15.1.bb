SUMMARY = "Python Qt Chart Bindings"
AUTHOR = "Adrian.Fiergolski@fastree3d.com"
HOMEPAGE = "https://www.riverbankcomputing.com/software/pyqtchart"
SECTION = "devel/python"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "\
    file://LICENSE;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit pypi

PYPI_PACKAGE = "PyQtChart"

SRC_URI[md5sum] = "8a36bc796b0d9a2301e613c382336b0e"
SRC_URI[sha256sum] = "8d976b3dbfb233fb0123129323c68adb9d3693c945bba1e227e004208f0747bc"

S = "${WORKDIR}/PyQtChart-${PV}"

inherit qmake5
inherit python3native python3-dir

DEPENDS = "qtcharts"
DEPENDS += "sip sip-native python3 python3-pyqt5"

export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

PARALLEL_MAKEINST = ""

DISABLED_FEATURES = "PyQt_Desktop_OpenGL PyQt_Accessibility PyQt_SessionManager"

do_configure:prepend() {
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

do_configure:append() {
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


FILES:${PN} += "${libdir}/${PYTHON_DIR}/site-packages ${datadir}/"

RDEPENDS:${PN} = "qtbase qtdeclarative qtquickcontrols2 qtquickcontrols2-mkspecs qtcharts"
RDEPENDS:${PN} += "python3-core sip python3-pyqt5"
