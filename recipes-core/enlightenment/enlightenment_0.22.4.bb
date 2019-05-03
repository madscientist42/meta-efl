SUMMARY = "Enlightenment Foundation Libraries"
LICENSE = "BSD"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
    efl \
    xcb-util-keysyms \
    "

SRC_URI = " \
    git://git.enlightenment.org/core/enlightenment.git;protocol=https;branch=enlightenment-0.22 \
	"

SRCREV = "v0.22.4"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=76de290eb3fdda12121830191c152a7d \
    "

inherit autotools pkgconfig gettext

S = "${WORKDIR}/git"

# Handle the needs of the Target build- including specifying the efl-native tools
# to do content generation...
EXTRA_OECONF = " \
    --with-eet-eet=${STAGING_BINDIR_NATIVE}/eet \
    --with-eldbus_codegen=${STAGING_BINDIR_NATIVE}/eldbus-codegen \
    --x-includes=${STAGING_INCDIR}/X11 \
    --x-libraries=${STAGING_LIBDIR} \
    --disable-doc \
    --disable-rpath \
    --disable-systemd \
    --disable-geolocation \
    "

# Now handle processing special cases for tasks...  There's going to be a few...  (Sigh...)
do_autotools_fixes() {
    # Give autotools a binky- it won't backfill this and they've thoughtfully .gitignored it.
    touch ${S}/ABOUT-NLS
}
addtask do_autotools_fixes after do_fetch before do_configure 

# Append one file to the end of the list...
FILES_${PN} += "/usr/share/xsessions/enlightenment.desktop"
