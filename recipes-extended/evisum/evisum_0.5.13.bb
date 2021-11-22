SUMMARY = "EFL Terminal Emulator"
LICENSE = "BSD"
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
    git://git.enlightenment.org/apps/evisum.git;protocol=https \
	"

SRCREV = "v${PV}"
S = "${WORKDIR}/git"

inherit meson pkgconfig

FILES:${PN} += " \
    /usr/share/icons \
    "
