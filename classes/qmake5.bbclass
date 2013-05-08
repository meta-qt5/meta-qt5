#
# QMake variables for Qt
#
inherit qmake5_base

QT5TOOLSDEPENDS ?= "qtbase-native "
DEPENDS_prepend = "${QT5TOOLSDEPENDS}"

# do we still need to export these?
#OE_QMAKE_INCDIR_QT = "${STAGING_INCDIR}/qt5"
#OE_QMAKE_LIBDIR_QT = "${STAGING_LIBDIR}"
#OE_QMAKE_LIBS_QT = "qt"
#OE_QMAKE_LIBS_X11 = "-lXext -lX11 -lm"
#OE_QMAKE_LIBS_X11SM = "-lSM -lICE"
#OE_QMAKE_LRELEASE = "${STAGING_BINDIR_NATIVE}/lrelease5"
#OE_QMAKE_LUPDATE = "${STAGING_BINDIR_NATIVE}/lupdate5"
