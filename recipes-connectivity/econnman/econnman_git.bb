SUMMARY = "EFL based GUI for Connman"
LICENSE = "GPLv3"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS:${PN} = "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus python3 python3-configargparse"

SRC_URI = " \
    git://git.enlightenment.org/enlightenment/econnman.git;protocol=https;branch=master \
	"

SRCREV = "18e7be6bf80df6b86965ba93391b205339fc7267"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=6a6a8e020838b23406c81b19c1d46df6 \
    "

inherit autotools pkgconfig

S = "${WORKDIR}/git"

# Since this allegedly works with Python3, even though they didn't do it, "right"
# for what most people did with it...we're going to try a kludge here via sed...
do_source_fixup() {
    sed -i "s/\/usr\/bin\/python/\/usr\/bin\/python3/g" ${S}/econnman-bin.in
}
do_patch[postfuncs] += " do_source_fixup"

# For now, turn off PAM, consolekit, etc. until we can gracefully add packageconfig knobs for this stuff.
EXTRA_OECONF = " \
    --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    "

# There's something about this where YOCTO can't figure out that I specified "python3" in my RDEPS for
# this recipe...SO, tell it to skip that sanity check.
INSANE_SKIP:${PN} = "file-rdeps"