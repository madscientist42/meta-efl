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
    file://COPYING;md5=e12e5c3b6822d51c620d5d05a7397c5c \
    "

inherit meson pkgconfig gettext mime

S = "${WORKDIR}/git"


# Because of the above, we're rather a MACHINE specific buildset for this part of
# things...
PACKAGE_ARCH = "${MACHINE_ARCH}"

# Core rules for build.  This is things that're generic for all BBCLASSes...things we
# explicitly support out of box (or not...usually not...) for any case of things.
EXTRA_OEMESON = " \
    -Dsystemd=false \
    -Dpulseaudio=false \
    -Dbuild-examples=false \
    -Dbuild-tests=false \
    -Dphysics=false \
    -Devas-loaders-disabler=gst,pdf,ps,raw,rsvg,xcf,dds,eet,generic,pmaps,psd,tga,tgv,wbmp,webp,xpm,json,jp2k,avif \
    -Decore-imf-loaders-disabler=scim,ibus \
    "

# Handle the needs of the Target build- including specifying the efl-native tools
# to do content generation...
EXTRA_OEMESON_append_class-target = " \
    -Dharfbuzz=true \
    -Dfribidi=true \
    -Dfb=true \
    -Dx11=true \
    -Dopengl=full \
    "

NATIVE_MESON_CONFIG = " \
    -Dfontconfig=false \
    -Daudio=false \
    -Dharfbuzz=false \
    -Dfribidi=false \
    -Deeze=false \
    -Dfb=false \
    -Dx11=false \
    -Dopengl=none \
    -Dwl=false \
    -Davahi=false \
    -Dgstreamer=false \
    -Dedje-sound-and-video=false \
    "

EXTRA_OEMESON_append_class-native = "${NATIVE_MESON_CONFIG}"
EXTRA_OECONF_append_class-nativesdk = "${NATIVE_MESON_CONFIG}"

BBCLASSEXTEND = "native nativesdk"

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
    ${libdir}/ecore* \
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
    ${datadir}/exactness \
    ${datadir}/eolian \
    ${bindir}/eldbus-codegen \
    "
