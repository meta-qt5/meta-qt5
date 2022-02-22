require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0-only & The-Qt-Company-GPL-Exception-1.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
"

DEPENDS += "qtbase qtdeclarative qtquickcontrols2"

QT_MODULE_BRANCH = "master"
QT_MODULE_BRANCH_EASTL = "master"
QT_GIT_PROJECT = "qt3dstudio"
PV = "2.8.0+git${SRCPV}"

COMPATIBLE_HOST = "(i.86|x86_64|aarch64|arm|powerpc64).*-linux"

SRC_URI += " \
    ${QT_GIT}/qt3dstudio-eastl.git;name=EASTL;branch=${QT_MODULE_BRANCH_EASTL};protocol=${QT_GIT_PROTOCOL};destsuffix=git/src/3rdparty/EASTL \
    file://0001-Fix-examples-build-error.patch \
    file://0001-Qt3DSSimpleTypes-make-QT3DSU64-as-unsigned-long-on-l.patch \
    file://0001-Qt3D-Add-support-to-fix-build-on-ppc64.patch \
"

SRCREV_ogl-runtime = "b53aeb784f950d7b2a5ebe38ea37c5bb6f456c9c"
SRCREV_EASTL = "31697c758f2ed19bd7c6bbe61f1b91f9e12035b5"
SRCREV = "${SRCREV_ogl-runtime}"

SRCREV_FORMAT = "ogl-runtime_EASTL"
