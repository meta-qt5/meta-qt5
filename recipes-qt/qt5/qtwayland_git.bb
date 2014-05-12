require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.2.1

SRCREV = "f9ebbd6c618488f9b671f5504528ced3350754fb"

SRC_URI += " \
    file://0001-examples.pro-include-server-buffer-only-when-buildin.patch \
"
