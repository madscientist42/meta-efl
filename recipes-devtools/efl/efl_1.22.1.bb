SUMMARY = "Enlightenment Foundation Libraries"
LICENSE = "BSD & LGPLv2"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
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
    giflib \
    tiff \ 
    freetype \
    poppler \
    avahi \
    util-linux \
    librsvg \
    eudev \
    libsndfile1 \    
    gstreamer1.0 \
	gstreamer1.0-libav \
	gstreamer1.0-plugins-good \
	gstreamer1.0-plugins-bad \
    "

SRC_URI = " \
    https://download.enlightenment.org/rel/libs/${PN}/${PN}-${PV}.tar.xz \
	"

SRC_URI[md5sum] = "636cf76b0b0f9170806aafe883ba700d"
SRC_URI[sha256sum] = "20d3e5e945d54ae46ec916c7341b5dec24f904b6c0123b4d3ecb8cd8d596ae12"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=e5f6e713fdebf1237adf5c87de8255d8 \
    "

inherit autotools-brokensep pkgconfig gettext

S = "${WORKDIR}/${PN}-${PV}"

EXTRA_OECONF = " \
    --disable-doc \
    --disable-rpath \
    --disable-systemd \
    --disable-pulseaudio \
    --disable-spectre \
    --disable-libraw \
    --disable-physics \
    --enable-harfbuzz \
    --enable-fribidi \
    "


