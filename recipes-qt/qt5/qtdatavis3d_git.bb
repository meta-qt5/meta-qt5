require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

DEPENDS += "qtbase qtdeclarative qtmultimedia qtxmlpatterns"

# Use 46a49e34b9644da11f9e68d6ea3b45d586c711cf
# instead of 243106a56ff681ffefcf65af8ecd30fc14181200 because
# v5.9.8 tag wasn't merged to 5.9 branch yet
SRCREV = "46a49e34b9644da11f9e68d6ea3b45d586c711cf"
