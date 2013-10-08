require qt5.inc

LICENSE = "BSD & LGPLv2+"
LIC_FILES_CHKSUM = "file://examples/webkitwidgets/scroller/wheel/main.cpp;endline=112;md5=6f92d041824c63426993c8ce3ae0eb77 \
                    file://examples/webkitwidgets/imageanalyzer/imageanalyzer.cpp;endline=223;md5=13ffb472fefe4bdf6464954a22251f35"

SRC_URI += " \
    file://0001-qtwebkit-examples-enable-building-examples-by-defaul.patch \
"

DEPENDS += "qtwebkit"
