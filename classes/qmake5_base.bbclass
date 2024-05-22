# hardcode linux, because that's what 0001-Add-linux-oe-g-platform.patch adds
XPLATFORM:toolchain-clang = "linux-oe-clang"
XPLATFORM ?= "linux-oe-g++"

OE_QMAKE_PLATFORM_NATIVE = "${XPLATFORM}"
OE_QMAKE_PLATFORM = "${XPLATFORM}"

# Add -d to show debug output from every qmake call, but it prints *a lot*, better to add it only to debugged recipe
OE_QMAKE_DEBUG_OUTPUT ?= ""

# Look through supplied directories recursively by default
OE_QMAKE_RECURSIVE ?= "-r"

# Paths in .prl files contain SYSROOT value
SSTATE_SCAN_FILES += "*.pri *.prl *.prf"

# drop default -e and add needed OE_QMAKE vars explicitly
# the problem is that when generated Makefile has:
# CFLAGS = -pipe $(OE_QMAKE_CFLAGS) -O2 -pthread -D_REENTRANT -Wall -W -fPIC $(DEFINES)
# then OE_QMAKE_CFLAGS are exported and used correctly, but then whole CFLAGS is overwritten from env (and -fPIC lost and build fails)
EXTRA_OEMAKE = " \
    MAKEFLAGS='${PARALLEL_MAKE}' \
    OE_QMAKE_CC='${OE_QMAKE_CC}' \
    OE_QMAKE_CXX='${OE_QMAKE_CXX}' \
    OE_QMAKE_CFLAGS='${OE_QMAKE_CFLAGS}' \
    OE_QMAKE_CXXFLAGS='${OE_QMAKE_CXXFLAGS}' \
    OE_QMAKE_LINK='${OE_QMAKE_LINK}' \
    OE_QMAKE_LDFLAGS='${OE_QMAKE_LDFLAGS}' \
    OE_QMAKE_AR='${OE_QMAKE_AR}' \
    OE_QMAKE_OBJCOPY='${OE_QMAKE_OBJCOPY}' \
    OE_QMAKE_STRIP='${OE_QMAKE_STRIP}' \
    OE_QMAKE_INCDIR_QT='${STAGING_DIR_TARGET}/${OE_QMAKE_PATH_HEADERS}' \
"

OE_QMAKE_QMAKE = "${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}/qmake"
export OE_QMAKE_CC = "${CC}"
export OE_QMAKE_CFLAGS = "${CFLAGS}"
export OE_QMAKE_CXX = "${CXX}"
export OE_QMAKE_CXXFLAGS = "${CXXFLAGS}"
export OE_QMAKE_LINK = "${CXX}"
export OE_QMAKE_LDFLAGS = "${LDFLAGS}"
export OE_QMAKE_AR = "${AR}"
export OE_QMAKE_OBJCOPY = "${OBJCOPY}"
export OE_QMAKE_STRIP = "echo"

# qmake reads if from shell environment
export OE_QMAKE_QTCONF_PATH = "${WORKDIR}/qt.conf"

inherit qmake5_paths

generate_target_qt_config_file() {
    qtconf="$1"
    cat > "$qtconf" <<EOF
[Paths]
Prefix = ${OE_QMAKE_PATH_PREFIX}
Headers = ${OE_QMAKE_PATH_HEADERS}
Libraries = ${OE_QMAKE_PATH_LIBS}
ArchData = ${OE_QMAKE_PATH_ARCHDATA}
Data = ${OE_QMAKE_PATH_DATA}
Binaries = ${OE_QMAKE_PATH_BINS}
LibraryExecutables = ${OE_QMAKE_PATH_LIBEXECS}
Plugins = ${OE_QMAKE_PATH_PLUGINS}
Qml2Imports = ${OE_QMAKE_PATH_QML}
Translations = ${OE_QMAKE_PATH_TRANSLATIONS}
Documentation = ${OE_QMAKE_PATH_DOCS}
Settings = ${OE_QMAKE_PATH_SETTINGS}
Examples = ${OE_QMAKE_PATH_EXAMPLES}
Tests = ${OE_QMAKE_PATH_TESTS}
HostBinaries = ${OE_QMAKE_PATH_BINS}
HostData = ${OE_QMAKE_PATH_ARCHDATA}
HostLibraries = ${OE_QMAKE_PATH_LIBS}
HostSpec = ${OE_QMAKE_PLATFORM}
TargetSpec = ${OE_QMAKE_PLATFORM}
ExternalHostBinaries = ${OE_QMAKE_PATH_BINS}
Sysroot =
EOF
}

