require qt5.inc
require qt5-git.inc

LICENSE = "BSD & LGPLv2+ | GPL-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE.LGPLv21;md5=58a180e1cf84c756c29f782b3a485c29 \
    file://Source/JavaScriptCore/parser/Parser.h;endline=21;md5=bd69f72183a7af673863f057576e21ee \
"

DEPENDS += "qtbase qtdeclarative icu ruby-native sqlite3 glib-2.0 libxslt gperf-native"

# qemuarm build fails with:
# | {standard input}: Assembler messages:
# | {standard input}:106: Error: invalid immediate: 983040 is out of range
# | {standard input}:106: Error: value of 983040 too large for field of 2 bytes at 146
ARM_INSTRUCTION_SET_armv4 = "arm"
ARM_INSTRUCTION_SET_armv5 = "arm"

# https://bugzilla.yoctoproject.org/show_bug.cgi?id=9474
# https://bugs.webkit.org/show_bug.cgi?id=159880
# JSC JIT can build on ARMv7 with -marm, but doesn't work on runtime.
# Upstream only tests regularly the JSC JIT on ARMv7 with Thumb2 (-mthumb).
ARM_INSTRUCTION_SET_armv7a = "thumb"
ARM_INSTRUCTION_SET_armv7r = "thumb"
ARM_INSTRUCTION_SET_armv7ve = "thumb"

PACKAGECONFIG ??= "gstreamer qtlocation qtmultimedia qtsensors qtwebchannel \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'libxcomposite libxrender', '', d)} \
    fontconfig \
"
PACKAGECONFIG[gstreamer] = "OE_GSTREAMER_ENABLED,,gstreamer1.0 gstreamer1.0-plugins-base"
PACKAGECONFIG[gstreamer010] = "OE_GSTREAMER010_ENABLED,,gstreamer gst-plugins-base"
PACKAGECONFIG[qtlocation] = "OE_QTLOCATION_ENABLED,,qtlocation"
PACKAGECONFIG[qtmultimedia] = "OE_QTMULTIMEDIA_ENABLED,,qtmultimedia"
PACKAGECONFIG[qtsensors] = "OE_QTSENSORS_ENABLED,,qtsensors"
PACKAGECONFIG[qtwebchannel] = "OE_QTWEBCHANNEL_ENABLED,,qtwebchannel"
PACKAGECONFIG[libwebp] = "OE_LIBWEBP_ENABLED,,libwebp"
PACKAGECONFIG[libxcomposite] = "OE_LIBXCOMPOSITE_ENABLED,,libxcomposite"
PACKAGECONFIG[libxrender] = "OE_LIBXRENDER_ENABLED,,libxrender"
PACKAGECONFIG[fontconfig] = "OE_FONTCONFIG_ENABLED,,fontconfig"

do_configure_prepend() {
    export QMAKE_CACHE_EVAL="CONFIG+=${PACKAGECONFIG_CONFARGS}"
}

# Forcibly enable ICU, so qtbase doesn't need it.
EXTRA_QMAKEVARS_PRE += "QT_CONFIG+=icu"

# qtwebkit gets terribly big when linking with all debug info, disable by default
QTWEBKIT_DEBUG = "QMAKE_CFLAGS+=-g0 QMAKE_CXXFLAGS+=-g0"
EXTRA_QMAKEVARS_PRE += "${QTWEBKIT_DEBUG}"

# remove default ${PN}-examples* set in qt5.inc, because they conflicts with ${PN} from separate webkit-examples recipe
PACKAGES_remove = "${PN}-examples-dev ${PN}-examples-staticdev ${PN}-examples-dbg ${PN}-examples"

# make sure rb files are used from sysroot, not from host
# ruby-1.9.3-always-use-i386.patch is doing target_cpu=`echo $target_cpu | sed s/i.86/i386/`
# we need to replace it too (a bit longer version without importing re)
RUBY_SYS = "${@ '${BUILD_SYS}'.replace('i486', 'i386').replace('i586', 'i386').replace('i686', 'i386') }"
export RUBYLIB="${STAGING_DATADIR_NATIVE}/rubygems:${STAGING_LIBDIR_NATIVE}/ruby:${STAGING_LIBDIR_NATIVE}/ruby/${RUBY_SYS}"

QT_MODULE_BRANCH = "dev"

SRCREV = "beaeeb99881184fd368c121fcbb1a31c78b794a3"
