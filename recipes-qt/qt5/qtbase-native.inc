DESCRIPTION = "Native version of Qt/[X11|Mac|Embedded]"
DEPENDS = "zlib-native dbus-native"
SECTION = "libs"
HOMEPAGE = "http://qt-project.org"

INC_PR = "r1"

inherit native

QT_MODULE = "qtbase"

SSTATE_SCAN_FILES += "*.pri *.prl"
QT_DIR_NAME ?= "qt5"

SRC_URI += " \
  file://0001-qlibraryinfo-allow-to-set-qt.conf-from-the-outside-u.patch \
  file://0001-Always-build-uic.patch \
"

EXTRA_ENV = 'MAKE="make -e ${PARALLEL_MAKE}"'
EXTRA_OEMAKE = " MAKEFLAGS='${PARALLEL_MAKE}'"

EXTRA_OECONF = " \
  -prefix ${prefix} \
  -L ${STAGING_LIBDIR_NATIVE} \
  -I ${STAGING_INCDIR_NATIVE} \
  -system-zlib \
  -no-libjpeg \
  -no-libpng \
  -no-gif \
  -no-accessibility \
  -no-cups \
  -no-nis \
  -no-gui \
  -no-qml-debug \
  -no-sql-mysql \
  -no-sql-sqlite \
  -no-opengl \
  -no-openssl \
  -no-xcb \
  -verbose -release \
  -prefix ${prefix} \
  -bindir ${bindir}/${QT_DIR_NAME} \
  -headerdir ${includedir}/${QT_DIR_NAME} \
  -archdatadir ${libdir}/${QT_DIR_NAME} \
  -datadir ${datadir}/${QT_DIR_NAME} \
  -docdir ${docdir}/${QT_DIR_NAME} \
  -hostdatadir ${datadir}/${QT_DIR_NAME} \
  -hostbindir ${bindir}/${QT_DIR_NAME} \
  -no-glib \
  -no-iconv \
  -silent \
  -nomake examples \
  -nomake tests \
  -nomake demos \
  -no-rpath \
"

do_configure() {
	# Avoid setting QMAKE_LINK from LD (since we want the linker to be g++)
	unset LD

	(echo o; echo yes) | ./configure ${EXTRA_OECONF} || die "Configuring qt failed. EXTRA_OECONF was ${EXTRA_OECONF}"
}

do_install() {
	oe_runmake install INSTALL_ROOT=${D}
	ln -sf linux-g++ ${D}${datadir}/${QT_DIR_NAME}/mkspecs/${BUILD_OS}-oe-g++

	# replace common files with our version
	cp -f ${WORKDIR}/g++.conf ${D}${datadir}/${QT_DIR_NAME}/mkspecs/common/g++-unix.conf
	cp -f ${WORKDIR}/linux.conf ${D}${datadir}/${QT_DIR_NAME}/mkspecs/common/linux.conf
}
