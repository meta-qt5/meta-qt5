require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.2.1

SRCREV = "573d0ee5ba86d99095f217ea9e19172bfc5e75fd"

SRC_URI += " \
    file://0001-xcomposite-glx-Fix-build-on-Qt-5.2.1.patch \
    file://0001-examples.pro-include-server-buffer-only-when-buildin.patch \
"
