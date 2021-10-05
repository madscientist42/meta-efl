DESCRIPTION = "Exquisite EFL splash-screen engine"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=00fa857fa1985216012cb415a6b40b0c"

SRC_URI = " \
    git://github.com/madscientist42/exquisite.git;protocol=https \
    "

SRCREV = "8179253cddcc6701b7a7727c6a4055bf41677a22"

S = "${WORKDIR}/git"

DEPENDS = " \
    efl-native \
    efl \
    "

RDEPENDS:${PN} = " \
    eet \
    evas \
    ecore-con \
    ecore-x \
    ecore-evas \
    embryo \
    edje \
    "

inherit cmake pkgconfig efl-theme

# Override behavior here - we want the lib and the bin in the same package.
PACKAGES = "${PN} ${PN}-dbg ${PN}-demo"
FILES:${PN} = " \
    /usr/bin/exquisite \
    /usr/bin/exquisite_write \
    /usr/lib \
    "

FILES:${PN}-dbg = " \
    /usr/bin/.debug \
    "

# Strip back out the theme .bbclass file adds to the base packaging
# so we can separately define the demo theme, etc. as packaged as
# ${PN}-demo since it's not needed past testing things and a rosetta
# stone for people to make their own theme setups and bootsplashes
# with this accordingly as it's almost more a bootsplash toolkit
# unlike with psplash and usplash
FILES:${PN}:remove += " \
    /usr/share/efl-themes/exquisite/default.edj \
    "
FILES:${PN}-demo = " \
    /usr/share \
    "

# As strange as this might sound, we want this available and around to test
# bootsplash work either in a devshell or in the cross-compile SDK or an eSDK.
BBCLASSEXTEND = "native nativesdk"