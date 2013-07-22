inherit cmake
inherit qmake5_paths

EXTRA_OECMAKE += " \
    -DOE_QMAKE_PATH_EXTERNAL_HOST_BINS=${OE_QMAKE_PATH_EXTERNAL_HOST_BINS} \
"
