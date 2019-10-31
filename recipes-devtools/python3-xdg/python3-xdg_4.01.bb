DESCRIPTION = "Variables defined by the XDG Base Directory Specification"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENCE;md5=0303f838363d700ddcb9e21e52fea608"

inherit python3-dir

SRC_URI = " \
    git://github.com/srstevenson/xdg.git;protocol=https \
    "

SRCREV = "36a9f27330df33949630a27d4eabd35ede8df5ef"

S = "${WORKDIR}/git"