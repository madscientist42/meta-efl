SUMMARY = "EFL based photo display/manipulation/editor tool"
LICENSE = "BSD"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS:${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/enlightenment/ephoto.git;protocol=https \
    file://fix_meson_build_for_oe.patch \
	"

SRCREV = "4b71bb9e1b8bea28b8c7c2e7a64eea810bc142b5"
PV = "git+${SRCPV}"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b38a3b30bb84860010f6658bd0515272 \
    file://COPYING.thumbnailer;md5=a2bd1b78fe4bf5d4bf9018587d03bb5c \
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
