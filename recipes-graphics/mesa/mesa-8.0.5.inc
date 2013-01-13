LIC_FILES_CHKSUM = "file://docs/license.html;md5=03ccdc4c379c4289aecfb8892c546f67"

SRC_URI = " \
            ftp://ftp.freedesktop.org/pub/mesa/${PV}/MesaLib-${PV}.tar.bz2;name=archive \
            file://cross_compile_fix.patch \
          "

SRC_URI[archive.md5sum] = "01305591073a76b65267f69f27d635a3"
SRC_URI[archive.sha256sum] = "511b8da34f8e69ed24caf422964fb7ae747f3b1d8093f6b8aa2602164a475a5e"

PROVIDES += "virtual/libgl virtual/libegl virtual/libgles1 virtual/libgles2"
PROVIDES += "gl-dev egl-dev libgles1-dev libgles2-dev"

EXTRA_OECONF := "${@oe_filter_out('--disable-egl', '${EXTRA_OECONF}', d)}"
EXTRA_OECONF += "--enable-gles1 --enable-gles2 --enable-gbm --enable-shared-glapi"

DEPENDS += "talloc"

S = "${WORKDIR}/Mesa-${PV}"

do_configure_prepend() {
  #check for python not python2, because python-native does not stage python2 binary/link
  sed -i 's/AC_CHECK_PROGS(\[PYTHON2\], \[python2 python\])/AC_CHECK_PROGS(\[PYTHON2\], \[python python\])/g' ${S}/configure.ac
}

PACKAGES =+ "libgles1 libgles2 libglapi libopenvg libgbm"
PACKAGES =+ "libgles1-dev libgles2-dev libglapi-dev libopenvg-dev libgbm-dev"
PACKAGES =+ "libgbm-dbg"

FILES_libgles1 = "${libdir}/libGLESv1*.so.*"
FILES_libgles2 = "${libdir}/libGLESv2.so.*"
FILES_libglapi = "${libdir}/libglapi.so.*"
FILES_libopenvg = "${libdir}/libOpenVG.so.*"
FILES_libgbm = "${libdir}/libgbm.so.* ${libdir}/gbm/*.so"

FILES_libgles1-dev = "${libdir}/libGLESv1*.so  ${includedir}/GLES ${libdir}/pkgconfig/glesv1*.pc"
FILES_libgles2-dev = "${libdir}/libGLESv2.so  ${includedir}/GLES2 ${libdir}/pkgconfig/glesv2.pc"
FILES_libglapi-dev = "${libdir}/libglapi.so"
FILES_libopenvg-dev = "${libdir}/libOpenVG.so  ${includedir}/VG ${libdir}/pkgconfig/vg.pc"
FILES_libgbm-dev = "${libdir}/libgbm.so ${includedir}/gbm.h ${libdir}/pkgconfig/gbm.pc"

FILES_libgbm-dbg = "${libdir}/gbm/.debug ${libdir}/.debug/libgbm.so"
