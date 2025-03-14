SUMMARY = "Entrance EFL based X11 display manager"
LICENSE = "GPL-3.0-or-later"
HOMEPAGE = "https://github.com/Obsidian-StudiosInc/entrance"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of Enlightenment and EFL's functional core and it's dependencies
# if we're specified.  Technically, this is universal, but right now we're still
# only really used if we're in meta-efl, soo...Enlightenment's a requirement.
RDEPENDS:${PN} = " \
    enlightenment \
    "

SRC_URI = " \
    git://github.com/Obsidian-StudiosInc/entrance.git;protocol=https;branch=master \
    file://change_to_sh_instead_of_bash.patch \
	"

SRCREV = "4ace4c854706bb422b05052c75a692751190ad24"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
    "

inherit meson pkgconfig

S = "${WORKDIR}/git"


# For now, turn off PAM, consolekit, etc. until we can gracefully add packageconfig knobs for this stuff.
EXTRA_OEMESON = " \
    -Dpam=false \
    -Dconsolekit=false \
    -Dedje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    "


