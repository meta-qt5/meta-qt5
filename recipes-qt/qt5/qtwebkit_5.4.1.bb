require qt5-${PV}.inc
require ${PN}.inc

SRC_URI += "\
    file://0003-Fix-building-with-glib-2.43.patch \
    file://0004-gcc5-qtbug-44829.patch \
"

SRC_URI[md5sum] = "186627b1ea5b614811fbd0cfa9b4d073"
SRC_URI[sha256sum] = "6607211ef8a913dc778617bf4ba0970e34cc71e1da3abb477eabe0035e7119bf"
