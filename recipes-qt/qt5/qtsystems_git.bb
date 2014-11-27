require qt5-git.inc
require ${PN}.inc

QT_MODULE_BRANCH = "dev"

# qtsystems wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.4

SRCREV = "3a0216301ad1338126abb326e8e4c22c6ea12f40"

LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de \
                    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
                    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e"
