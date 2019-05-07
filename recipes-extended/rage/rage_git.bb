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
    git://git.enlightenment.org/apps/rage.git;protocol=https \
	"

SRCREV = "95cb9155572e84bcb12b9b30c3b0bff3a0263b70"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=3890ab2189145a682727902e7fc644e2 \
    "

inherit meson pkgconfig 

S = "${WORKDIR}/git"

EXTRA_OEMESON = " \
    -Dedje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    "

FILES_${PN} = " \
    /usr/bin \
    /usr/lib \
    /usr/share \
    "
