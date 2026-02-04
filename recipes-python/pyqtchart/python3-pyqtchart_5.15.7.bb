SUMMARY = "Python Qt Chart Bindings"
AUTHOR = "Adrian.Fiergolski@fastree3d.com"
HOMEPAGE = "https://www.riverbankcomputing.com/software/pyqtchart"
SECTION = "devel/python"
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

inherit pypi python3-dir python3native qmake5 qmake5_paths

SRC_URI[sha256sum] = "bc9f1d26c725e820b0fff8db6e906e8b286128a14b3a98c59a0cd0c3d9924095"
PYPI_PACKAGE = "PyQtChart"

S = "${WORKDIR}/PyQtChart-${PV}"

DEPENDS = " \
    python3 \
    python3-ply-native \
    python3-pyqt-builder-native \
    python3-toml-native \
    python3-pyqt5 \
    qtcharts \
    sip \
    sip-native \
"

DISABLED_FEATURES = " \
    PyQt_Desktop_OpenGL \
    PyQt_Accessibility \
    PyQt_SessionManager \
"

do_configure:prepend() {
    extra_args=""

    cd ${S}

    if ! grep -q "sip-include-dirs" pyproject.toml; then
        sed -i '/\[tool.sip.project\]/a sip-include-dirs = \["${STAGING_DIR_HOST}/${PYTHON_SITEPACKAGES_DIR}/PyQt5/bindings"\]' pyproject.toml
    fi

    for i in ${DISABLED_FEATURES}; do
        extra_args="$extra_args --disabled-feature=$i"
    done

    sip-build \
        --verbose \
        --scripts-dir="${bindir}" \
        --build-dir="${B}" \
        --target-dir="${PYTHON_SITEPACKAGES_DIR}" \
        --qmake=${OE_QMAKE_QMAKE} \
        --no-make \
        $extra_args

    QMAKE_PROFILES=${B}/PyQtChart.pro
}

pyqt_fix_sources() {
    find ${PKGD}${TARGET_DBGSRC_DIR} -type f -exec sed -i "s,${B},${TARGET_DBGSRC_DIR},g" {} \;
    find ${PKGD}${TARGET_DBGSRC_DIR} -type f -exec sed -i "s,${S}/sip,${PYTHON_SITEPACKAGES_DIR}/PyQt5/bindings,g" {} \;
    find ${PKGD}${TARGET_DBGSRC_DIR} -type f -exec sed -i "s,${RECIPE_SYSROOT},,g" {} \;
}
PACKAGESPLITFUNCS =+ "pyqt_fix_sources"

FILES:${PN} += "${PYTHON_SITEPACKAGES_DIR}"

RDEPENDS:${PN} = " \
    python3-core \
    python3-pyqt5 \
    python3-pyqt5-sip \
    qtbase \
    qtcharts \
    qtdeclarative \
    qtquickcontrols2 \
    qtquickcontrols2-mkspecs \
    sip \
"
