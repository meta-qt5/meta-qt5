DESCRIPTION = "Ninja is a small build system with a focus on speed."
LICENSE = "Apache-2"

inherit native

LIC_FILES_CHKSUM = "file://COPYING;md5=a81586a64ad4e476c791cda7e2f2c52e"

SRCREV="63d5b1013cafb2db95687cf446eb5bb68cf6a27a"
SRCBRANCH="release"

SRC_URI = "git://github.com/martine/ninja.git;branch=${SRCBRANCH}"

S="${WORKDIR}/git"

do_compile() {
    python ${S}/bootstrap.py
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/ninja ${D}${bindir}/ninja
}
