# Copyright (C) 2012 O.S. Systems Software LTDA.

inherit qmake5_base

QT_DIR_NAME = "qt5"
QT_BASE_NAME = "qt5"

# Qt5 is dependent on icu for localization
ICU = "icu "
ICU_powerpc = "pango"

DEPENDS += "qt5-tools-native virtual/libgl freetype jpeg libpng zlib openssl glib-2.0 ${ICU}"

require qt5_arch.inc

QT_MODULE ?= "${BPN}"

QT_DISTRO_FLAGS ?= "-no-accessibility -no-sm"
QT_DISTRO_FLAGS_linuxstdbase = "-sm"

# Some can be used only for certain QT_MODULEs, so define them here, 
# but add them to QT_CONFIG_FLAGS e.g. in qtbase.inc
QT_SQL_DRIVER_FLAGS ?= "-no-sql-ibase -no-sql-mysql -no-sql-psql -no-sql-odbc -plugin-sql-sqlite"
QT_GLFLAGS ?= ""
QT_XML ?= "-xmlpatterns"
QT_WEBKIT ?= "-webkit"
QT_PHONON ?= "-phonon"
QT_DBUS ?= "-qdbus"
QT_MULTIMEDIA ?= "${@base_contains('DISTRO_FEATURES', 'pulseaudio', '-pulseaudio', '-no-pulseaudio', d)}"
QT_MODULE_FLAGS ?= ""
QT_NAS ?= "-no-nas-sound"
QT_NIS ?= "-no-nis"
QT_CUPS ?= "-no-cups"
QT_STL ?= "-stl"
QT_SYSTEM_LIBS ?= "-system-libjpeg -system-libpng -system-zlib"
QT_TESTS ?= "-nomake tests"
QT_EXAMPLES ?= "-nomake examples"
QT_DEMOS ?= "-nomake demos"

QT_CONFIG_FLAGS += " \
  -release \
  -reduce-relocations \
  -shared \
  -silent \
  -glib \
  -no-pch \
  -no-rpath \
  -no-fast \
  -pkg-config \
  ${QT_SYSTEM_LIBS} \
  ${QT_NIS} \
  ${QT_CUPS} \
  ${QT_SQL_DRIVER_FLAGS} \
  ${QT_DISTRO_FLAGS} \
  ${QT_MODULE_FLAGS} \
  ${QT_GLFLAGS} \
  ${QT_TESTS} \
  ${QT_EXAMPLES} \
  ${QT_DEMOS} \
"

EXTRA_ENV = 'QMAKE="${STAGING_BINDIR_NATIVE}/qmake -d -after \
             INCPATH+=${STAGING_INCDIR}/freetype2 LIBS+=-L${STAGING_LIBDIR}" \
             LINK="${CXX} -Wl,-rpath-link,${STAGING_LIBDIR}" \
             AR="${TARGET_PREFIX}ar cqs" \
             MOC="${STAGING_BINDIR_NATIVE}/moc" UIC="${STAGING_BINDIR_NATIVE}/uic" MAKE="make -e ${PARALLEL_MAKE}"'

export QT_CONF_PATH="${S}/qt.conf"

do_configure() {
    set_endian

    if [ ! -e bin/qmake ]; then
        ln -sf ${STAGING_BINDIR_NATIVE}/qmake bin/qmake
    fi

    # Avoid problems with the linkers, since we want the linker to be g++
    unset LD
}

do_compile() {

    unset CFLAGS CXXFLAGS AR

    oe_runmake ${EXTRA_ENV}
}

do_install() {
    oe_runmake install INSTALL_ROOT=${D}
}

FILES_${PN}-dev += " \
        ${libdir}/cmake/* \
        ${libdir}/*.prl \
        ${includedir}/qt5/* \
        ${datadir}/qt5/* \
"

PACKAGES_DYNAMIC = "${QT_BASE_NAME}-plugin-*"

python populate_packages_prepend () {

        packages = []
        # Package all the plugins and their -dbg version and create a meta package
        def qtopia_split(path, name, glob):
                """
                Split the package into a normal and -dbg package and then add the
                new packages to the meta package.
                """
                plugin_dir = d.expand('${libdir}/${QT_DIR_NAME}/plugins/%s/' % path)
                if not os.path.exists("%s%s" % (d.expand('${D}'), plugin_dir)):
                        bb.note("The path does not exist:", d.expand('${D}'), plugin_dir)
                        return

                plugin_name = d.expand('${QT_BASE_NAME}-plugin-%s-%%s' % name)
                dev_packages = []
                dev_hook = lambda file,pkg,b,c,d:dev_packages.append((file,pkg))
                do_split_packages(d, plugin_dir, glob, plugin_name, '${PN} %s for %%s' % name, extra_depends='', hook=dev_hook)
                # Create a -dbg package as well
                plugin_dir_dbg = d.expand('${libdir}/${QT_DIR_NAME}/plugins/%s/.debug' % path)
                packages = d.getVar('PACKAGES')
                for (file,package) in dev_packages:
                        packages = "%s %s-dbg" % (packages, package)
                        file_name = os.path.join(plugin_dir_dbg, os.path.basename(file))
                        d.setVar("FILES_%s-dbg" % package, file_name)
                        d.setVar("DESCRIPTION_%s-dbg" % package, "${PN} %s for %s" % (name, package))

                d.setVar('PACKAGES', packages)

        qtopia_split('imageformats',          'imageformat',          '^libq(.*)\.so$')
        qtopia_split('sqldrivers',            'sqldriver',            '^libq(.*)\.so$')
        qtopia_split('bearer',                'bearer',               '^libq(.*)\.so$')
        qtopia_split('platforms',             'platform',             '^libq(.*)\.so$')
        qtopia_split('platforminputcontexts', 'platforminputcontext', '^lib(.*)platforminputcontextplugin\.so$')
        qtopia_split('generic',               'generic',              '^libq(.*)\.so$')
        qtopia_split('iconengines',           'iconengine',           '^libq(.*)icon\.so$')
        qtopia_split('mediaservice',          'mediaservice',         '^lib(.*)\.so$')
        qtopia_split('playlistformats',       'playlistformat',       '^libqtmultimedia_(.*)\.so$')
        qtopia_split('qmltooling',            'qmltooling',           '^libqmldbg_(.*)\.so$')
}

