require qt5-git.inc
require ${PN}.inc

QT_MODULE_BRANCH = "dev"

# qtsystems wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# qt5-git PV is only to indicate that this recipe is compatible with qt5 5.4

SRCREV = "37b614abbfb35d06a57e5b0824249c3abd5640e3"
