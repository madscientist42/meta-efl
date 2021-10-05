SUMMARY = "EFL Based Calculator"
LICENSE = "MIT"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS:${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/apps/equate.git;protocol=https \
	"

SRCREV = "2afdce05eba77d454774fcf57dba38dc4baf3f0a"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=344895f253c32f38e182dcaf30fe8a35 \
    file://COPYING-PLAIN;md5=f59cacc08235a546b0c34a5422133035 \
    "

inherit autotools pkgconfig

S = "${WORKDIR}/git"

EXTRA_OECONFIG = " \
    --with-edje_cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    "

FILES:${PN} = " \
    /usr/bin \
    /usr/share \
    "

