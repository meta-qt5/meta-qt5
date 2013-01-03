
require recipes-graphics/directfb/directfb.inc

RV = "1.6-0"
PR = "r11"

DEPENDS += "sysfsutils virtual/libgl gstreamer gst-plugins-base"

SRC_URI = "http://directfb.org/downloads/Core/DirectFB-1.6/DirectFB-${PV}.tar.gz \
           file://remove_typeof_define.patch \
          "

EXTRA_OECONF = "\
  --enable-mesa \
  --enable-devmem \
  --enable-fbdev \
  --with-gfxdrivers=all \
  --with-inputdrivers=all \
  --enable-freetype=yes \
  --enable-zlib \
  --enable-gstreamer \
  --disable-sdl \
  --disable-vnc \
  --disable-x11 \
"

LEAD_SONAME = "libdirectfb-1.6.so.0.2.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=dcf3c825659e82539645da41a7908589"
SRC_URI[md5sum] = "6bebdbf26f03f7114ae17ab86d4d1d27"
SRC_URI[sha256sum] = "b91bc410837d794d2af140aa23cd8fb7ba2c76241fa9039aa6cad32d2d453d4d"

### Override FILES_${PN} until below gets pushed up stream
FILES_${PN} = "\
  ${bindir} \
  ${libdir}/lib*.so.* \
  ${libdir}/directfb-${RV}/interfaces/*/*.so \
  ${libdir}/directfb-${RV}/wm/*.so \
  ${datadir}/directfb-${PV} \
"

### Add FILES_${PN}-dev until below gets pushed up stream
FILES_${PN}-dev += "\
  ${libdir}/directfb-${RV}/gfxdrivers/*.la \
"

PACKAGES_DYNAMIC = "directfb-gfxdriver-* directfb-inputdriver-* directfb-system-*"

python populate_packages_prepend () {
        gfxdrivers_libdir = bb.data.expand('${libdir}/directfb-${RV}/gfxdrivers', d)
        inputdrivers_libdir = bb.data.expand('${libdir}/directfb-${RV}/inputdrivers', d)
        systems_libdir = bb.data.expand('${libdir}/directfb-${RV}/systems', d)

        do_split_packages(d, gfxdrivers_libdir, '^libdirectfb_(.*).so$', 'directfb-gfxdriver-%s', 'DirectFB graphics driver for %s', allow_dirs=False)
        do_split_packages(d, inputdrivers_libdir, '^libdirectfb_(.*).so$', 'directfb-inputdriver-%s', 'DirectFB input driver for %s', allow_dirs=False)
        do_split_packages(d, systems_libdir, '^libdirectfb_(.*).so$', 'directfb-system-%s', 'DirectFB system for %s', allow_dirs=False)
}