do_generate_qt_config_file() {
    generate_qt_config_file_paths
    generate_qt_config_file_effective_paths
}

generate_qt_config_file_paths() {
    cat > ${OE_QMAKE_QTCONF_PATH} <<EOF
[Paths]
Prefix = ${OE_QMAKE_PATH_PREFIX}
Headers = ${OE_QMAKE_PATH_HEADERS}
Libraries = ${OE_QMAKE_PATH_LIBS}
ArchData = ${OE_QMAKE_PATH_ARCHDATA}
Data = ${OE_QMAKE_PATH_DATA}
Binaries = ${OE_QMAKE_PATH_BINS}
LibraryExecutables = ${OE_QMAKE_PATH_LIBEXECS}
Plugins = ${OE_QMAKE_PATH_PLUGINS}
Qml2Imports = ${OE_QMAKE_PATH_QML}
Translations = ${OE_QMAKE_PATH_TRANSLATIONS}
Documentation = ${OE_QMAKE_PATH_DOCS}
Settings = ${OE_QMAKE_PATH_SETTINGS}
Examples = ${OE_QMAKE_PATH_EXAMPLES}
Tests = ${OE_QMAKE_PATH_TESTS}
HostBinaries = ${OE_QMAKE_PATH_HOST_BINS}
HostData = ${OE_QMAKE_PATH_HOST_DATA}
HostLibraries = ${OE_QMAKE_PATH_HOST_LIBS}
HostSpec = ${OE_QMAKE_PLATFORM_NATIVE}
TargetSpec = ${OE_QMAKE_PLATFORM}
ExternalHostBinaries = ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}
Sysroot = ${STAGING_DIR_TARGET}
EOF
}

generate_qt_config_file_effective_paths() {
    cat >> ${OE_QMAKE_QTCONF_PATH} <<EOF
[EffectivePaths]
HostBinaries = ${OE_QMAKE_PATH_EXTERNAL_HOST_BINS}
HostLibraries = ${STAGING_LIBDIR_NATIVE}
HostData = ${OE_QMAKE_PATH_HOST_DATA}
HostPrefix = ${STAGING_DIR_NATIVE}
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

# qmake works fine with separate B, use it by default
SEPB = "${WORKDIR}/build"
B = "${SEPB}"

CONFIGURESTAMPFILE = "${WORKDIR}/qmake5_base_configure.sstate"

qmake5_base_preconfigure() {
        if [ -n "${CONFIGURESTAMPFILE}" -a -e "${CONFIGURESTAMPFILE}" ]; then
                if [ "`cat ${CONFIGURESTAMPFILE}`" != "${BB_TASKHASH}" -a "${S}" != "${B}" ]; then
                        echo "Previously configured separate build directory detected, cleaning ${B}"
                        rm -rf ${B}
                        mkdir ${B}
                fi
        fi
}

qmake5_base_postconfigure(){
        if [ -n "${CONFIGURESTAMPFILE}" ]; then
                echo ${BB_TASKHASH} > ${CONFIGURESTAMPFILE}
        fi
}

EXTRAQCONFFUNCS ??= ""

do_configure[prefuncs] += "qmake5_base_preconfigure ${EXTRAQCONFFUNCS}"
do_configure[postfuncs] += "qmake5_base_postconfigure"

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

    if [ ! -z "${EXTRA_QMAKEVARS_CONFIGURE}" ]; then
        QMAKE_VARSUBST_CONFIGURE="${EXTRA_QMAKEVARS_CONFIGURE}"
        bbnote "qmake configure substitution: '${EXTRA_QMAKEVARS_CONFIGURE}'"
    fi

    # for config.tests to read this
    export QMAKE_MAKE_ARGS="${EXTRA_OEMAKE}"

    CMD="${OE_QMAKE_QMAKE} -makefile -o Makefile ${OE_QMAKE_DEBUG_OUTPUT} ${OE_QMAKE_RECURSIVE} $QMAKE_VARSUBST_PRE $AFTER $PROFILES $QMAKE_VARSUBST_POST -- $QMAKE_VARSUBST_CONFIGURE"
    ${OE_QMAKE_QMAKE} -makefile -o Makefile ${OE_QMAKE_DEBUG_OUTPUT} ${OE_QMAKE_RECURSIVE} $QMAKE_VARSUBST_PRE $AFTER $PROFILES $QMAKE_VARSUBST_POST -- $QMAKE_VARSUBST_CONFIGURE || die "Error calling $CMD"
}

