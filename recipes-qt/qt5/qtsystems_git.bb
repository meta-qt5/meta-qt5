require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# # qt5-git PV is only to indicate that this recipe is compatible with qt5 5.2.0

QT_MODULE_BRANCH = "dev"

SRCREV = "d6104a92321c2e72b140156fddf0378c9795cdb4"
