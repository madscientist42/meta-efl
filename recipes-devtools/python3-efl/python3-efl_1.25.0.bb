SUMMARY = "Enlightenment Foundation Libraries, Python3cd . Bindings"
LICENSE = "GPLv3 & LGPLv3"
HOMEPAGE = "https://www.enlightenment.org"

# Compute the first two digits of ${PV} as Base PV...this is what we use
# for the branch pull on this one...
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
    git://git.enlightenment.org/enlightenment/python-efl.git;protocol=https;branch=python-efl-${BPV} \
	"

# Pulled from the version we're claiming in the recipe out of the repository...
# (We used to use the tag, but Yocto determined that was not deterministic enough- for good reasons.)
SRCREV = "b91fdb82ba0b315130d2f45ab7f5caf816dfa756"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    file://COPYING.LESSER;md5=e6a600fd5e1d9cbde2d983680233ad02 \
    "

inherit python3-dir setuptools3

S = "${WORKDIR}/git"

BBCLASSEXTEND = "native nativesdk"


