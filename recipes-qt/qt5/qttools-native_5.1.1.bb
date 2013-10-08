require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.0 and 5.0.1
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
"

SRC_URI[md5sum] = "022073d32ff9d408de0182b5d1f01781"
SRC_URI[sha256sum] = "2b42c6d5feeccffb67e890b86a150bae64dd2ff550be39a3cc449ee0e95462b6"
