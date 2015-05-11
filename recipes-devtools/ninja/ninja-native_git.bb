DESCRIPTION = "Ninja is a small build system with a focus on speed."
LICENSE = "Apache-2"

inherit native

LIC_FILES_CHKSUM = "file://COPYING;md5=a81586a64ad4e476c791cda7e2f2c52e"

PV = "1.5.3+gitr${SRCPV}"
SRCREV="3309498174411e02e7680ea8b470bb7d1d70bdb8"
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
