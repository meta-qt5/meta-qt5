FILESEXTRAPATHS_append := ":${THISDIR}/ptest"
SRC_URI += "file://run-ptest"

inherit ptest

do_compile_ptest() {
    mkdir -p ${B}/tests
    cd ${B}/tests
    ${OE_QMAKE_QMAKE} -o Makefile ${S}/tests/tests.pro
    oe_runmake
}

fakeroot do_install_ptest() {
    mkdir -p ${D}${PTEST_PATH}
    t=${D}${PTEST_PATH}
    for var in ` find ${B}/tests/auto/ -name tst_*`; do
        if [ -z ` echo ${var##*/} | grep '\.'` ]; then
            echo ${var##*/} >> ${t}/tst_list
            install -m 0644  ${var} ${t}
        fi
    done
}
