SUMMARY = "Python Qt5 Bindings"
AUTHOR = "Phil Thomson @ riverbank.co.uk"
HOMEPAGE = "https://www.riverbankcomputing.com/software/pyqt"
SECTION = "devel/python"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "\
    file://LICENSE;md5=d32239bcb673463ab874e80d47fae504 \
"

inherit pypi

PYPI_PACKAGE = "PyQt5"

S = "${WORKDIR}/PyQt5-${PV}"

inherit qmake5
inherit python3native python3-dir

DEPENDS = "qtbase qtdeclarative qtquickcontrols2"
DEPENDS += "sip3 sip3-native python3"

export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

# Work around mk_distinfo.py race condition
#  FileNotFoundError: [Errno 2] No such file or directory: '${WORKDIR}/image/${WORKDIR}/recipe-sysroot-native/usr/lib/python3.10/site-packages/PyQt5/QtCore.so'
#  https://gitweb.gentoo.org/repo/gentoo.git/commit/?id=97ae223251a26f941c1404dc36390244c636e142
PARALLEL_MAKEINST = "-j 1"

DISABLED_FEATURES = "PyQt_Desktop_OpenGL PyQt_Accessibility PyQt_SessionManager ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', '', 'PyQt_OpenGL', d)}"

# These are the base pyqt modules needed by native and target
PYQT_MODULES = "QtCore QtGui QtXml"

do_configure:prepend() {
    cd ${S}
    echo "py_platform = linux" > pyqt.cfg
    echo "py_inc_dir = %(sysroot)/$includedir/python%(py_major).%(py_minor)${PYTHON_ABI}" >> pyqt.cfg
    echo "py_pylib_dir = %(sysroot)/${libdir}/python%(py_major).%(py_minor)" >> pyqt.cfg
    echo "py_pylib_lib = python$%(py_major).%(py_minor)" >> pyqt.cfg
    echo "pyqt_module_dir = ${D}/${libdir}/python%(py_major).%(py_minor)/site-packages" >> pyqt.cfg
    echo "pyqt_bin_dir = ${D}/${bindir}" >> pyqt.cfg
    echo "pyqt_sip_dir = ${D}/${datadir}/sip/PyQt5" >> pyqt.cfg
    echo "pyuic_interpreter = ${D}/${bindir}/python%(py_major).%(py_minor)" >> pyqt.cfg
    echo "pyqt_disabled_features = ${DISABLED_FEATURES}" >> pyqt.cfg
    echo "qt_shared = True" >> pyqt.cfg
    QT_VERSION=`${OE_QMAKE_QMAKE} -query QT_VERSION`
    echo "[Qt $QT_VERSION]" >> pyqt.cfg
    echo "pyqt_modules = ${PYQT_MODULES}" >> pyqt.cfg
    echo yes | ${PYTHON} configure.py --verbose --qmake  ${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/qmake --configuration pyqt.cfg $(if [ -n "${STAGING_HOST_DIR}" ]; then echo --sysroot ${STAGING_HOST_DIR}; fi)
}

do_compile() {
    cd ${S}
    oe_runmake
}

do_install:class-target() {
    cd ${S}
    oe_runmake install
}

do_install:class-native() {
    cd ${S}
    oe_runmake install

    # Copied from qmake5_base_native_do_install()
    find "${D}" -ignore_readdir_race -name "*.la" -delete
    if ls ${D}${libdir}/pkgconfig/Qt5*.pc >/dev/null 2>/dev/null; then
        sed -i "s@-L${STAGING_LIBDIR}@-L\${libdir}@g" ${D}${libdir}/pkgconfig/Qt5*.pc
    fi
}

FILES:${PN} += "${libdir}/${PYTHON_DIR}/site-packages ${datadir}/sip/PyQt5/"

RDEPENDS:${PN} = "qtbase qtdeclarative qtquickcontrols2 qtquickcontrols2-mkspecs"
RDEPENDS:${PN} += "python3-core python3-sip3"
