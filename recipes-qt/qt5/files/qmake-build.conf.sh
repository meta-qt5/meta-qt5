#cat <<EOF
#
# qmake configuration for building with openembedded
#

MAKEFILE_GENERATOR      = UNIX
TARGET_PLATFORM         = unix
TEMPLATE                = app
CONFIG                  += qt warn_on release incremental link_prl gdb_dwarf_index
QT                      += core gui
QMAKE_INCREMENTAL_STYLE = sublib

include(../common/linux.conf)
include(../common/gcc-base-unix.conf)
include(../common/g++-unix.conf)

QMAKE_AR              = ${OE_QMAKE_AR} cqs
#QMAKE_AR              = ${BUILD_AR} cqs
QMAKE_STRIP           = ${BUILD_STRIP}
QMAKE_CFLAGS         += ${BUILD_CFLAGS}
QMAKE_CXXFLAGS       += \$\$QMAKE_CFLAGS ${BUILD_CXXFLAGS}
#QMAKE_LFLAGS         += ${BUILD_LDFLAGS}
QMAKE_LFLAGS		 += $(OE_QMAKE_LDFLAGS)
QMAKE_CC              = ${BUILD_CC}
QMAKE_LINK_C          = ${BUILD_CCLD}
QMAKE_LINK_C_SHLIB    = ${BUILD_CCLD}
QMAKE_CFLAGS_RELEASE_WITH_DEBUGINFO += ${OE_QMAKE_CFLAGS}
QMAKE_CXX             = ${BUILD_CXX}
QMAKE_LINK            = ${BUILD_CXX} ${BUILD_LDFLAGS}
QMAKE_LINK_SHLIB      = ${BUILD_CXX} ${BUILD_LDFLAGS}
QMAKE_RANLIB = ${BUILD_RANLIB}
