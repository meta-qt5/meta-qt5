require qt5-git.inc
require ${PN}.inc

# qtwayland wasn't released yet, last tag before this SRCREV is 5.0.0-beta1
# this PV is only to indicate that this recipe is compatible with qt5 5.1.0
PV = "5.1.0+git${SRCPV}"

SRC_URI += "file://fix.missing.v8.public.api.patch"

# newer revisions depend on newer wayland-1.1 APIs
SRCREV = "87dba733acfddecd8562e8e26ce5f994aa499fe3"
# SRCREV = "ede872db1cdfdc2810c2dd29edd5fb6e1cdac0f5"
