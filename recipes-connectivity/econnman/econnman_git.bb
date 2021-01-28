SUMMARY = "EFL based GUI for Connman"
LICENSE = "GPLv3"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS_${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus python3 python3-configargparse"

SRC_URI = " \
    git://git.enlightenment.org/apps/econnman.git;protocol=https \
	"

SRCREV = "18e7be6bf80df6b86965ba93391b205339fc7267"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6 \
    "

inherit autotools pkgconfig

S = "${WORKDIR}/git"


# For now, turn off PAM, consolekit, etc. until we can gracefully add packageconfig knobs for this stuff.
EXTRA_OECONF = " \
    --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    "


