require ${PN}.inc
require qt5-${PV}.inc

LICENSE = "BSD & LGPLv2+"
LIC_FILES_CHKSUM = "file://Source/WebCore/rendering/RenderApplet.h;endline=22;md5=fb9694013ad71b78f8913af7a5959680 \
                    file://Source/WebKit/gtk/webkit/webkit.h;endline=21;md5=b4fbe9f4a944f1d071dba1d2c76b3351 \
                    file://Source/JavaScriptCore/parser/Parser.h;endline=23;md5=b57c8a2952a8d0e655988fa0ecb2bf7f"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "11556c74161612fd37ce70277de3baa4"
SRC_URI[sha256sum] = "45981088d39ec044762861ee67acee8524ca20b13f1543a3045219aa9da21f9f"

