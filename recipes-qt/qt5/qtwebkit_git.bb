require qt5-git.inc
require ${PN}.inc

# Apple header updated year in Parser.h
# - *  Copyright (C) 2003, 2006, 2007, 2008, 2009, 2010, 2011 Apple Inc. All rights reserved.
# + *  Copyright (C) 2003, 2006, 2007, 2008, 2009, 2010, 2011, 2013 Apple Inc. All rights reserved.
LIC_FILES_CHKSUM = "file://Source/WebCore/rendering/RenderApplet.h;endline=22;md5=fb9694013ad71b78f8913af7a5959680 \
                    file://Source/WebKit/gtk/webkit/webkit.h;endline=21;md5=b4fbe9f4a944f1d071dba1d2c76b3351 \
                    file://Source/JavaScriptCore/parser/Parser.h;endline=21;md5=bd69f72183a7af673863f057576e21ee"

SRCREV = "4c86230bfe563ef1e3ef493b870203761bc9f32d"
