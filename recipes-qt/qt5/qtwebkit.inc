require qt5.inc

LICENSE = "BSD & LGPLv2+"
LIC_FILES_CHKSUM = "file://Source/WebCore/rendering/RenderApplet.h;endline=22;md5=fb9694013ad71b78f8913af7a5959680 \
                    file://Source/WebKit/gtk/webkit/webkit.h;endline=21;md5=b4fbe9f4a944f1d071dba1d2c76b3351 \
                    file://Source/JavaScriptCore/parser/Parser.h;endline=23;md5=b57c8a2952a8d0e655988fa0ecb2bf7f"

DEPENDS += "qtdeclarative qtlocation qtsensors ${ICU} ruby-native sqlite3"

INC_PR = "r0"

do_configure_append() {
    # Fix rpaths for QtWebProcess
    find -name "Makefile*" | xargs sed -i "s|-Wl,-rpath,${B}/lib||g"
}
