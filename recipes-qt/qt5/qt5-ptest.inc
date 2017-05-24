SRC_URI += "file://run-ptest"

inherit ptest

addtask do_populate_sysroot after do_install before do_compile_ptest_base
deltask do_compile_ptest_base
addtask do_compile_ptest_base after do_populate_sysroot before do_install_ptest
addtask do_install_ptest after do_compile_ptest_base before do_package

do_compile_ptest() {
    cd ${S}/tests
    qmake -o Makefile tests.pro
    oe_runmake
}

fakeroot do_install_ptest() {
    mkdir -p ${D}${PTEST_PATH}
    t=${D}${PTEST_PATH}
    for var in ` find ${S}/tests/auto/ -name tst_*`; do
        if [ not ` echo ${var##*/} | grep '\.'` ]; then
            echo ${var##*/} >> ${t}/tst_list
            install -m 0644  ${var} ${t}
        fi
    done
}
