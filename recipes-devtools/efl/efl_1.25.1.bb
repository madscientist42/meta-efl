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
    libxpresent \
    harfbuzz \
    fribidi \
    libjpeg-turbo \
    libpng \
    giflib \
    tiff \
    freetype \
    poppler \
    librsvg \
    rlottie \
    util-linux \
    eudev \
    libsndfile1 \
    gstreamer1.0 \
	gstreamer1.0-libav \
	gstreamer1.0-plugins-base \
	gstreamer1.0-plugins-good \
	gstreamer1.0-plugins-bad \
    "

DEPENDS:class-native += " \
    luajit-native \
    libjpeg-turbo-native \
    libpng-native \
    giflib-native \
    tiff-native \
    librsvg-native \
    freetype-native \
    util-linux-native \
    dbus-native \
    libsndfile1-native \
    "

DEPENDS:class-nativesdk += " \
    efl-native \
    nativesdk-libjpeg-turbo \
    nativesdk-giflib \
    nativesdk-tiff \
    nativesdk-libpng \
    nativesdk-librsvg \
    nativesdk-freetype \
    nativesdk-util-linux \
    nativesdk-gettext \
    nativesdk-glib-2.0 \
    nativesdk-dbus \
    nativesdk-libsndfile1 \
    "

RDEPENDS:nativesdk-${PN} = "\
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
    file://COPYING;md5=e12e5c3b6822d51c620d5d05a7397c5c \
    "

inherit meson pkgconfig gettext mime

S = "${WORKDIR}/git"


# Because of the above, we're rather a MACHINE specific buildset for this part of
# things...
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Core rules for build.  This is things that're generic for all BBCLASSes...things we
# explicitly support out of box (or not...usually not...) for any case of things.
#
# FIXME : We need to properly integrate PKGCONFIG support for systemd and pulseaudio
#         at some point in the near future...
#
EXTRA_OEMESON = " \
    -Dsystemd=false \
    -Dpulseaudio=false \
    -Dbuild-examples=false \
    -Dbuild-tests=false \
    -Dphysics=false \
    "

# Handle the needs of the Target build- including specifying the efl-native tools
# to do content generation...
#
# FIXME : We're crippling a few things that would make sense in a desktop but less
#         so in 4/5ths of the embedded contexts.  We probably want to fix that.
#         (Also, it seems we don't have the version of libavif pinned down for this...
#          it certainly isn't the latest stable because there's number of param probs.)
EXTRA_OEMESON_append_class-target = " \
    -Decore-imf-loaders-disabler=scim,ibus \
    -Devas-loaders-disabler=ps,raw,avif \
    -Dopengl=full \
    -Dfb=true \
    -Dxpresent=true \
    -Dxinput2=true \
    -Dxinput22=true \
    "

NATIVE_MESON_CONFIG = " \
    -Dharfbuzz=false \
    -Dfribidi=false \
    -Deeze=false \
    -Dx11=false \
    -Dopengl=none \
    -Dgstreamer=false \
    -Dedje-sound-and-video=false \
    -Devas-loaders-disabler=gst,pdf,ps,raw,xcf,dds,eet,rsvg,generic,pmaps,psd,tga,tgv,wbmp,webp,xpm,json,jp2k,avif \
    "

EXTRA_OEMESON:append:class-native = "${NATIVE_MESON_CONFIG}"
EXTRA_OECONF:append:class-nativesdk = "${NATIVE_MESON_CONFIG}"

BBCLASSEXTEND = "native nativesdk"

# Start defining all the packagings for this...
PACKAGES =+ "edje-utils embryo-utils embryo-tests efreet-trash efreet-mime libeet libefreet ecore-audio ecore-input-evas ecore-input ecore-imf-evas ecore-imf ecore-file ecore-con ecore-ipc ecore-x ecore-evas libemotion eo ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus elua elementary elementary-dev elementary-themes elementary-configs elementary-tests"

FILES:efreet-trash = " \
    ${libdir}/libefreet_trash${SOLIBS} \
    "

FILES:efreet-mime = " \
    ${libdir}/libefreet_mime${SOLIBS} \
    "

FILES:libeet = " \
    ${libdir}/libeet${SOLIBS} \
    "

FILES:libefreet = " \
    ${libdir}/libefreet${SOLIBS} \
    "

FILES:ecore-audio = "\
    ${libdir}/libecore_audio${SOLIBS} \
    "

FILES:ecore-input-evas = "\
    ${libdir}/libecore_input_evas${SOLIBS} \
    "

FILES:ecore-input = "\
    ${libdir}/libecore_input${SOLIBS} \
    "

FILES:ecore-imf-evas = "\
    ${libdir}/libecore_imf_evas${SOLIBS} \
    ${libdir}/ecore-imf/modules/*/*/module.so \
    "

