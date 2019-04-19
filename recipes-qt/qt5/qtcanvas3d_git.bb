require qt5.inc
require qt5-git.inc

LICENSE = "LGPL-3.0 | GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv3;md5=e0459b45c5c4840b353141a8bbed91f0 \
    file://LICENSE.GPLv3;md5=88e2b9117e6be406b5ed6ee4ca99a705 \
    file://LICENSE.GPLv2;md5=c96076271561b0e3785dad260634eaa8 \
"

DEPENDS = "qtdeclarative"

# Use 909a41f68455cf9f7e0d5ba14ca59641a18b6c6f
# instead of 2097038d94301c0a1e684c939d29bc24620ce3c4
# because v5.9.8 tag wasn't merged to 5.9 branch yet
SRCREV = "909a41f68455cf9f7e0d5ba14ca59641a18b6c6f"
