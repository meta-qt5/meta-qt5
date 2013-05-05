require qt5module.inc

DEPENDS += "qtdeclarative icu ruby-native sqlite3"

INC_PR = "r0"

do_configure_append() {
    # Fix rpaths for QtWebProcess
    find -name "Makefile*" | xargs sed -i "s|-Wl,-rpath,${SEPB}/lib||g"
}
