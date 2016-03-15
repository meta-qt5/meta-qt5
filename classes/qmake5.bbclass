#
# QMake variables for Qt
#
inherit qmake5_base

QT5TOOLSDEPENDS ?= "qtbase-native"
DEPENDS_prepend = "${QT5TOOLSDEPENDS} "

do_configure() {
    qmake5_base_do_configure
}

do_install() {
    qmake5_base_do_install
}

do_install_class-native() {
    qmake5_base_native_do_install
}
