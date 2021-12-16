SUMMARY = "Enlightenment Foundation Libraries, Python3cd . Bindings"
LICENSE = "GPLv3 & LGPLv3"
HOMEPAGE = "https://www.enlightenment.org"

# Compute the first two digits of ${PV} as Base PV...
BPV = "${@'.'.join(d.getVar('PV').split('.')[0:2])}"

DEPENDS = " \
    efl \
    efl-native \
    dbus \
    dbus-native \
    python3-dbus \
    python3-native \
    python3-cython-native \
    "

SRC_URI = " \
    git://git.enlightenment.org/bindings/python/python-efl.git;protocol=https;branch=python-efl-${BPV} \
	"

SRCREV = "v${PV}"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING.LESSER;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    "

inherit python3-dir setuptools3

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native nativesdk"


