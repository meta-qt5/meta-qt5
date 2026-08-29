require qt5.inc
require qt5-git.inc

HOMEPAGE = "http://www.qt.io"
LICENSE = "BSD-3-Clause AND GFDL-1.3-only AND (GPL-3.0-only AND LicenseRef-The-Qt-Company-GPL-Exception-1.0 OR LicenseRef-The-Qt-Company-Commercial) AND (GPL-2.0-or-later OR LGPL-3.0-only OR LicenseRef-The-Qt-Company-Commercial)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=4bfd28363f541b10d9f024181b8df516 \
    file://LICENSE.LGPLv3;md5=e0459b45c5c4840b353141a8bbed91f0 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e \
"

DEPENDS += "qtbase"

SRCREV = "ba3671e467d952f9d47649654082143582984e1d"
