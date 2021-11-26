SUMMARY = "EFL Simple Text Editor"
LICENSE = "GPLv3"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS:${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/apps/ecrire.git;protocol=https \
	"

SRCREV = "ea7c0240194fe611f3bf85fe0d060b1be11e9c1c"
PV = "git+${SRCPV}"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    "

inherit meson mime-xdg

S = "${WORKDIR}/git"

FILES:${PN} = " \
    /usr/bin \
    /usr/share \
    "

