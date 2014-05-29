require qt5.inc

DEPENDS += "qtbase qtdeclarative qtwebkit qtxmlpatterns"

SRC_URI += " \
    file://0002-assistant-help-fix-linking-of-dependent-libraries.patch \
"

# older copyright year than what e.g. qtbase is using now
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de \
                    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
                    file://LICENSE.FDL;md5=6d9f2a9af4c8b8c3c769f6cc1b6aaf7e"

FILES_${PN}-examples = "${datadir}/${QT_DIR_NAME}/examples"
