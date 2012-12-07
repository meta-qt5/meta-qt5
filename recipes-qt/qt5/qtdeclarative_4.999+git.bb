SRCREV = "90ac5018823ab1109d084cbe0e9e63a4cd531faa"
SRC_URI += "git://gitorious.org/qt/qtdeclarative.git;protocol=git"
S = "${WORKDIR}/git"

require qtdeclarative.inc

PR = "${INC_PR}.0"
