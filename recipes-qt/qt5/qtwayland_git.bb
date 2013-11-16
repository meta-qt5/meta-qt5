require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.2.0

SRCREV = "3e9412e2fd91e64a565ed8ddbef76f57ca9413d5"
