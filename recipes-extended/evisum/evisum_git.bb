SUMMARY = "EFL Terminal Emulator"
LICENSE = "BSD-3-Clause"
HOMEPAGE = "https://www.enlightenment.org"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=1b382d1d39e10dce772c84459157ee1a \
    "
DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS:${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/enlightenment/evisum.git;protocol=https;branch=master \
	"

SRCREV = "38ad17b0384c965b40ff411745e93b64ce27f1aa"
S = "${WORKDIR}/git"

inherit meson pkgconfig

FILES:${PN} += " \
    /usr/share/icons \
    "
