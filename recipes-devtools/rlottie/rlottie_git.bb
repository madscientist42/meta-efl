DESCRIPTION = "rlottie is a platform independent standalone c++ library for rendering vector based animations and art in realtime."
HOMEPAGE = "https://github.com/Samsung/rlottie"
LICENSE = "MIT+BSD+MPL+FreeType"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=9c6767159882cf3781303201885daa7f \
    file://licenses/COPYING.MIT;md5=fa9f69cbd246c28e686c58e0af9a1979 \
    file://licenses/COPYING.SKIA;md5=822f02cc7736281816581cd064afbb1c \
    file://licenses/COPYING.MPL;md5=815ca599c9df247a0c7f619bab123dad \
    file://licenses/COPYING.FTL;md5=4733c1b9ecac6a4c71414f233c5f98fd \
    file://licenses/COPYING.PIX;md5=f64e6eb897d32e10d70f707a7d426bdf \
    file://licenses/COPYING.RPD;md5=ba04aa8f65de1396a7e59d1d746c2125 \
    file://licenses/COPYING.STB;md5=9eeff69cf82bc7ce447a955d0ad50013 \
    "

SRC_URI = " \
    git://github.com/Samsung/rlottie.git;protocol=https \
    "


GIT_HASH ?= "f3eed9a332a924ead06ecd7a3167af7c8a72b68f"
SRCREV = "${@bb.utils.contains('PV', 'git', '${GIT_HASH}', 'v${PV}', d)}"

S = "${WORKDIR}/git"

inherit meson

# Force the libraries to be part of the main package and the pkgconfig, includes, etc.
# to be a -dev package...
FILES:${PN} = " \
    /usr/lib/librlottie-image-loader.so \
    /usr/lib/librlottie.so \
    /usr/lib/librlottie.so.0.2 \
    /usr/lib/librlottie.so.0 \
    "

FILES:${PN}-dev = " \
    /usr/include \
    /usr/lib/pkgconfig \
    "

# The upstream we're packaging framed it in a dev-so "problem" way- the thing is?
# It's legit and Yocto is complaining about something I can't easily fix...so...
INSANE_SKIP:${PN} = "dev-so"