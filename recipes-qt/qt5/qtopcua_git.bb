require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & ( GPL-2.0+ | LGPL-3.0 ) | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.FDL;md5=f70ee9a6c44ae8917586fea34dff0ab5 \
    file://LICENSE.GPLv2;md5=05832301944453ec79e40ba3c3cfceec \
    file://LICENSE.LGPLv3;md5=c4fe8c6de4eef597feec6e90ed62e962 \
"

DEPENDS += "qtbase"

SRCREV = "326bf6a1058d878b7891ee6d7078ed381c462df1"
