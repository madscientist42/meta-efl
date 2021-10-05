SUMMARY = "EFL Based On-screen ruler and measurement tools"
LICENSE = "GPLv2"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS:${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/apps/eruler.git;protocol=https \
	"

SRCREV = "ef5c03207c0985a3b42f96d77fcbef995ea86298"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    "

inherit autotools pkgconfig

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    "

FILES:${PN} = " \
    /usr/bin \
    /usr/share \
    "

