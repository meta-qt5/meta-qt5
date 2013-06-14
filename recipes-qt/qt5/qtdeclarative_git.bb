require qt5-git.inc
require ${PN}.inc

PR = "${INC_PR}.0"

SRC_URI += "file://0001-Fix-wrong-calculation-of-viewPort-for-transitions.patch \
            file://0002-Fix-null-pointer-access-in-QQuickVisualDataModelPriv.patch \
"

SRCREV = "1d594c4e10caa9258f00bb7bcf61c307d027633b"
