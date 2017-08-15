inherit qmake5

require qwt-qt5.inc

SRC_URI[qwt.md5sum] = "19d1f5fa5e22054d22ee3accc37c54ba"
SRC_URI[qwt.sha256sum] = "f3ecd34e72a9a2b08422fb6c8e909ca76f4ce5fa77acad7a2883b701f4309733"

RPROVIDES_${PN}-dev = "libqwt-qt5-dev"
