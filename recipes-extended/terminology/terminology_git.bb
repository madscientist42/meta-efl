SUMMARY = "EFL Terminal Emulator"
LICENSE = "BSD"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS_${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/apps/terminology.git;protocol=https \
	"

SRCREV = "v1.4.0"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=3b2305533179f49bbe7fd6498b3a7e62 \
    "

inherit meson pkgconfig 

S = "${WORKDIR}/git"

EXTRA_OEMESON = " \
    -Dedje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    "

FILES_${PN} = " \
    /usr/bin \
    /usr/share \
    "
