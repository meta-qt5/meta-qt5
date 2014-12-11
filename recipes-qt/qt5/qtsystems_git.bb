require qt5-git.inc
require ${PN}.inc

QT_MODULE_BRANCH = "dev"

# qtsystems wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.4

SRCREV = "3a0216301ad1338126abb326e8e4c22c6ea12f40"
