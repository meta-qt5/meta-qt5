require qt5.inc

LICENSE = "BSD & LGPLv2+ | GPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.GPLv2;md5=e782f55badfa137e5e59c330f12cc8ed \
                    file://examples/webkitwidgets/scroller/wheel/main.cpp;endline=112;md5=6f92d041824c63426993c8ce3ae0eb77 \
                    file://examples/webkitwidgets/imageanalyzer/imageanalyzer.cpp;endline=223;md5=13ffb472fefe4bdf6464954a22251f35"

SRC_URI += " \
    file://0001-qtwebkit-examples-enable-building-examples-by-defaul.patch \
"

DEPENDS += "qtwebkit"
RDEPENDS_${PN}-examples += "qtwebkit-qmlplugins"
RDEPENDS_${PN}-examples += "${@base_contains('PACKAGECONFIG_OPENSSL', 'openssl', 'ca-certificates', '', d)}"
