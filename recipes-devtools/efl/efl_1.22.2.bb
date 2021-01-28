SUMMARY = "Enlightenment Foundation Libraries"
LICENSE = "BSD & LGPLv2"
HOMEPAGE = "https://www.enlightenment.org"

# Compute the first two digits of ${PV} as Base PV...
BPV = "${@'.'.join(d.getVar('PV').split('.')[0:2])}"

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
    tslib \
    gstreamer1.0 \
	gstreamer1.0-libav \
	gstreamer1.0-plugins-base \
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

DEPENDS_class-nativesdk += " \
    efl-native \
    nativesdk-openssl \
    nativesdk-luajit \
    nativesdk-libjpeg-turbo \
    nativesdk-giflib \
    nativesdk-tiff \
    nativesdk-freetype \
    nativesdk-util-linux \
    nativesdk-gettext \
    nativesdk-glib-2.0 \
    nativesdk-dbus \
    nativesdk-libpng \
    "

RDEPENDS_nativesdk-${PN} = "\
    nativesdk-openssl \
    nativesdk-luajit \
    nativesdk-libjpeg-turbo \
    nativesdk-giflib \
    nativesdk-tiff \
    nativesdk-freetype \
    nativesdk-util-linux \
    nativesdk-gettext \
    nativesdk-glib-2.0 \
    nativesdk-dbus \
    nativesdk-libpng \
    nativesdk-nasm \
    "

PROVIDES += "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

SRC_URI = " \
    git://git.enlightenment.org/core/efl.git;protocol=https;branch=efl-${BPV} \
	"

SRCREV = "v${PV}"

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
    --enable-fb \
    --with-opengl=full \
    --with-pic \
    --with-x \
    --with-x11=xlib \
    --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    --with-eolian-gen=${STAGING_BINDIR_NATIVE}/eolian_gen \
    --with-eolian-cxx=${STAGING_BINDIR_NATIVE}/eolian_cxx \
    --with-elua=${STAGING_BINDIR_NATIVE}/elua \
    --with-eet-eet=${STAGING_BINDIR_NATIVE}/eet \
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

EXTRA_OECONF_append_class-nativesdk = " \
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
    --with-edje-cc=${STAGING_BINDIR_NATIVE}/edje_cc \
    --with-eolian-gen=${STAGING_BINDIR_NATIVE}/eolian_gen \
    --with-eolian-cxx=${STAGING_BINDIR_NATIVE}/eolian_cxx \
    --with-elua=${STAGING_BINDIR_NATIVE}/elua \
    --with-eet-eet=${STAGING_BINDIR_NATIVE}/eet \
    --with-elementary-codegen=${STAGING_BINDIR_NATIVE}/elementary_codegen \
    --with-elm-prefs-cc=${STAGING_BINDIR_NATIVE}/elm_prefs_cc \
    "

BBCLASSEXTEND = "native nativesdk"

# Now handle processing special cases for tasks...  There's going to be a few...  (Sigh...)
do_autotools_fixes() {
    # Give autotools a binky- it won't backfill this and they've thoughtfully .gitignored it.
    touch ${S}/ABOUT-NLS
}
do_patch[postfuncs] += "do_autotools_fixes "


# Start defining all the packagings for this...
PACKAGES =+ "edje-utils embryo-utils embryo-tests efreet-trash efreet-mime libeet libefreet ecore-audio ecore-input-evas ecore-input ecore-imf-evas ecore-imf ecore-file ecore-con ecore-ipc ecore-x ecore-evas libemotion eo ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus elua elementary elementary-dev elementary-themes elementary-configs elementary-tests"

FILES_efreet-trash = " \
    ${libdir}/libefreet_trash${SOLIBS} \
    "

FILES_efreet-mime = " \
    ${libdir}/libefreet_mime${SOLIBS} \
    "

FILES_libeet = " \
    ${libdir}/libeet${SOLIBS} \
    "

FILES_libefreet = " \
    ${libdir}/libefreet${SOLIBS} \
    "

FILES_ecore-audio = "\
    ${libdir}/libecore_audio${SOLIBS} \
    "

FILES_ecore-input-evas = "\
    ${libdir}/libecore_input_evas${SOLIBS} \
    "

FILES_ecore-input = "\
    ${libdir}/libecore_input${SOLIBS} \
    "

FILES_ecore-imf-evas = "\
    ${libdir}/libecore_imf_evas${SOLIBS} \
    ${libdir}/ecore-imf/modules/*/*/module.so \
    "

FILES_ecore-imf = "\
    ${libdir}/libecore_imf${SOLIBS} \
    "

FILES_ecore-file = "\
    ${libdir}/libecore_file${SOLIBS} \
    "

FILES_ecore-con = "\
    ${libdir}/libecore_con${SOLIBS} \
    ${libdir}/ecore_con/utils/v-1.22/efl_net_proxy_helper \
    "

FILES_ecore-ipc = "\
    ${libdir}/libecore_ipc${SOLIBS} \
    "

