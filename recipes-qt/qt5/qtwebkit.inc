require qt5.inc

LICENSE = "BSD & LGPLv2+"
LIC_FILES_CHKSUM = "file://Source/WebCore/rendering/RenderApplet.h;endline=22;md5=fb9694013ad71b78f8913af7a5959680 \
                    file://Source/WebKit/gtk/webkit/webkit.h;endline=21;md5=b4fbe9f4a944f1d071dba1d2c76b3351 \
                    file://Source/JavaScriptCore/parser/Parser.h;endline=23;md5=b57c8a2952a8d0e655988fa0ecb2bf7f"

DEPENDS += "qtdeclarative qtlocation qtsensors ${ICU} ruby-native sqlite3 glib-2.0 gstreamer gst-plugins-base libxslt"

# qtwebkit gets terribly big when linking with all debug info, disable by default
QTWEBKIT_DEBUG ?= "QMAKE_CFLAGS+=-g0 QMAKE_CXXFLAGS+=-g0"
EXTRA_QMAKEVARS_PRE += "${QTWEBKIT_DEBUG}"

do_configure_append() {
    # Fix rpaths for QtWebProcess
    find -name "Makefile*" | xargs sed -i "s|-Wl,-rpath,${B}/lib||g"
}
