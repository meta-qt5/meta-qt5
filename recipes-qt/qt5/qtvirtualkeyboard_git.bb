require qt5.inc
require qt5-git.inc

LICENSE = "GPL-3.0 | The-Qt-Company-Commercial"
LIC_FILES_CHKSUM = " \
    file://LICENSE.GPL3;md5=d32239bcb673463ab874e80d47fae504 \
"

# To enabled Nuance T9 Write support, you need to provide the licensed components
# and enable "t9write" in PACKAGECONFIG. This can be done in a separate .bbappend file.
# for example:
#T9WRITEPACKAGE = "${HOME}/Downloads/zzEval_QT_T9Write_Alpha_v750_20150916.zip"
#SRC_URI += "file://${T9WRITEPACKAGE};subdir=git/src/virtualkeyboard/3rdparty/t9write"
#PACKAGECONFIG = "t9write lang-all"

PACKAGECONFIG ?= "lang-all lipi-toolkit qtquickcompiler"
PACKAGECONFIG[hunspell] = ",CONFIG+=disable-hunspell,hunspell"
PACKAGECONFIG[t9write] = "CONFIG+=t9write"
PACKAGECONFIG[lipi-toolkit] = "CONFIG+=lipi-toolkit"
PACKAGECONFIG[lang-all] = "CONFIG+=lang-all"
PACKAGECONFIG[lang-ar_AR] = "CONFIG+=lang-ar_AR"
PACKAGECONFIG[lang-da_DK] = "CONFIG+=lang-da_DK"
PACKAGECONFIG[lang-de_DE] = "CONFIG+=lang-de_DE"
PACKAGECONFIG[lang-en_GB] = "CONFIG+=lang-en_GB"
PACKAGECONFIG[lang-es_ES] = "CONFIG+=lang-es_ES"
PACKAGECONFIG[lang-fa_FA] = "CONFIG+=lang-fa_FA"
PACKAGECONFIG[lang-fi_FI] = "CONFIG+=lang-fi_FI"
PACKAGECONFIG[lang-fr_FR] = "CONFIG+=lang-fr_FR"
PACKAGECONFIG[lang-hi_IN] = "CONFIG+=lang-hi_IN"
PACKAGECONFIG[lang-it_IT] = "CONFIG+=lang-it_IT"
PACKAGECONFIG[lang-ja_JP] = "CONFIG+=lang-ja_JP"
PACKAGECONFIG[lang-ko_KR] = "CONFIG+=lang-ko_KR"
PACKAGECONFIG[lang-nb_NO] = "CONFIG+=lang-nb_NO"
PACKAGECONFIG[lang-pl_PL] = "CONFIG+=lang-pl_PL"
PACKAGECONFIG[lang-pt_PT] = "CONFIG+=lang-pt_PT"
PACKAGECONFIG[lang-ro_RO] = "CONFIG+=lang-ro_RO"
PACKAGECONFIG[lang-ru_RU] = "CONFIG+=lang-ru_RU"
PACKAGECONFIG[lang-sv_SE] = "CONFIG+=lang-sv_SE"
PACKAGECONFIG[lang-zh_CN] = "CONFIG+=lang-zh_CN"
PACKAGECONFIG[lang-zh_TW] = "CONFIG+=lang-zh_TW"

EXTRA_QMAKEVARS_PRE += "${PACKAGECONFIG_CONFARGS}"
EXTRA_QMAKEVARS_PRE += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'CONFIG+=disable-desktop', d)}"

PACKAGES += "${PN}-dictionaries"
RRECOMMENDS_${PN} += "${PN}-dictionaries"
FILES_${PN}-dictionaries = "${OE_QMAKE_PATH_DATA}/qtvirtualkeyboard/*/*.dat"
FILES_${PN} += "${OE_QMAKE_PATH_DATA}/qtvirtualkeyboard/lipi_toolkit"

DEPENDS += "qtbase qtdeclarative qtmultimedia qtquickcontrols qtsvg qtxmlpatterns qtdeclarative-native"

SRCREV = "a8cab520dc7bedff73ec60a5a189b21cdf01f083"
