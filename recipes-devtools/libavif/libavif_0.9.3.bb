DESCRIPTION = "Library for encoding and decoding .avif files"
HOMEPAGE = "https://github.com/AOMediaCodec/libavif"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=eb2c3b8fcc34ed603e622fced328988f"


SRC_URI = " \
    git://github.com/AOMediaCodec/libavif.git;protocol=http;branch=master \
    "

SRCREV = "ac76fe0d16a255bccefff5bdfddd865742176d85"
S = "${WORKDIR}/git"

inherit cmake