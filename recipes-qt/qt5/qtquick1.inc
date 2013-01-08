require qt5module.inc

DEPENDS += "qtscript"

INC_PR = "r0"

FILES_${PN}-plugins += " \
            ${libdir}/qt5/imports/builtins.qmltypes \
            ${libdir}/qt5/imports/Qt/labs/*/qmldir \
            ${libdir}/qt5/imports/Qt/labs/*/plugins.qmltypes \
            ${libdir}/qt5/imports/Qt/labs/*/*${SOLIBSDEV} \
"

FILES_${PN}-plugins-dbg += "${libdir}/qt5/imports/Qt/labs/*/.debug/*"
