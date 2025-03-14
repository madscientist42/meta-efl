SUMMARY = "Enlightenment Foundation Libraries"
LICENSE = "BSD-3-Clause & LGPL-2.0-or-later"
HOMEPAGE = "https://www.enlightenment.org"

LIC_FILES_CHKSUM = " \
    file://COPYING;md5=e12e5c3b6822d51c620d5d05a7397c5c \
    "

# Because of the above, we're rather a MACHINE specific buildset for this part of
# things...
PACKAGE_ARCH = "${MACHINE_ARCH}"

# This builds with Meson, etc.
inherit meson pkgconfig gettext mime

# We provide these moving parts for RDEPENDS purposes...
PROVIDES += "ecore edje eet eeze efreet eina eio embryo emotion ethumb evas eldbus"

# This is the non-bbclass specific (R)DEPENDS list for the recipe.  There's a TARGET
# specifc list over the NATIVE and global list that are supersets of the core.
CORE_DEPENDS = " \
    luajit \
    jpeg \
    zlib \
    lz4 \
    dbus \
    libpng \
    tiff \
    giflib \
    freetype \
    shared-mime-info \
    "

DEPENDS = " \
    efl-native \
    ${CORE_DEPENDS} \
    "

TARGET_DEPENDS = " \
    openssl \
    libsndfile1 \
    eudev \
    libinput \
    libxkbcommon \
    freetype \
    fontconfig \
    harfbuzz \
    fribidi \
    librsvg \
    "

DEPENDS:append:class-target = " \
    ${TARGET_DEPENDS} \
    "

RDEPENDS:${PN} = " \
    ${CORE_DEPENDS} \
    "

RDEPENDS:${PN}:append:class-target = " \
    ${TARGET_DEPENDS} \
    "

# Compute the first two digits of ${PV} as Base PV for branch on checkout.
BPV = "${@'.'.join(d.getVar('PV').split('.')[0:2])}"

SRC_URI = " \
    git://git.enlightenment.org/enlightenment/efl.git;protocol=https;branch=v-${BPV} \
	"

SRCREV = "ddb5860f2f7e633cadcc07a837dc226fa2aa4821"
S = "${WORKDIR}/git"

# Core rules for build.  This is things that're generic for all BBCLASSes...under all
# cases (Things like the Physics system isn't contemplated at this time, for example.)
# This helps us adapt this to the current state of affairs for the latest Yocto versions.
EXTRA_OEMESON = " \
    -Dbuild-examples=false \
    -Dbuild-tests=false \
    -Dphysics=false \
    -Dlua-interpreter=luajit \
    -Dembedded-lz4=false \
    -Dmount-path='/usr/bin/mount' \
    -Dunmount-path='/usr/bin/umount' \
    -Deject-path='/usr/bin/eject' \
    "

# Native and Nativesdk's purpose is to provide tooling for the target build as a canadian-cross type
# affair.  There's a LOT in either the native/nativesdk build that just DOES NOT MAKE SENSE
# for us in this context.  So we're going to make generic NATIVE mode carve-outs for things that
# turns off vast swathes of EFL's core to get us tooling for the main build because it's not needed
# in the case of that.
NATIVE_SPECIFIC_EXTRAS = " \
    -Daudio=false \
    -Dx11=false \
    -Dwl=false \
    -Dinput=false \
    -Deeze=false \
    -Dopengl=none \
    -Dsystemd=false \
    -Dgstreamer=false \
    -Dpulseaudio=false \
    -Dxinput2=false \
    -Dxinput22=false \
    -Dnetwork-backend=none \
    -Dharfbuzz=false \
    -Dfribidi=false \
    -Dfontconfig=false \
    -Dedje-sound-and-video=false \
    -Dlibmount=false \
    -Dv4l2=false \
    -Dnative-arch-optimization=false \
    -Devas-loaders-disabler='gst,pdf,ps,raw,svg,rsvg,xcf,bmp,dds,generic,ico,jp2k,pmaps,psd,tga,tgv,wbmp,webp,xpm,json,avif,heif,jxl' \
    "

EXTRA_OEMESON:append:class-native = " ${NATIVE_SPECIFIC_EXTRAS}"
EXTRA_OEMESON:append:class-nativesdk = " ${NATIVE_SPECIFIC_EXTRAS}"

