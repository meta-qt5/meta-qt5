# This is useful for target recipes to reference native mkspecs
QMAKE_MKSPEC_PATH_NATIVE = "${STAGING_LIBDIR_NATIVE}/${QT_DIR_NAME}"
QMAKE_MKSPEC_PATH_TARGET = "${STAGING_LIBDIR}/${QT_DIR_NAME}"

QMAKE_MKSPEC_PATH = "${QMAKE_MKSPEC_PATH_TARGET}"
QMAKE_MKSPEC_PATH_class-native = "${QMAKE_MKSPEC_PATH_NATIVE}"

# hardcode linux, because that's what 0001-Add-linux-oe-g-platform.patch adds
OE_QMAKE_PLATFORM_NATIVE = "linux-oe-g++"
OE_QMAKE_PLATFORM = "linux-oe-g++"

# Add -d to show debug output from every qmake call, but it prints *a lot*, better to add it only to debugged recipe
OE_QMAKE_DEBUG_OUTPUT ?= ""

# Paths in .prl files contain SYSROOT value
SSTATE_SCAN_FILES += "*.pri *.prl"

# drop default -e and add needed OE_QMAKE vars explicitly
# the problem is that when generated Makefile has:
# CFLAGS = -pipe $(OE_QMAKE_CFLAGS) -O2 -pthread -D_REENTRANT -Wall -W -fPIC $(DEFINES)
# then OE_QMAKE_CFLAGS are exported and used correctly, but then whole CFLAGS is overwritten from env (and -fPIC lost and build fails)
EXTRA_OEMAKE = " \
    MAKEFLAGS='${PARALLEL_MAKE}' \
    OE_QMAKE_COMPILER='${OE_QMAKE_COMPILER}' \
    OE_QMAKE_CC='${OE_QMAKE_CC}' \
    OE_QMAKE_CXX='${OE_QMAKE_CXX}' \
    OE_QMAKE_CFLAGS='${OE_QMAKE_CFLAGS}' \
    OE_QMAKE_CXXFLAGS='${OE_QMAKE_CXXFLAGS}' \
    OE_QMAKE_LINK='${OE_QMAKE_LINK}' \
    OE_QMAKE_LDFLAGS='${OE_QMAKE_LDFLAGS}' \
    OE_QMAKE_AR='${OE_QMAKE_AR}' \
    OE_QMAKE_STRIP='${OE_QMAKE_STRIP}' \
    OE_QMAKE_WAYLAND_SCANNER='${OE_QMAKE_WAYLAND_SCANNER}' \
    OE_QMAKE_QT_CONFIG='${OE_QMAKE_QT_CONFIG}' \
"

export OE_QMAKESPEC = "${QMAKE_MKSPEC_PATH_NATIVE}/mkspecs/${OE_QMAKE_PLATFORM_NATIVE}"
export OE_XQMAKESPEC = "${QMAKE_MKSPEC_PATH}/mkspecs/${OE_QMAKE_PLATFORM}"
export OE_QMAKE_QMAKE = "${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}/qmake"
export OE_QMAKE_COMPILER = "${CC}"
export OE_QMAKE_CC = "${CC}"
export OE_QMAKE_CFLAGS = "${CFLAGS}"
export OE_QMAKE_CXX = "${CXX}"
export OE_QMAKE_CXXFLAGS = "${CXXFLAGS}"
export OE_QMAKE_LINK = "${CXX}"
export OE_QMAKE_LDFLAGS = "${LDFLAGS}"
export OE_QMAKE_AR = "${AR}"
export OE_QMAKE_STRIP = "echo"
export OE_QMAKE_WAYLAND_SCANNER = "${STAGING_BINDIR_NATIVE}/wayland-scanner"
export QT_CONF_PATH = "${WORKDIR}/qt.conf"
export QT_DIR_NAME ?= "qt5"

