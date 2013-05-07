require qt5module.inc

DEPENDS += "qtdeclarative ${ICU}"

LICENSE = "BSD & LGPL-2.1 & GFDL-1.3"
# this is for latest SRCREV:
LIC_FILES_CHKSUM = "file://${S}/doc/src/qtjsondb.qdoc;endline=26;md5=757f4eda130ceff3ca0985dde715af07 \
                    file://${S}/examples/jsondb-client/example.qml;endline=39;md5=5213e8171c07d54db7107f29ac2f7b5e \
                    file://${S}/src/client/qjsondbglobal.h;endline=40;md5=34f5660524f0d70941d0416511ef49a0 \
"

INC_PR = "r0"
