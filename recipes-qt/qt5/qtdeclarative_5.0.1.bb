require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.0 and 5.0.1
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "91bb22db9501847e7549e8a431d81b78"
SRC_URI[sha256sum] = "fa7c6941626ff4a40419f9be286f478b0461ac57f4a058035b3e1a9043dfee85"