# Handle the needs of the Target build- including specifying the efl-native tools
# to do content generation...
#
# FIXME : We're crippling a few things that would make sense in a desktop but less
#         so in 4/5ths of the embedded contexts.  We probably want to fix that.
#         (Also, it seems we don't have the version of libavif pinned down for this...
#          it certainly isn't the latest stable because there's number of param probs.)
EXTRA_OEMESON:append:class-target = " \
    -Decore-imf-loaders-disabler=scim,ibus \
    -Devas-loaders-disabler='gst,pdf,ps,raw,xcf,bmp,dds,generic,ico,jp2k,pmaps,psd,tga,tgv,wbmp,webp,xpm,json,avif,heif,jxl' \
    "

# This is a global, for all BBCLASSes, thing- so it's empty.  We force other behaviors at build time accordingly
PACKAGECONFIG ??= ""

# Kind of autodetect some of our hooks.  If there's a feature turned on, we should (emphasis this!)
# turn around and build for the package using an amended PACKAGECONFIG.  We default to Framebuffer
# support for a base on this to support core functionality under all cases.  We extend according to
# what was set for the Distro in DISTRO_FEATURES.  We assign to PACKAGECONFIG for class-target
# to do this functionality ONLY on the target packagings of this recipe.
PACKAGECONFIG:class-target += "fb"
PACKAGECONFIG:class-target += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11', '', d)}"
PACKAGECONFIG:class-target += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"
PACKAGECONFIG:class-target += "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl', '', d)}"
PACKAGECONFIG:class-target += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"
PACKAGECONFIG:class-target += "${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio', '', d)}"
PACKAGECONFIG:class-target += "${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'systemd', '', d)}"
PACKAGECONFIG:class-target += "${@bb.utils.contains('DISTRO_FEATURES', 'sdl', 'sdl', '', d)}"

# Handle OpenGL support...  FIXME - Need to do package dependencies in this one.
OPENGL_DEPENDENCIES = " \
    mesa \
    "
PACKAGECONFIG[opengl] = "-Dopengl=full,-Dopengl=none,${OPENGL_DEPENDENCIES},${OPENGL_DEPENDENCIES}"

# Handle base framebuffer support...  FIXME - Need to do package dependencies in this one.
PACKAGECONFIG[fb] = "-Dfb=true,-Dfb=false,"

# Handle DRM framebuffer support...  FIXME - Need to do package dependencies in this one.
PACKAGECONFIG[drm] = "-Ddrm=true,-Ddrm=false,"

# Handle Wayland support...  FIXME - Need to do package dependencies in this one.
WAYLAND_DEPENDENCIES = " \
    wayland \
    "
PACKAGECONFIG[wayland] = "-Dwl=true,-Dwl=false,${WAYLAND_DEPENDENCIES},${WAYLAND_DEPENDENCIES}"

# Handle systemd support...
PACKAGECONFIG[systemd] = "-Dsystemd=true,-Dsystemd=false,"

# Handle pulseaudio support...
PACKAGECONFIG[pulseaudio] = "-Dpulseaudio=true,-Dpulseaudio=false,pulseaudio,pulseaudio"

# Handle X11 support
X11_DEPENDENCIES = " \
    libx11 \
    libxcursor \
    libxcomposite \
    libxinerama \
    libxrandr \
    libxtst \
    libxscrnsaver \
    libxpresent \
    libxdamage \
    "
PACKAGECONFIG[x11] = "-Dx11=true -Dxpresent=true,-Dx11=false -Dxinput2=false -Dxinput22=false,${X11_DEPENDENCIES},${X11_DEPENDENCIES}"

# Handle SDL support  FIXME - Need to verify package dependencies in this one.
PACKAGECONFIG[sdl] = "-Dsdl=true,-Dsdl=false,libsdl2,libsdl2"

# Handle gstreamer support.  FIXME - Need to do package dependencies in this one.
PACKAGECONFIG[gstreamer] = "-Dgstreamer=true,-Dgstreamer=false,"

# Extend the recipe to count for the NATIVE and NATIVESDK bbclasses.
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
    ${prefix}/share/elementary/colors/* \
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

# FIXME - For now, until I can sort out the stupid (yes!) grabbing of
# build pathing in the .so files and binaries from the framework, tell
# Yocto it's, "okay".
INSANE_SKIP = "buildpaths"