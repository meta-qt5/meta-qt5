require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.0 and 5.0.1
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

PR = "${INC_PR}.1"

SRC_URI += " \
    file://0001-Flickable-Fix-bug-when-flicking-twice-using-touches.patch \
"

SRC_URI[md5sum] = "5373ebb2f78e27e14d0c2b3997599832"
SRC_URI[sha256sum] = "eb13966ecaa61baff53f19b03e97f0e7ca7103a25d89c7540f6e8d7d98bf59bd"
