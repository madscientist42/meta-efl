SUMMARY = "Enlightenment Window Manager"
LICENSE = "BSD"
HOMEPAGE = "https://www.enlightenment.org"

# Compute the first two digits of ${PV} as Base PV...
BPV = "${@'.'.join(d.getVar('PV').split('.')[0:2])}"

DEPENDS += " \
    efl \
    efl-native \
    xcb-util-keysyms \
    "

# Require all of EFL's functional core and it's dependencies if we're specified...
RDEPENDS:${PN} = " \
    luajit \
    libx11 \
    libxcursor \
    libxcomposite \
    libxinerama \
    libxrandr \
    libxtst \
    libxscrnsaver \
    harfbuzz \
    fribidi \
    libjpeg-turbo \
    libpng \
    giflib \
    tiff \
    freetype \
    poppler \
    avahi-daemon \
    avahi-dnsconfd \
    libavahi-client \
    libavahi-glib \
    libavahi-gobject \
    avahi-utils \
    util-linux \
    librsvg \
    eudev \
    libsndfile1 \
    gstreamer1.0 \
	gstreamer1.0-libav \
	gstreamer1.0-plugins-good \
	gstreamer1.0-plugins-bad \
    libeet \
    libefreet \
    ecore-audio \
    ecore-input-evas \
    ecore-input \
    ecore-imf-evas \
    ecore-imf \
    ecore-file \
    ecore-con \
    ecore-ipc \
    ecore-x \
    ecore-evas \
    libemotion \
    eo \
    ecore \
    edje \
    eet \
    eeze \
    efreet \
    eina \
    eio \
    embryo \
    emotion \
    ethumb \
    evas \
    eldbus \
    elua \
    elementary \
    elementary-themes \
    elementary-configs \
    "

SRC_URI = " \
    git://git.enlightenment.org/enlightenment/enlightenment.git;protocol=https;branch=enlightenment-${BPV} \
	"

SRCREV = "v${PV}"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=cf9a72875f2592643ec472b0ca65413a \
    "

inherit meson pkgconfig gettext mime-xdg

S = "${WORKDIR}/git"

# Handle the needs of the Target build- including specifying the efl-native tools
# to do content generation...
EXTRA_OEMESON = " \
    -Dedje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    -Deet=${STAGING_BINDIR_NATIVE}/eet \
    -Deldbus-codegen=${STAGING_BINDIR_NATIVE}/eldbus-codegen \
    -Dsystemd=false \
    -Dgeolocation=false \
    -Dbacklight=false \
    -Dbattery=false \
    -Dsysinfo=false \
    -Dmount-eeze=true \
    "

# Append one file to the end of the list...
FILES:${PN} += " \
    /usr/share/xsessions/enlightenment.desktop \
    /usr/share/icons \
    "
