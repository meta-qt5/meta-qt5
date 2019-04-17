require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & ( GPL-2.0+ | LGPL-3.0 ) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.FDL;md5=f70ee9a6c44ae8917586fea34dff0ab5 \
    file://LICENSE.GPLv2;md5=05832301944453ec79e40ba3c3cfceec \
    file://LICENSE.LGPLv3;md5=c4fe8c6de4eef597feec6e90ed62e962 \
"

# src/3rdparty/open62541.pri adds -Wno-format, causing following error
# because -Wformat-security cannot be used together with -Wno-format
# cc1: error: -Wformat-security ignored without -Wformat [-Werror=format-security]
SECURITY_STRINGFORMAT = ""

DEPENDS += "qtbase qtdeclarative"

# v5.12.3 tag wasn't created yet
SRCREV = "e42d517e350c18d6e6c5c3081e3005f10a5c20ab"
