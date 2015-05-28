require qt5.inc
require qt5-git.inc

# text of LGPL_EXCEPTION.txt and LICENSE.FDL is slightly different than what
# other qt* components use :/
LICENSE = "GFDL-1.3 & BSD & (LGPL-2.1 & Digia-Qt-LGPL-Exception-1.1 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=cff17b12416c896e10ae2c17a64252e7 \
    file://LICENSE.GPLv2;md5=e782f55badfa137e5e59c330f12cc8ed \
    file://LICENSE.LGPLv3;md5=c1939be5579666be947371bc8120425f \
    file://LGPL_EXCEPTION.txt;md5=eb6c371255e1262c55ae9b652a90b528 \
    file://LICENSE.FDL;md5=3801d7932fdc07fd9efe89f9854a6caa \
"

SRC_URI += " \
    file://0001-Unix-Clear-serial_struct-instances.patch \
"

DEPENDS += "qtbase"

SRCREV = "b84fe7eb3d6d977a347bfbb82da724409b2eda69"
