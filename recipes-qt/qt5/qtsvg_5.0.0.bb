require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.0
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "2dbed5aeae30bd492a38ca230e69e038"
SRC_URI[sha256sum] = "be3674b5b058503ba90ae9138fec8de25c2e67a12059e937b3540e50a1ca08cf"