OE_QMAKE_PATH_PREFIX = "${prefix}"
OE_QMAKE_PATH_HEADERS = "${includedir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_LIBS = "${libdir}"
OE_QMAKE_PATH_ARCHDATA = "${libdir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_DATA = "${datadir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_BINS = "${bindir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_LIBEXECS = "${libdir}/${QT_DIR_NAME}/libexec"
OE_QMAKE_PATH_PLUGINS = "${libdir}/${QT_DIR_NAME}/plugins"
OE_QMAKE_PATH_IMPORTS = "${libdir}/${QT_DIR_NAME}/imports"
OE_QMAKE_PATH_QML = "${libdir}/${QT_DIR_NAME}/qml"
OE_QMAKE_PATH_TRANSLATIONS = "${datadir}/${QT_DIR_NAME}/translations"
OE_QMAKE_PATH_DOCS = "${datadir}/${QT_DIR_NAME}/doc"
OE_QMAKE_PATH_SETTINGS = "${sysconfdir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_EXAMPLES = "${datadir}/${QT_DIR_NAME}/examples"
OE_QMAKE_PATH_TESTS = "${datadir}/${QT_DIR_NAME}/tests"
OE_QMAKE_PATH_HOST_PREFIX = ""
OE_QMAKE_PATH_HOST_BINS = "${bindir}/${QT_DIR_NAME}"
OE_QMAKE_PATH_HOST_DATA = "${QMAKE_MKSPEC_PATH_TARGET}"
OE_QMAKE_PATH_EXTERNAL_HOST_BINS = "${STAGING_BINDIR_NATIVE}/${QT_DIR_NAME}"

# do not export STRIP to the environment
STRIP[unexport] = "1"

do_generate_qt_config_file() {
    cat > ${WORKDIR}/qt.conf <<EOF
[Paths]
Prefix = ${OE_QMAKE_PATH_PREFIX}
Headers = ${OE_QMAKE_PATH_HEADERS}
Libraries = ${OE_QMAKE_PATH_LIBS}
ArchData = ${OE_QMAKE_PATH_ARCHDATA}
Data = ${OE_QMAKE_PATH_DATA}
Binaries = ${OE_QMAKE_PATH_BINS}
LibraryExecutables = ${OE_QMAKE_PATH_LIBEXECS}
Plugins = ${OE_QMAKE_PATH_PLUGINS}
Imports = ${OE_QMAKE_PATH_IMPORTS}
Qml2Imports = ${OE_QMAKE_PATH_QML}
Translations = ${OE_QMAKE_PATH_TRANSLATIONS}
Documentation = ${OE_QMAKE_PATH_DOCS}
Settings = ${OE_QMAKE_PATH_SETTINGS}
Examples = ${OE_QMAKE_PATH_EXAMPLES}
Tests = ${OE_QMAKE_PATH_TESTS}
HostBinaries = ${OE_QMAKE_PATH_HOST_BINS}
HostData = ${OE_QMAKE_PATH_HOST_DATA}
HostSpec = ${OE_QMAKESPEC}
TartgetSpec = ${OE_XQMAKESPEC} 
ExternalHostBinaries = ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}
Sysroot = ${STAGING_DIR_TARGET}
EOF
}
#
# Allows to override following values (as in version 5.0.1)
# Prefix The default prefix for all paths.
# Documentation The location for documentation upon install.
# Headers The location for all headers.
# Libraries The location of installed libraries.
# LibraryExecutables The location of installed executables required by libraries at runtime.
# Binaries The location of installed Qt binaries (tools and applications).
# Plugins The location of installed Qt plugins.
# Imports The location of installed QML extensions to import (QML 1.x).
# Qml2Imports The location of installed QML extensions to import (QML 2.x).
# ArchData The location of general architecture-dependent Qt data.
# Data The location of general architecture-independent Qt data.
# Translations The location of translation information for Qt strings.
# Examples The location for examples upon install.
# Tests The location of installed Qt testcases.
# Settings The location for Qt settings. Not applicable on Windows.

# For bootstrapped
# Sysroot The location of target sysroot
# HostPrefix The prefix for host tools when cross compiling (building tools for both systems)
# HostBinaries The location where to install host tools
# HostData The location where to install host data
# ExternalHostBinaries The location where we already have host tools (when cross compiling, but reusing existing tools)
# TargetSpec The location where to install target mkspec
# HostSpec The location where to install host mkspec

addtask generate_qt_config_file after do_patch before do_configure

qmake5_base_do_configure () {
    if [ -z "${QMAKE_PROFILES}" ]; then
        PROFILES="`ls ${S}/*.pro`"
    else
        PROFILES="${QMAKE_PROFILES}"
        bbnote "qmake using profiles: '${QMAKE_PROFILES}'"
    fi

    if [ ! -z "${EXTRA_QMAKEVARS_POST}" ]; then
        AFTER="-after"
        QMAKE_VARSUBST_POST="${EXTRA_QMAKEVARS_POST}"
        bbnote "qmake postvar substitution: '${EXTRA_QMAKEVARS_POST}'"
    fi

    if [ ! -z "${EXTRA_QMAKEVARS_PRE}" ]; then
        QMAKE_VARSUBST_PRE="${EXTRA_QMAKEVARS_PRE}"
        bbnote "qmake prevar substitution: '${EXTRA_QMAKEVARS_PRE}'"
    fi

    CMD="${OE_QMAKE_QMAKE} -makefile -o Makefile ${OE_QMAKE_DEBUG_OUTPUT} -r $QMAKE_VARSUBST_PRE $AFTER $PROFILES $QMAKE_VARSUBST_POST"
    $CMD || die "Error calling $CMD"
}

qmake5_base_do_install() {
    # Fix install paths for all
    find -name "Makefile*" | xargs sed -i "s,(INSTALL_ROOT)${STAGING_DIR_TARGET},(INSTALL_ROOT),g"

    oe_runmake install INSTALL_ROOT=${D}

    # everything except HostData and HostBinaries is prefixed with sysroot value,
    # but we cannot remove sysroot override, because that's useful for pkg-config etc
    # In some cases like QtQmlDevTools in qtdeclarative, the sed above does not work,
    # fix them manually
    if [ -d ${D}${STAGING_DIR_TARGET} ] ; then
        echo "Some files are installed in wrong directory ${D}${STAGING_DIR_TARGET}"
        cp -ra ${D}${STAGING_DIR_TARGET}/* ${D}
        rm -rf ${D}${STAGING_DIR_TARGET}
        # remove empty dirs
        TMP=`dirname ${D}/${STAGING_DIR_TARGET}`
        while test ${TMP} != ${D}; do
            rmdir ${TMP}
            TMP=`dirname ${TMP}`;
        done
    fi
}
