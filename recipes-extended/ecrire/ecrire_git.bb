SUMMARY = "EFL Simple Text Editor"
LICENSE = "GPLv3"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS_${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/apps/ecrire.git;protocol=https \
	"

SRCREV = "a2980856b7d1bb960bb7612e4042e034f530da9c"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    "

inherit cmake pkgconfig 

S = "${WORKDIR}/git"

FILES_${PN} = " \
    /usr/bin \
    /usr/share \    
    "

