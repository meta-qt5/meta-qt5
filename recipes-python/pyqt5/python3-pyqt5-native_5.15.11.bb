require python3-pyqt5.inc

inherit native

# Add PyQt_OpenGL to the DISABLED_FEATURES even if opengl distro feature is enabled
DISABLED_FEATURES += "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'PyQt_OpenGL', '', d)}"
