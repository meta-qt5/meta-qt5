require qt5-git.inc
require ${PN}.inc

# LICENSE files are missing in 5.0.0
LIC_FILES_CHKSUM = "file://${S}/src/imports/systeminfo/qsysteminfo.cpp;endline=40;md5=f28e1baba502dda6849d69d5c24e7356 \
                    file://${S}/examples/systeminfo/qml-storageinfo/content/ProgressBar.qml;endline=39;md5=5213e8171c07d54db7107f29ac2f7b5e \
                    file://${S}/doc/src/systeminfo/systeminfo.qdoc;endline=26;md5=757f4eda130ceff3ca0985dde715af07 \
"

# this wasn't released, the PV is set just to signify that this SRCREV was tested together
# with 5.3.2 version of other modules
PV = "5.3.2"
DEFAULT_PREFERENCE = "1"

QT_MODULE_BRANCH = "5.3"

# qtsystems wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.3.2

SRCREV = "aa651c73bf7bc57c1b6b1bfcfa9afe901884a102"
