SUMMARY = "EFL based Bittorrent client"
LICENSE = "GPLv3"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    python3-efl \
    python3-efl-native \
    python3-distutils-extra \
    python3-distutils-extra-native \
    python3-xdg \
    intltool-native \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS:${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus python3 python3-efl python3-dbus"

SRC_URI = " \
    git://git.enlightenment.org/apps/epour.git;protocol=https \
	"

SRCREV = "028d7873d7c0efbae1d23eddfeb57c95e74c900a"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    "

inherit python3-dir setuptools3 mime-xdg

S = "${WORKDIR}/git"


FILES:${PN} = " \
    /usr/bin \
    /usr/lib \
    /usr/share \
    "
