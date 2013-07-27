require qt5-${PV}.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.0 and 5.0.1
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/LGPL-2.1;md5=1a6d268fd218675ffea8be556788b780 \
                    file://${COMMON_LICENSE_DIR}/GPL-3.0;md5=c79ff39f19dfec6d293b95dea7b07891 \
"

SRC_URI[md5sum] = "f3cc602d4b720a847f4ab0953a82d8ef"
SRC_URI[sha256sum] = "9b9aa948e01bf9d0fc7fa4584ededf9b5b280ee74c334c5790dbc6f9015b3738"
