DESCRIPTION = "Variables defined by the XDG Base Directory Specification"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENCE;md5=42ab0b466c05a052a8bcc85bf71f74d0"

inherit python3-dir

SRC_URI = " \
    git://github.com/srstevenson/xdg.git;protocol=https;branch=main \
    "

SRCREV = "${PV}"

S = "${WORKDIR}/git"