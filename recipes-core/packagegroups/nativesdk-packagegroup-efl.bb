DESCRIPTION = "EFL nativesdk packagegroup" 
PR = "r1"

inherit packagegroup

RDEPENDS_${PN} = " \
    nativesdk-efreet-trash \
    nativesdk-efreet-mime \
    nativesdk-libeet \
    nativesdk-libefreet \
    nativesdk-ecore-audio \
    nativesdk-ecore-input-evas \
    nativesdk-ecore-input \
    nativesdk-ecore-imf-evas \
    nativesdk-ecore-imf \
    nativesdk-ecore-file \
    nativesdk-ecore-con \
    nativesdk-ecore-ipc \
    nativesdk-ecore-x \
    nativesdk-ecore-evas \
    nativesdk-libemotion \
    nativesdk-eo \
    nativesdk-ecore \
    nativesdk-edje \
    nativesdk-eet \
    nativesdk-eeze \
    nativesdk-efreet \
    nativesdk-eina \
    nativesdk-eio \
    nativesdk-embryo \
    nativesdk-emotion \
    nativesdk-ethumb \
    nativesdk-evas \
    nativesdk-eldbus \
    nativesdk-elua \
    nativesdk-elementary \
    nativesdk-elementary-dev \
    nativesdk-elementary-themes \
    nativesdk-elementary-configs \
    "

    
