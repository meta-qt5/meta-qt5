require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.0
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "1031f8021a2b2e3a2387d94103e8868e"
SRC_URI[sha256sum] = "e9cf71b4da1a1e879845f1fb1a532408ff6a0875e407a65d6bb3ce3dff0fe942"