FILES:ecore-imf = "\
    ${libdir}/libecore_imf${SOLIBS} \
    "

FILES:ecore-file = "\
    ${libdir}/libecore_file${SOLIBS} \
    "

FILES:ecore-con = "\
    ${libdir}/libecore_con${SOLIBS} \
    ${libdir}/ecore_con/utils/v-1.22/efl_net_proxy_helper \
    "

FILES:ecore-ipc = "\
    ${libdir}/libecore_ipc${SOLIBS} \
    "

FILES:ecore-x = "\
    ${libdir}/libecore_x${SOLIBS} \
    ${libdir}/ecore_x/bin/v-*/ecore_x_vsync \
    "

FILES:ecore-evas = "\
    ${libdir}/libecore_evas${SOLIBS} \
    ${libdir}/ecore-evas/engines/*/*/module.so \
    "

FILES:eio = "\
    ${libdir}/libeio${SOLIBS} \
    "

FILES:eina = "\
    ${libdir}/libeina${SOLIBS} \
    ${bindir}/eina-bench-cmp \
    "

FILES:edje-utils = "\
    ${bindir}/edje_* \
    ${datadir}/edje/include/edje.inc \
    "

FILES:eldbus = "\
    ${libdir}/libeldbus${SOLIBS} \
    "

FILES:eo = "\
    ${libdir}/libeo${SOLIBS} \
    "

FILES:libemotion = "\
    ${libdir}/libemotion${SOLIBS} \
    "

FILES:efreet = " \
    ${datadir}/dbus-1/services/*Efreet* \
    ${libdir}/efreet/*/efreet*create \
    ${bindir}/efreetd \
    ${datadir}/efreet \
    "

FILES:eet = " \
    ${bindir}/eet \
    ${bindir}/eetpack \
    ${bindir}/vieet \
    ${bindir}/diffeet \
    ${libdir}/libeet${SOLIBS} \
    "

FILES:emotion = " \
    ${datadir}/emotion \
    ${libdir}/emotion/modules/gstreamer1/*/module.so \
    "

FILES:embryo-tests = " \
    ${datadir}/embryo/ \
    "

FILES:embryo-utils = " \
    ${binddir}/embryo_* \
    "

FILES:embryo = " \
    ${libdir}/libembryo${SOLIBS} \
    "

FILES:ethumb = " \
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

FILES:ecore = " \
    ${libdir}/libecore${SOLIBS} \
    ${libdir}/ecore* \
    ${datadir}/ecore* \
    "

FILES:evas = " \
    ${libdir}/libevas${SOLIBS} \
    ${libdir}/evas* \
    ${datadir}/evas* \
    ${bindir}/evas_cserve2_* \
    "

FILES:eeze = " \
    ${libdir}/libeeze${SOLIBS} \
    ${libdir}/eeze*/*/*/*/*/module.so \
    ${datadir}/eeze \
    ${bindir}/eeze_* \
    "

FILES:edje = " \
    ${libdir}/libedje${SOLIBS} \
    ${libdir}/edje*/*/*/*/module.so \
    ${libdir}/edje/utils/*/epp \
    ${datadir}/edje \
    ${datadir}/mime \
    "

FILES:elua = " \
    ${bindir}/elua \
    ${datadir}/elua \
    "

FILES:elementary += "\
    ${libdir}/edje/modules/elm \
    ${libdir}/elementary/* \
    ${prefix}/share/elementary/edje_externals/* \
    "

FILES:elementary-dev += "\
    ${bindir}/elm_prefs_cc \
    ${libdir}/elementary/modules/*/*/module.la \
    ${libdir}/cmake \
    "

FILES:elementary-themes = "\
    ${datadir}/elementary/themes \
    "

FILES:elementary-configs = "\
    ${datadir}/elementary/config \
    "

FILES:elementary-dbg += "\
    ${libdir}/elementary/modules/*/*/.debug \
    ${libdir}/edje/modules/elm/*/.debug \
    "

FILES:elementary-tests = "\
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

FILES:${PN}-dev += " \
    ${libdir}/cmake \
    ${libdir}/ecore*/*/*/*/module.la \
    ${libdir}/evas*/*/*/*/*/module.la \
    ${libdir}/ethumb*/*/*/*/module.la \
    ${libdir}/eeze*/*/*/*/*/module.la \
    ${libdir}/edje*/*/*/*/module.la \
    ${libdir}/emotion/modules/gstreamer1/*/module.la \
    ${datadir}/gdb/auto-load \
    ${datadir}/eo/gdb \
    ${datadir}/exactness \
    ${datadir}/eolian \
    ${bindir}/eldbus-codegen \
    "
