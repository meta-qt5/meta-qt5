require qt5.inc
require qt5-git.inc

LICENSE = "GFDL-1.3 & (GPL-3 | LGPL-3.0)"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
    file://LICENSE.GPL3-EXCEPT;md5=763d8c535a234d9a3fb682c7ecb6c073 \
    file://LICENSE.LGPL3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

DEPENDS += "qtbase qtdeclarative"

# Use 3a7a8c98a12569f133f7914118e13ec919f2e484
# instead of b5d8a09e573ae5c194a962e84a31ef3251250258 because
# v5.9.8 tag wasn't merged to 5.9 branch yet
SRCREV = "3a7a8c98a12569f133f7914118e13ec919f2e484"
