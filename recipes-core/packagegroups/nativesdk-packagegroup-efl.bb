DESCRIPTION = "EFL nativesdk packagegroup" 
PR = "r1"

inherit packagegroup

RDEPENDS_${PN} = " \
    nativesdk-luajit \
    nativesdk-efl \
    "
    
