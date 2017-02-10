# Copyright (C) 2014 O.S. Systems Software LTDA.

inherit populate_sdk_qt5_base

TOOLCHAIN_HOST_TASK_append = " nativesdk-packagegroup-qt5-toolchain-host"
TOOLCHAIN_TARGET_TASK_append = " packagegroup-qt5-toolchain-target"

FEATURE_PACKAGES_qtcreator-debug = "packagegroup-qt5-qtcreator-debug"
