require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0 & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
"

DEPENDS += "qtbase qtdeclarative"

QT_MODULE_BRANCH = "2.4"
QT_MODULE_BRANCH_EASTL = "master"
QT_GIT_PROJECT = "qt3dstudio"
PV = "2.4+git${SRCPV}"

SRC_URI += " \
    ${QT_GIT}/qt3dstudio-eastl.git;name=EASTL;branch=${QT_MODULE_BRANCH_EASTL};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/EASTL \
"

SRCREV_ogl-runtime = "a41270dced230d90e0e07f2ebb880e4f97317a7f"
SRCREV_EASTL = "31697c758f2ed19bd7c6bbe61f1b91f9e12035b5"
SRCREV = "${SRCREV_ogl-runtime}"

SRCREV_FORMAT = "ogl-runtime_EASTL"
