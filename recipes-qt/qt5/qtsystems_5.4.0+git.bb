require qt5-git.inc
require ${PN}.inc

# this wasn't released, the PV is set just to signify that this SRCREV was tested together
# with 5.4.0 version of other modules
PV = "5.4.0"
DEFAULT_PREFERENCE = "1"

QT_MODULE_BRANCH = "dev"

# qtsystems wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.4.0

SRCREV = "44f70d99a2ecd5f3f320650461f1d69142d11bcc"
