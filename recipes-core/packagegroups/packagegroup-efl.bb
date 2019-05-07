DESCRIPTION = "EFL library packagegroup.  Provides a one-stop shop to incorporate EFL in full on a target (Which is what you TYPICALLY want)" 
PR = "r1"

inherit packagegroup

RDEPENDS_${PN} = " \
    efreet-trash \
    efreet-mime \
    libeet \
    libefreet \
    ecore-audio \
    ecore-input-evas \
    ecore-input \
    ecore-imf-evas \
    ecore-imf \
    ecore-file \
    ecore-con \
    ecore-ipc \
    ecore-x \
    ecore-evas \
    libemotion \
    eo \
    ecore \
    edje \
    eet \
    eeze \
    efreet \
    eina \
    eio \
    embryo \
    emotion \
    ethumb \
    evas \
    eldbus \
    elua \
    elementary \
    elementary-dev \
    elementary-themes \
    elementary-configs \
    elementary-tests \
    "
