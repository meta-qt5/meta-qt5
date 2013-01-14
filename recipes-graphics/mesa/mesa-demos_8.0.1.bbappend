FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR .= "-devonit1"

SRC_URI += "file://mesa-demos-8.0.1-missing_headers.patch"
