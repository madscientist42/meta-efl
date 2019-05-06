SUMMARY = "EFL based photo display/manipulation/editor tool"
LICENSE = "BSD"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS_${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/apps/ephoto.git;protocol=https \
    file://fix_meson_build_for_oe.patch \
	"

SRCREV = "d0ec29d753f01abcbe4dc355b8817401784daa70"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b38a3b30bb84860010f6658bd0515272 \
    file://COPYING.thumbnailer;md5=a2bd1b78fe4bf5d4bf9018587d03bb5c \
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
