require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.1
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "27820e2447e4c3b7ea75f5d4a16b9149"
SRC_URI[sha256sum] = "507d02a5ffaa4f045f5b9cc807190d0f9db80c09b12feb4b4aa2fdb62d1bd529"
