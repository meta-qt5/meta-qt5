cat <<EOF
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

QMAKE_INCDIR_QT       = ${OE_QMAKE_INCDIR_QT}
QMAKE_LIBDIR_QT       = ${OE_QMAKE_LIBDIR_QT}
QMAKE_LIBS_X11        = ${OE_QMAKE_LIBS_X11}
QMAKE_LIBS_X11SM      = ${OE_QMAKE_LIBS_X11SM}
QMAKE_MOC             = ${OE_QMAKE_MOC}
QMAKE_UIC             = ${OE_QMAKE_UIC}
QMAKE_RCC             = ${OE_QMAKE_RCC}
#QMAKE_AR              = ${OE_QMAKE_AR} cqs
QMAKE_AR              = ${OE_QMAKE_AR}
QMAKE_STRIP           = ${OE_QMAKE_STRIP}
QMAKE_CFLAGS         += ${OE_QMAKE_CFLAGS}
QMAKE_CXXFLAGS       += \$\$QMAKE_CFLAGS ${OE_QMAKE_CXXFLAGS}
QMAKE_LFLAGS         += ${OE_QMAKE_LDFLAGS}
QMAKE_CC              = ${OE_QMAKE_CC}
QMAKE_LINK_C          = ${OE_QMAKE_LINK}
QMAKE_LINK_C_SHLIB    = ${OE_QMAKE_LINK}
QMAKE_CFLAGS_RELEASE_WITH_DEBUGINFO += ${OE_QMAKE_CFLAGS}
QMAKE_CXX             = ${OE_QMAKE_CXX}
QMAKE_LINK            = ${OE_QMAKE_LINK}
QMAKE_LINK_SHLIB      = ${OE_QMAKE_LINK}
QMAKE_CONF_COMPILER   = ${OE_QMAKE_CONF_COMPILER}

load(qt_config)
EOF
