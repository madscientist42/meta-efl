SUMMARY = "EFL Terminal Emulator"
LICENSE = "BSD-3-Clause"
HOMEPAGE = "https://www.enlightenment.org"

# Compute the first two digits of ${PV} as Base PV...
BPV = "${@'.'.join(d.getVar('PV').split('.')[0:2])}"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS:${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/enlightenment/terminology.git;protocol=https;branch=master \
	"

SRCREV = "36720f0570f669ce95eeff64d6cf885c1d6fe636"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=5194803f5875ad4c412e4d02eff083af \
    "

inherit meson pkgconfig

S = "${WORKDIR}/git"

EXTRA_OEMESON = " \
    -Dedje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    -Deet=${STAGING_BINDIR_NATIVE}/eet \
    "

FILES:${PN} = " \
    /usr/bin \
    /usr/share \
    "
