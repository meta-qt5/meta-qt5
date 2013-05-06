require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.1
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "aee9c86a7ff64dfc905ccaef33f0a967"
SRC_URI[sha256sum] = "e87fe8fbc1465840458a88de0be10f096374fb7cd0888c703710c0ecc936d414"
