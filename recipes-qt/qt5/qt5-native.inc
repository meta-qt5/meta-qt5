DESCRIPTION = "Native version of Qt/[X11|Mac|Embedded]"
DEPENDS = "zlib-native dbus-native"
SECTION = "libs"
HOMEPAGE = "http://qt-project.org"
PROVIDES = "qt5-tools-native"

LICENSE = "LGPLv2.1 | GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE.LGPL;md5=cc5963be04f8b19c3e3c46bc31f0f6e1 \
                    file://LICENSE.GPL;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LGPL_EXCEPTION.txt;md5=411080a56ff917a5a1aa08c98acae354"

INC_PR = "r0"

include qt5.inc

inherit native

QT_MODULE = "qtbase"

EXTRA_OECONF = "-prefix ${prefix} \
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
                -verbose -release \
                -headerdir ${includedir}/qt5 \
                -no-glib \
                -no-iconv \
                -no-fast \
                -silent \
                -nomake examples \
                -nomake tests \
                -no-rpath"

do_configure() {
	# Avoid setting QMAKE_LINK from LD (since we want the linker to be g++)
	unset LD

	(echo o; echo yes) | ./configure ${EXTRA_OECONF} || die "Configuring qt failed. EXTRA_OECONF was ${EXTRA_OECONF}"
}

do_install() {
    install -d ${D}${bindir}/
    # Install only the qmake bin for now.
    install -m 0755 ${S}/bin/qmake ${D}${bindir}/qmake
}
