require ${PN}.inc
require qt5-${PV}.inc

# LICENSE files are missing in 5.0.1
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "349691431c3cc1b03e2571bac7c6c852"
SRC_URI[sha256sum] = "0b5901a78e8bc6853e7d549f2e8109794b2fb03b7ce22963eb3897eb97256f84"
