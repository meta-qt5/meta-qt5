require python3-pyqt5.inc

PYQT_MODULES += " \
    QtNetwork \
    QtQml \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'QtQuick QtWidgets QtQuickWidgets', '', d)} \
"

do_compile:prepend() {
    for pro_file in $(find ${B} -name "*.pro"); do
        echo "Patching $pro_file"
        sed -i "s|/[^ ]*/libpython${PYTHON_BASEVERSION}\.so|-lpython${PYTHON_BASEVERSION}|g" "$pro_file"
        sed -i "s|-L/[^ ]*/recipe-sysroot-native/[^ ]*||g" "$pro_file"
        sed -i "s|/[^ ]*/include/python${PYTHON_BASEVERSION}|${STAGING_INCDIR}/python${PYTHON_BASEVERSION}|g" "$pro_file"
    done
}

do_compile:append() {
    sed -i "s,${STAGING_DIR_TARGET},," ${B}/inventory.txt
}
