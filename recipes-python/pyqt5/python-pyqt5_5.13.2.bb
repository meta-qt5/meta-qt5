require python-pyqt5.inc

inherit ${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-python2", "pythonnative python-dir", "", d)}

python() {
    if 'meta-python2' not in d.getVar('BBFILE_COLLECTIONS').split():
        raise bb.parse.SkipRecipe('Requires meta-python2 to be present.')
}

DEPENDS += "sip sip-native python"

RDEPENDS_${PN} += "python-core python-sip"

PNBLACKLIST[python-pyqt5] = "Sip fails in do_configure sip: QOpenGLFramebufferObject is undefined"
