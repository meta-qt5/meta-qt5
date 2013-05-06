require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.0 and 5.0.1
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "af5ccb9d5ab589df03eb0b12fb5ab4cd"
SRC_URI[sha256sum] = "3fc8fca258b8c73f935e509c5c1efdc104725e4b44e240e9031b12901e7b971a"
