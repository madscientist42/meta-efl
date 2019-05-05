SUMMARY = "Entrance EFL based X11 display manager"
LICENSE = "GPLv3"
HOMEPAGE = "https://github.com/Obsidian-StudiosInc/entrance"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of Enlightenment and EFL's functional core and it's dependencies if we're specified...
RDEPENDS_${PN} = " \ 
    enlightenment \
    "

SRC_URI = " \
    git://github.com/Obsidian-StudiosInc/entrance.git;protocol=https \
    file://change_to_sh_instead_of_bash.patch \
	"

SRCREV = "d0ef8b13baa3410b9ca0cd4245979312dc2fc46a"

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


