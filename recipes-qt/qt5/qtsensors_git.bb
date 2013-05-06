require qt5-git.inc
require ${PN}.inc

# LICENSE files are missing in this old SRCREV, remove when upgraded
# commit cf831b83142dee8114df3bc37c29116a66945f5f
# Author: Timo Jyrinki <timo.jyrinki@canonical.com>
# Date:   Mon Feb 11 08:56:35 2013 +0200
# Subject: Add license files mandated by (L)GPL.
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

# qtsensors wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

PR = "${INC_PR}.0"

SRCREV = "6323be3e2fc1b69145f37cda1d0214ec5fa3cb44"
