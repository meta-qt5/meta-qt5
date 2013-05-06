require qt5-git.inc
require ${PN}.inc

# LICENSE files are missing in this old SRCREV, remove when upgraded
# commit 54e6103798bb098bc9e83aa77decdad4868cb98e
# Author: Timo Jyrinki <timo.jyrinki@canonical.com>
# Date:   Mon Feb 11 08:19:48 2013 +0200
# Subject: Add license files mandated by (L)GPL.
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
                    file://${COMMON_LICENSE_DIR}/GFDL-1.3;md5=1083add59b39991c748ea70a92166959 \
"

# qtlocation wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
PV = "4.999+5.0.0-beta1+git${SRCPV}"

PR = "${INC_PR}.0"

QT_MODULE_BRANCH = "master"

SRCREV = "ac83b242c26d5b8750b6bf0f9ed0bac0e4569b6c"
