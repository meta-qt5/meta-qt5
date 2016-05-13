require qt5.inc
require qt5-git.inc

# There are no LGPLv3-only licensed files in this component.
# There are no GPLv2 licensed files in this component.
# Note that some files are LGPL-2.1 only without The-Qt-Company-Qt-LGPL-Exception-1.1.
LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
    file://LICENSE.LGPLv3;md5=c4fe8c6de4eef597feec6e90ed62e962 \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.GPLv2;md5=05832301944453ec79e40ba3c3cfceec \
"

DEPENDS += "qtscript qtsvg qtxmlpatterns"
# qttools

SRC_URI += "file://0001-qdeclarativetextinput-update-to-match-QWidgetLineCon.patch"

PACKAGECONFIG ??= "webkit"
PACKAGECONFIG[webkit] = "CONFIG+=enable-webkit,CONFIG-=enable-webkit,qtwebkit"

do_configure_prepend() {
    sed -i 's#^qtHaveModule(webkitwidgets):#enable-webkit:qtHaveModule(webkitwidgets):#g' ${S}/src/imports/imports.pro
}

SRCREV = "26229cfa0b729313893af5674d604e8692dbb946"
