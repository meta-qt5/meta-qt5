require recipes-qt/qt5/qt5.inc
require recipes-qt/qt5/qt5-git.inc

DESCRIPTION = "License-free and opensource Qt virtual keyboard"
HOMEPAGE = "https://amarula.github.io/cutekeyboard/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2f6f4d4f7d76b223f08e17122d04010f"

DEPENDS += "qtbase qtdeclarative qtmultimedia qtquickcontrols qtsvg qtxmlpatterns"

SRCREV = "e022ab3a95111bb12d07d1700d02a6f7a79fed7d"
SRC_URI = "git://github.com/amarula/cutekeyboard.git;protocol=https;branch=main"

QMAKE_PROFILES += "${S}/src/src.pro"
