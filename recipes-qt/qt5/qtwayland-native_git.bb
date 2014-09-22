require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.2.1

QT_MODULE_BRANCH = "5.4"
SRCREV = "573d0ee5ba86d99095f217ea9e19172bfc5e75fd"
