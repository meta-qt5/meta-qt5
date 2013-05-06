require qt5module.inc

DEPENDS += "qtdeclarative qtjsondb udev bluez4 gconf" 

LICENSE = "BSD & LGPL-2.1 & GFDL-1.3"
# LICENSE files are missing in 5.0.0
LIC_FILES_CHKSUM = "file://${S}/src/imports/systeminfo/qsysteminfo.cpp;endline=40;md5=f28e1baba502dda6849d69d5c24e7356 \
                    file://${S}/examples/systeminfo/qml-storageinfo/content/ProgressBar.qml;endline=39;md5=5213e8171c07d54db7107f29ac2f7b5e \
                    file://${S}/doc/src/systeminfo/systeminfo.qdoc;endline=26;md5=757f4eda130ceff3ca0985dde715af07 \
"

INC_PR = "r0"
