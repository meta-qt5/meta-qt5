require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.2
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "f0902250974440c9c63569112a67440c"
SRC_URI[sha256sum] = "fffc7bd71e6cb2853da3c90d7f9794583e7a83e4eeb6fea039a2efac53688834"
