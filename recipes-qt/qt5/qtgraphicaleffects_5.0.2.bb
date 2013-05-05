require ${PN}.inc
require qt5-${PV}.inc

# LICENSE files are missing in 5.0.2
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "3d4475a4702f7bbe8064f69e17f1e8dd"
SRC_URI[sha256sum] = "944399c5795b83b8f6b5e4ccec763ce5192f0e99b5e6d256f2b43a3165ee3016"
