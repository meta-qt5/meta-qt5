require qt5-${PV}.inc
require ${PN}.inc

# LICENSE.FDL is missing in 5.0.2, added a bit later:
# commit 3bc0756a63d348d3446d9581f45429c417fd9c29
# Author: Sergio Ahumada <sergio.ahumada@digia.com>
# Date:   Thu Apr 18 11:38:32 2013 +0200
# Add missing LICENSE.FDL file
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=4193e7f1d47a858f6b7c0f1ee66161de \
                    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LGPL_EXCEPTION.txt;md5=0145c4d1b6f96a661c2c139dfb268fb6 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "29e6776165e43e9f3a865338121b4e1d"
SRC_URI[sha256sum] = "c05ae43fffc911ffda808deecebddd1b31916714cefc9cfd840689fc52ae20a6"
