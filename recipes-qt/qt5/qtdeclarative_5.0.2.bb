require qt5-${PV}.inc
require ${PN}.inc

PR = "r1"

SRC_URI += " \
    file://0001-qmltestexample-fix-link.patch \
    file://0002-Fix-displacement-transition-bug-for-horizontal-case.patch \
    file://0003-Fix-wrong-calculation-of-viewPort-for-transitions.patch \
    file://0004-Fix-null-pointer-access-in-QQuickVisualDataModelPriv.patch \
    file://0005-Avoid-swizzling-on-OpenGL-ES-when-possible.patch \
"

SRC_URI[md5sum] = "55ab45a7a17db5b202b225603a35a37c"
SRC_URI[sha256sum] = "dcfcc6c0d1913d285dd7b42dd9bc457c304f3e3074a1e0d875fff1e9a8318520"
