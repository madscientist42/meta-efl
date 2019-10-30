SUMMARY = "Python bindings for the DBus inter-process communication system - dev set..."
SECTION = "devel/python"
HOMEPAGE = "http://www.freedesktop.org/Software/dbus"
LICENSE = "MIT & AFL-2.1 | GPLv2"
LIC_FILES_CHKSUM = " \
    file://COPYING;md5=b03240518994df6d8c974675675e5ca4 \
    file://dbus-gmain/COPYING;md5=99fece6728a80737c8fd3e7c734c17c4 \
    "

# This is quite a bit of overkill- but the problem is one of having both the
# Python 2.x and Python 3.x versions of python-dbus having the exact same 
# package config set (Basically, ${PN}-dev) so that you can realistically only
# use one or the other.  Main Yocto project upstream picked a designated victim
# (Python 2.x) to lose it's packaging for this stuff, but this doesn't QUITE 
# do what you wanted as while it's trying to avoid doing this extra, seemingly 
# redundant build step, it doesn't provide the proper support for building 
# anything that USES this stuff correctly in 2.x builds.  We're building/packaging 
# it so that it does the right things for follow-on packages and the SDKs as needed.
#
# Packages needing to build against python-dbus or python3-dbus should also
# depend on this one to get it to work right.  The python-efl and python3-efl
# packages already do this.

DEPENDS = "expat dbus dbus-glib virtual/libintl python-pyrex-native"

SRC_URI = "http://dbus.freedesktop.org/releases/dbus-python/dbus-python-${PV}.tar.gz"

SRC_URI[md5sum] = "428b7a9e7e2d154a7ceb3e13536283e4"
SRC_URI[sha256sum] = "cdd4de2c4f5e58f287b12013ed7b41dee81d503c8d0d2397c5bd2fb01badf260"

S = "${WORKDIR}/dbus-python-${PV}"

inherit distutils-base autotools pkgconfig

export STAGING_LIBDIR
export STAGING_INCDIR

do_install_append() {
    rm -rf ${D}${libdir}/python*
}

BBCLASSEXTEND = "native nativesdk"
