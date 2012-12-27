DESCRIPTION = "Native version of Qt/[X11|Mac|Embedded]"
DEPENDS = "zlib-native dbus-native"
SECTION = "libs"
HOMEPAGE = "http://qt-project.org"
PROVIDES = "qt5-tools-native"

INC_PR = "r0"

inherit native

QT_MODULE = "qtbase"

FILESEXTRAPATHS =. "${FILE_DIRNAME}/${PN}:${FILE_DIRNAME}/qtbase:"

SRC_URI += " \
  file://0001-qlibraryinfo-Allow-the-specification-of-where-to-loa.patch \
  file://0003-qtbase-Allow-qt.conf-override-when-bootstrapping.patch \
  file://0001-Always-build-uic.patch \
"

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
  -headerdir ${includedir}/qt5 \
  -datadir ${datadir}/qt5 \
  -docdir ${docdir}/qt5 \
  -no-glib \
  -no-iconv \
  -no-fast \
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
	install -d ${D}${bindir}/
	for i in findtr fixqt4headers.pl moc qdoc qmake rcc qdbuscpp2xml qdbusxml2cpp syncqt uic; do
		install -v -m 0755 bin/${i} ${D}${bindir}/${i}
	done
	install -d ${D}${datadir}/qt5/
	cp -PfR mkspecs ${D}${datadir}/qt5/
}