qmake5_base_native_do_install() {
    oe_runmake install INSTALL_ROOT=${D}
    find "${D}" -ignore_readdir_race -name "*.la" -delete
    if ls ${D}${libdir}/pkgconfig/Qt5*.pc >/dev/null 2>/dev/null; then
        sed -i "s@-L${STAGING_LIBDIR}@-L\${libdir}@g" ${D}${libdir}/pkgconfig/Qt5*.pc
    fi
}

qmake5_base_fix_install() {
    STAGING_PATH=$1
    if [ -d ${D}${STAGING_PATH} ] ; then
        echo "Some files are installed in wrong directory ${D}${STAGING_PATH}"
        cp -ra ${D}${STAGING_PATH}/* ${D}
        rm -rf ${D}${STAGING_PATH}
        # remove empty dirs
        TMP=`dirname ${D}${STAGING_PATH}`
        while test $TMP != ${D}; do
            rmdir $TMP
            TMP=`dirname $TMP`;
        done
    fi
}

qmake5_base_do_install() {
    # Fix install paths for all
    find . -name "Makefile*" | xargs -r sed -i "s,(INSTALL_ROOT)${STAGING_DIR_TARGET},(INSTALL_ROOT),g"
    find . -name "Makefile*" | xargs -r sed -i "s,(INSTALL_ROOT)${STAGING_DIR_HOST},(INSTALL_ROOT),g"
    find . -name "Makefile*" | xargs -r sed -i "s,(INSTALL_ROOT)${STAGING_DIR_NATIVE},(INSTALL_ROOT),g"

    oe_runmake install INSTALL_ROOT=${D}

    # everything except HostData and HostBinaries is prefixed with sysroot value,
    # but we cannot remove sysroot override, because that's useful for pkg-config etc
    # concurrent builds may cause qmake to regenerate Makefiles and override the above
    # sed changes. If that happens, move files manually to correct location.
    qmake5_base_fix_install ${STAGING_DIR_TARGET}
    qmake5_base_fix_install ${STAGING_DIR_HOST}
    qmake5_base_fix_install ${STAGING_DIR_NATIVE}

    # Replace host paths with qmake built-in properties
    find ${D} \( -name "*.pri" -or -name "*.prl" \) -exec \
        sed -i -e 's|${STAGING_DIR_NATIVE}|$$[QT_HOST_PREFIX/get]|g' \
            -e 's|${STAGING_DIR_HOST}|$$[QT_SYSROOT]|g' {} \
            -e '/QMAKE_PRL_BUILD_DIR/d' {} \;

    # Replace host paths with pkg-config built-in variable
    find ${D} -name "*.pc" -exec \
        sed -i -e 's|prefix=${STAGING_DIR_HOST}|prefix=|g' \
            -e 's|${STAGING_DIR_HOST}|${pc_sysrootdir}|g' {} \;

    # Replace resolved lib path with the lib name
    find ${D} -name "*.cmake" -exec \
        sed -i -e 's@/[^;]*/lib\([^;]*\)\.\(so\|a\)@\1@g' {} \;

}
