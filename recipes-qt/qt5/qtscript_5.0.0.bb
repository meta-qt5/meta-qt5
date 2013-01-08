require ${PN}.inc
require qt5-${PV}.inc

# LICENSE files are missing in 5.0.0
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "0759bd132e46fb6d95451764018f3a92"
SRC_URI[sha256sum] = "8722e48c5f1f989ae920288fb4f5176ae5563775442d9f0aec1fc2e79869fb5f"
