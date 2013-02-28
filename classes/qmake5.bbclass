#
# QMake variables for Qt
#
inherit qmake5_base

QT5TOOLSDEPENDS ?= "qtbase-native "
DEPENDS_prepend = "${QT5TOOLSDEPENDS}"

export QMAKESPEC = "${STAGING_DATADIR}/qt5/mkspecs/${TARGET_OS}-oe-g++"
export OE_QMAKE_UIC = "${STAGING_BINDIR_NATIVE}/uic"
export OE_QMAKE_MOC = "${STAGING_BINDIR_NATIVE}/moc"
export OE_QMAKE_RCC = "${STAGING_BINDIR_NATIVE}/rcc"
export OE_QMAKE_QMAKE = "${STAGING_BINDIR_NATIVE}/qmake"
export OE_QMAKE_LINK = "${CXX}"
export OE_QMAKE_CXXFLAGS = "${CXXFLAGS}"
export OE_QMAKE_INCDIR_QT = "${STAGING_INCDIR}/qt5"
export OE_QMAKE_LIBDIR_QT = "${STAGING_LIBDIR}"
export OE_QMAKE_LIBS_QT = "qt"
export OE_QMAKE_LIBS_X11 = "-lXext -lX11 -lm"
export OE_QMAKE_LIBS_X11SM = "-lSM -lICE"
export OE_QMAKE_LRELEASE = "${STAGING_BINDIR_NATIVE}/lrelease5"
export OE_QMAKE_LUPDATE = "${STAGING_BINDIR_NATIVE}/lupdate5"
