SUMMARY = "EFL based screenshot tool"
LICENSE = "MIT"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS_${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/apps/emprint.git;protocol=https \
	"

SRCREV = "a0886e1a13d431e3bed27492696006100173e81b"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=344895f253c32f38e182dcaf30fe8a35 \
    file://COPYING-PLAIN;md5=e01359041001e8bf24c09acca556e792 \
    "

inherit autotools pkgconfig 

S = "${WORKDIR}/git"


# For now, turn off PAM, consolekit, etc. until we can gracefully add packageconfig knobs for this stuff.
EXTRA_OECONFIG = " \
    --with-edje_cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    "


