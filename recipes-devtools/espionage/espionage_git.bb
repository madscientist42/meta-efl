SUMMARY = "PyEFL D-Bus inspector"
LICENSE = "GPLv3"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    python-efl \
    python-efl-native \
    python-dbus \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS_${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus python python-efl python-dbus python-json"

SRC_URI = " \
    git://git.enlightenment.org/apps/espionage.git;protocol=https \
	"

SRCREV = "75fd54d1f0954c289b69b643dfb3dd091c68b08b"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    "

inherit python-dir setuptools

S = "${WORKDIR}/git"

FILES_${PN} = " \
    /usr/bin \
    /usr/lib \
    /usr/share \
    "
