require qt5-git.inc
require ${PN}.inc

# prepend this again, because ${PN}.inc prepends ${PN}
FILESEXTRAPATHS =. "${FILE_DIRNAME}/${BPN}-git:"

SRCREV = "33eb6cdf9313f0ad969b4af8fc7160859c2a6319"
