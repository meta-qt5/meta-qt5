require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.2
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "a000016afd3672540b2488c2f8e8d8b3"
SRC_URI[sha256sum] = "5983485ade365a9e809d4614cc76fb106c35d5c514e0312a9013bb25227a6521"
