SUMMARY = "Python Qt5 Bindings"
AUTHOR = "Phil Thomson @ riverbank.co.uk"
HOMEPAGE = "http://riverbankcomputing.co.uk"
SECTION = "devel/python"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "\
    file://LICENSE;md5=d32239bcb673463ab874e80d47fae504 \
"
SRC_URI = "\
    ${SOURCEFORGE_MIRROR}/pyqt/PyQt5_gpl-${PV}.tar.gz \
"
SRC_URI[md5sum] = "aac3428e90eca5df68c5764282fd9b98"
SRC_URI[sha256sum] = "c9b57d15601d436faf35dacf8e0acefa220194829a653e771e80b189b3261073"

S = "${WORKDIR}/PyQt5_gpl-${PV}"

inherit qmake5
DEPENDS = "qtbase"

export BUILD_SYS
export HOST_SYS
export STAGING_INCDIR
export STAGING_LIBDIR

PARALLEL_MAKEINST = ""

DISABLED_FEATURES = "PyQt_Desktop_OpenGL PyQt_Accessibility PyQt_SessionManager"

DISABLED_FEATURES_append_arm = " PyQt_qreal_double"

PYQT_MODULES = "QtCore QtGui QtNetwork QtWidgets"
PYQT_MODULES_aarch64 = "QtCore QtGui QtNetwork QtWidgets"

do_configure_prepend() {
    cd ${S}
    echo "py_platform = linux" > pyqt.cfg
    echo "py_inc_dir = %(sysroot)/$includedir/python${PYTHON_BASEVERSION}${PYTHON_ABI}" >> pyqt.cfg
    echo "py_pylib_dir = %(sysroot)/${libdir}/python${PYTHON_BASEVERSION}${PYTHON_ABI}" >> pyqt.cfg
    echo "py_pylib_lib = python${PYTHON_BASEVERSION}${PYTHON_ABI}" >> pyqt.cfg
    echo "pyqt_module_dir = ${D}/${libdir}/python${PYTHON_BASEVERSION}${PYTHON_ABI}/site-packages" >> pyqt.cfg
    echo "pyqt_bin_dir = ${D}/${bindir}" >> pyqt.cfg
    echo "pyqt_sip_dir = ${D}/${datadir}/sip/PyQt5" >> pyqt.cfg
    echo "pyuic_interpreter = ${D}/${bindir}/python${PYTHON_BASEVERSION}${PYTHON_ABI}" >> pyqt.cfg
    echo "pyqt_disabled_features = ${DISABLED_FEATURES}" >> pyqt.cfg
    echo "qt_shared = True" >> pyqt.cfg
    QT_VERSION=`${OE_QMAKE_QMAKE} -query QT_VERSION`
    echo "[Qt $QT_VERSION]" >> pyqt.cfg
    echo "pyqt_modules = ${PYQT_MODULES}" >> pyqt.cfg
    echo yes | ${PYTHON} configure.py --verbose --qmake  ${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/qmake --configuration pyqt.cfg --sysroot ${STAGING_DIR_HOST}
}

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    cd ${S}
    oe_runmake MAKEFLAGS='-j 1' install
}

FILES_${PN} += "${libdir}/${PYTHON_DIR}${PYTHON_ABI}/site-packages ${datadir}/sip/PyQt5/"

RDEPENDS_${PN} = "qtbase"
