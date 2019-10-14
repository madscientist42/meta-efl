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

SRCREV = "f5f2adcfd5872ae94a62d4d12810b6ee9ce95f91"

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


