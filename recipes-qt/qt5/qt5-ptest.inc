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

do_populate_sysroot_append() {
    workdir = d.getVar('WORKDIR')
    srcdir = workdir + '/sysroot-destdir/usr/'
    destdir = workdir + '/recipe-sysroot/usr/'
    
    def copyFiles(sourceDir, targetDir, filelist):
        for file in os.listdir(sourceDir):
            sourceFile = os.path.join(sourceDir,  file)
            targetFile = os.path.join(targetDir,  file)
            filelist.append(targetFile)    #record the file list
            if os.path.isfile(sourceFile):
                if not os.path.exists(targetDir):
                    os.makedirs(targetDir)
                if not os.path.exists(targetFile) or(os.path.exists(targetFile) and (os.path.getsize(targetFile) != os.path.getsize(sourceFile))):
                        open(targetFile, "wb").write(open(sourceFile, "rb").read())
            if os.path.isdir(sourceFile):
                First_Directory = False
                copyFiles(sourceFile, targetFile, filelist)
    
    if os.path.exists(destdir):
        Tmpfilelist = []
        copyFiles(srcdir, destdir, Tmpfilelist)
        
        fp=open(workdir + '/filelist', 'w')
        for i in Tmpfilelist:
            fp.write(i)
            fp.write("\n")
        fp.close()
}

fakeroot do_install_ptest() {
    mkdir -p ${D}${PTEST_PATH}
    t=${D}${PTEST_PATH}
    for var in ` find ${S}/tests/auto/ -name tst_*`; do
        if [ -z ` echo ${var##*/} | grep '\.'` ]; then
            echo ${var##*/} >> ${t}/tst_list
            install -m 0644  ${var} ${t}
        fi
    done
    for file in `cat ${WORKDIR}/filelist`; do
       if [ -f $file ]; then
           rm -f $file
       fi
    done 
    rm -f ${WORKDIR}/filelist
}

