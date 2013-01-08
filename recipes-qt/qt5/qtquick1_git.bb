require ${PN}.inc
require qt5-git.inc

# LICENSE files are missing in this old SRCREV, remove when upgraded
# commit 22e940b2c97364df12dbdfb51e9fc5a8eaf8d268
# Author: Timo Jyrinki <timo.jyrinki@canonical.com>
# Date:   Fri Feb 8 09:12:41 2013 +0200
# Subject: Add license files mandated by (L)GPL.
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

# last tag before this SRCREV is 5.0.0
PV = "5.0.0+git${SRCPV}"

PR = "${INC_PR}.0"

SRCREV = "be48320bb7213edf0fbca481650a0bc6cf203d30"
