SRCREV = "b41c2151fdfca3f63a6cd45f6c69ae678694b63e"
SRC_URI += "git://gitorious.org/qt/qtjsbackend.git;protocol=git"
S = "${WORKDIR}/git"

require qtjsbackend.inc

PR = "${INC_PR}.0"
