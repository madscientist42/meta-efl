DESCRIPTION = "Terminology-ish EFL IRC app"
HOMEPAGE = "http://git.enlightenment.org/apps/express.git/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=693773c917d343da2c977412a79b11dc"

DEPENDS = " \
    efl-native \
    efl \
    "

SRC_URI = " \
    git://git.enlightenment.org/enlightenment/express.git;protocol=https;branch=master \
    "

SRCREV = "124bfd68b8851968a9f6dc48d47868408338bdc4"
S = "${WORKDIR}/git"

inherit meson pkgconfig

FILES:${PN} += " \
    /usr/share \
    "