FILES_ecore-x = "\
    ${libdir}/libecore_x${SOLIBS} \
    ${libdir}/ecore_x/bin/v-*/ecore_x_vsync \
    "

FILES_ecore-evas = "\
    ${libdir}/libecore_evas${SOLIBS} \
    ${libdir}/ecore-evas/engines/*/*/module.so \
    "

FILES_eio = "\
    ${libdir}/libeio${SOLIBS} \
    "

FILES_eina = "\
    ${libdir}/libeina${SOLIBS} \
    ${bindir}/eina-bench-cmp \
    "

FILES_edje-utils = "\
    ${bindir}/edje_* \
    ${datadir}/edje/include/edje.inc \
    "

FILES_eldbus = "\
    ${libdir}/libeldbus${SOLIBS} \
    "

FILES_eo = "\
    ${libdir}/libeo${SOLIBS} \
    "

FILES_libemotion = "\
    ${libdir}/libemotion${SOLIBS} \
    "

FILES_efreet = " \
    ${datadir}/dbus-1/services/*Efreet* \
    ${libdir}/efreet/*/efreet*create \
    ${bindir}/efreetd \
    ${datadir}/efreet \
    "

FILES_eet = " \
    ${bindir}/eet \
    ${bindir}/eetpack \
    ${bindir}/vieet \
    ${bindir}/diffeet \
    ${libdir}/libeet${SOLIBS} \
    "

FILES_emotion = " \
    ${datadir}/emotion \
    ${libdir}/emotion/modules/gstreamer1/*/module.so \
    "

FILES_embryo-tests = " \
    ${datadir}/embryo/ \
    "

FILES_embryo-utils = " \
    ${binddir}/embryo_* \
    "

FILES_embryo = " \
    ${libdir}/libembryo${SOLIBS} \
    "

FILES_ethumb = " \
    ${datadir}/dbus-1/services/*Ethumb* \
    ${libdir}/libethumb${SOLIBS} \
    ${libdir}/libethumb_client${SOLIBS} \
    ${bindir}/ethumbd \
    ${bindir}/ethumbd_client \
    ${bindir}/ethumb \
    ${libdir}/ethumb/*/*/*/module.so \
    ${libdir}/ethumb/*/*/*/*.edj \
    ${libdir}/ethumb_client/utils/*/ethumbd_slave \
    ${datadir}/ethumb* \
    "

FILES_ecore = " \
    ${libdir}/libecore${SOLIBS} \
    ${libdir}/ecore*/*/*/*/module.so \
    ${datadir}/ecore* \
    "

FILES_evas = " \
    ${libdir}/libevas${SOLIBS} \
    ${libdir}/evas* \
    ${datadir}/evas* \
    ${bindir}/evas_cserve2_* \
    "

FILES_eeze = " \
    ${libdir}/libeeze${SOLIBS} \
    ${libdir}/eeze*/*/*/*/*/module.so \
    ${datadir}/eeze \
    ${bindir}/eeze_* \
    "

FILES_edje = " \
    ${libdir}/libedje${SOLIBS} \
    ${libdir}/edje*/*/*/*/module.so \
    ${libdir}/edje/utils/*/epp \
    ${datadir}/edje \
    ${datadir}/mime \
    "

FILES_elua = " \
    ${bindir}/elua \
    ${datadir}/elua \
    "

FILES_elementary += "\
    ${libdir}/edje/modules/elm \
    ${libdir}/elementary/* \
    ${prefix}/share/elementary/edje_externals/* \
    "

FILES_elementary-dev += "\
    ${bindir}/elm_prefs_cc \
    ${libdir}/elementary/modules/*/*/module.la \
    ${libdir}/cmake \
    "

FILES_elementary-themes = "\
    ${datadir}/elementary/themes \
    "

FILES_elementary-configs = "\
    ${datadir}/elementary/config \
    "

FILES_elementary-dbg += "\
    ${libdir}/elementary/modules/*/*/.debug \
    ${libdir}/edje/modules/elm/*/.debug \
    "

FILES_elementary-tests = "\
    ${bindir}/elementary* \
    ${datadir}/elementary/images \
    ${datadir}/elementary/objects \
    ${datadir}/elementary/examples \
    ${datadir}/applications/* \
    ${datadir}/icons/* \
    ${libdir}/elementary/modules/test_entry/* \
    ${libdir}/elementary/modules/test_map/* \
    ${prefix}/share/elementary/test* \
    "

FILES_${PN}-dev += " \
    ${libdir}/cmake \
    ${libdir}/ecore*/*/*/*/module.la \
    ${libdir}/evas*/*/*/*/*/module.la \
    ${libdir}/ethumb*/*/*/*/module.la \
    ${libdir}/eeze*/*/*/*/*/module.la \
    ${libdir}/edje*/*/*/*/module.la \
    ${libdir}/emotion/modules/gstreamer1/*/module.la \
    ${datadir}/gdb/auto-load \
    ${datadir}/eo/gdb \
    ${bindir}/eldbus-codegen \
    "
