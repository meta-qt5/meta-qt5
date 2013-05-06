require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.0
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "bc24e00ded809c0c457ff3480c6d4198"
SRC_URI[sha256sum] = "55915e30ba089dd6f9cd5e0dfe93e231c4d741da0f85a2e16d5a47f948424cc4"
