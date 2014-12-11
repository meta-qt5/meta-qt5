require qt5-git.inc
require ${PN}.inc

# prepend this again, because ${PN}.inc prepneds ${PN}
FILESEXTRAPATHS =. "${FILE_DIRNAME}/${BPN}-git:"

SRCREV = "f3abe81d510bef5b984544a6d58551ce04cb8b48"
