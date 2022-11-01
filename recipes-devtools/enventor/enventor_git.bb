SUMMARY = "EFL EDC dynamic editor"
LICENSE = "BSD"
HOMEPAGE = "https://www.enlightenment.org"

LIC_FILES_CHKSUM = "file://COPYING;md5=a25b49ca1294e5e33842c4079a2fa2ca"

DEPENDS += " \
    efl-native \
    efl \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS:${PN} = "ecore edje edje-utils eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/enlightenment/enventor.git;protocol=https;branch=master \
    "

SRCREV = "2832e3197525499fbcdbbfba15c3e808dcc819b2"

EXTRA_OECONF += " \
    --with-eet-eet=${RECIPE_SYSROOT_NATIVE}/usr/bin/eet \
    --with-edje-cc=${RECIPE_SYSROOT_NATIVE}/usr/bin/edje_cc \
    "

S = "${WORKDIR}/git"

inherit autotools pkgconfig gettext

FILES:${PN} += " \
    /usr/share \
    "

BBCLASSEXTEND = "native nativesdk"