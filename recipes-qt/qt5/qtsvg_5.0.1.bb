require ${PN}.inc
require qt5-${PV}.inc

# LICENSE files are missing in 5.0.1
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "4bb52d0382d2557781c39cc27f380ce7"
SRC_URI[sha256sum] = "bd053b808b64aad8bfbd3f5399444adedaff97aa16f03e54de91bb0873c20fd3"
