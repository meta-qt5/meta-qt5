# Ugly hack to work around undefined OE_QMAKE_PATH_EXTERNAL_HOST_BINS variable
# and possibly missing qmake binary (qtbase-native can be removed from sysroot
# e.g. in order to upgrade it, even when there is target qtbase)

do_configure_prepend() {
    sed -i 's/^find_package(Qt5Core QUIET)$/#find_package(Qt5Core QUIET)/g' ${S}/Tests/RunCMake/CMakeLists.txt
}
