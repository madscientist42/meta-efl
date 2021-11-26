SUMMARY = "EFL Terminal Emulator"
LICENSE = "BSD"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS:${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/apps/rage.git;protocol=https \
	"

SRCREV = "52943e5ec7275a1dc8190cc44a1293414ba02619"
PV = "git+${SRCPV}"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=22b8ca1483549618ee95058bb1e79268 \
    "

inherit meson pkgconfig mime-xdg

S = "${WORKDIR}/git"

EXTRA_OEMESON = " \
    -Dedje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    "

FILES:${PN} = " \
    /usr/bin \
    /usr/lib \
    /usr/share \
    "
