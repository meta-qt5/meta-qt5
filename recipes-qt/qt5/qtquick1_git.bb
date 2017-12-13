require qt5.inc
require qt5-git.inc

# There are no LGPLv3-only licensed files in this component.
# There are no GPLv2 licensed files in this component.
# Note that some files are LGPL-2.1 only without The-Qt-Company-Qt-LGPL-Exception-1.1.
LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & The-Qt-Company-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=4bfd28363f541b10d9f024181b8df516 \
    file://LICENSE.LGPLv3;md5=e0459b45c5c4840b353141a8bbed91f0 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LGPL_EXCEPTION.txt;md5=9625233da42f9e0ce9d63651a9d97654 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
    file://LICENSE.GPLv2;md5=c96076271561b0e3785dad260634eaa8 \
"

DEPENDS += "qtscript qtsvg qtxmlpatterns"
# qttools

PACKAGECONFIG ??= "webkit"
PACKAGECONFIG[webkit] = ",,qtwebkit"

EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('PACKAGECONFIG', 'webkit', '', 'CONFIG+=noqtwebkit', d)}"

do_configure_prepend() {
    sed -i 's#^qtHaveModule(webkitwidgets):#qtHaveModule(webkitwidgets):!contains(CONFIG, noqtwebkit):#g' ${S}/src/imports/imports.pro
}

SRCREV = "b934c54583adc4f9021de2753f99836f60a96688"
