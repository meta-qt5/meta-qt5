Qt5 OpenEmbedded/Yocto Project layer
====================================

This layer depends on:

URI: https://github.com/openembedded/openembedded-core (branch: master)

When building stuff like `qtdeclarative`, `qtquick`, `qtwebkit`, make
sure that you have required `PACKAGECONFIG` options enabled in qtbase
build, see `qtbase` recipe for detail.

Some recipes like qtwebengine would need 32bit multilib compiler on build
host, especially when target to be built is 32bit, e.g. arm since it builds
v8 engine which requires `$CC -m32` to work, so ensure that host compiler
can generate 32bit code, on archlinux distributions this would be
```
pacman -S lib32-gcc-libs lib32-glibc
```

Contributing
------------

Please submit any patches against the `meta-qt5` layer by using the
GitHub pull-request feature.  Fork the repo, make a branch, do the
work, rebase from upstream, create the pull request, yada-yada.

Maintainers
-----------

- Martin 'JaMa' Jansa <martin.jansa@gmail.com>
- Otavio Salvador <otavio@ossystems.com.br>

Yocto Project Compatible
------------------------

![Yocto Project Compatible](https://github.com/meta-qt5/meta-qt5/blob/master/files/YoctoProject_Badge_Compatible.png)

meta-qt5 has Yocto Project Compatible status since 2013. Check it at:
https://www.yoctoproject.org/product/meta-qt5
