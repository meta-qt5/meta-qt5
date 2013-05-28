require qt5-${PV}.inc
require ${PN}.inc

PR = "${INC_PR}.0"

SRC_URI += " \
    file://0016-Allow-tslib-to-be-specified-at-configure-time.patch \
    file://0017-Rename-qAbs-Function-for-timeval.patch \
"

SRC_URI[md5sum] = "a4fec8ed03867c4ee4fe5a46001a11f0"
SRC_URI[sha256sum] = "31851ee2f844c100554506a9a446d4b6abb5270bca799c2a683e5f937456a9c8"
