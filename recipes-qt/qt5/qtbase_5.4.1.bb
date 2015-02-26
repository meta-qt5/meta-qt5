require qt5-${PV}.inc
require ${PN}.inc

# This patch is in 5.4 branch but didn't make it into 5.4.1 release
SRC_URI += "\
    file://0014-fix-build-of-egl-integration.patch \
"

SRC_URI[md5sum] = "9507825e558c980fed602de1f16ec7ae"
SRC_URI[sha256sum] = "8574a593830959c0f7e5430fe77a43832ea7f5299e14a397a74576b3df7fb1b7"
