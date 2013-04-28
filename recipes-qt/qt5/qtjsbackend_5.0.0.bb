require ${PN}.inc
require qt5-${PV}.inc

# LICENSE files are missing in 5.0.0 and 5.0.1
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "f7a745c09173abdd9cd21851557f76cc"
SRC_URI[sha256sum] = "277106c4bf9a79ffaa458fbf66844b34dbbec7165b3edf94f98c0922fc1c0f39"
