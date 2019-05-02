SUMMARY = "Enlightenment Foundation Libraries"
LICENSE = "BSD & LGPLv2"
HOMEPAGE = "https://www.enlightenment.org"

DEPENDS += " \
    efl-native \
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

DEPENDS_class-native += " \
    openssl-native \
    luajit-native \
    libjpeg-turbo-native \
    giflib-native \
    tiff-native \ 
    freetype-native \
    util-linux-native \
    gettext-native \
    glib-2.0-native \
    dbus-native \
    libpng-native \
    "

SRC_URI = " \
    git://git.enlightenment.org/core/efl.git;protocol=https;branch=efl-1.22 \
	"

SRCREV = "v1.22.2"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=e5f6e713fdebf1237adf5c87de8255d8 \
    "

inherit autotools pkgconfig gettext

S = "${WORKDIR}/git"

EXTRA_OECONF = " \
    --disable-doc \
    --disable-rpath \
    --disable-systemd \
    --disable-pulseaudio \
    --disable-spectre \
    --disable-libraw \
    --disable-physics \
    "

# Handle the needs of the Target build- including specifying the efl-native tools
# to do content generation...
EXTRA_OECONF_append_class-target = " \
    --enable-harfbuzz \
    --enable-fribidi \
    --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    --with-eolian-gen=${STAGING_BINDIR_NATIVE}/eolian_gen \
    --with-eolian-cxx=${STAGING_BINDIR_NATIVE}/eolian_cxx \
    --with-elua=${STAGING_BINDIR_NATIVE}/elua \
    --with-bin-eet-eet=${STAGING_BINDIR_NATIVE}/eet \
    --with-elementary-codegen=${STAGING_BINDIR_NATIVE}/elementary_codegen \
    --with-elm-prefs-cc=${STAGING_BINDIR_NATIVE}/elm_prefs_cc \
    "

EXTRA_OECONF_append_class-native = " \
    --disable-fontconfig \
    --disable-audio \
    --disable-multisense \
    --disable-libeeze \
    --with-x11=none \
    --disable-harfbuzz \
    --disable-fribidi \
    --disable-image-loader-bmp \
    --disable-image-loader-eet \
    --disable-image-loader-generic \
    --disable-image-loader-gif \
    --disable-image-loader-ico \
    --disable-image-loader-jp2k \
    --disable-image-loader-pmaps \
    --disable-image-loader-psd \
    --disable-image-loader-tga \
    --disable-image-loader-wbmp \
    --disable-image-loader-webp \
    --disable-image-loader-xpm \
    --disable-image-loader-tgv \
    --disable-image-loader-dds \
    --disable-gstreamer1 \
    --disable-poppler \
    --disable-avahi \
    --disable-librsvg \
    "

BBCLASSEXTEND = "native"

# Now handle processing special cases for tasks...  There's going to be a few...  (Sigh...)
do_autotools_fixes() {
    # Give autotools a binky- it won't backfill this and they've thoughtfully .gitignored it.
    touch ${S}/ABOUT-NLS
}
addtask do_autotools_fixes after do_fetch before do_configure 
