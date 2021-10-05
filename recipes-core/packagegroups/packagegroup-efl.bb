DESCRIPTION = "EFL library packagegroup.  Provides a one-stop shop to incorporate EFL in full on a target (Which is what you TYPICALLY want)"
PR = "r1"

inherit packagegroup

RDEPENDS:${PN} = " \
    luajit \
    libx11 \
    libxcursor \
    libxcomposite \
    libxinerama \
    libxrandr \
    libxtst \
    libxscrnsaver \
    libxpresent \
    harfbuzz \
    fribidi \
    libjpeg-turbo \
    libpng \
    giflib \
    tiff \
    freetype \
    poppler \
    librsvg \
    eudev \
    libsndfile1 \
    gstreamer1.0 \
	gstreamer1.0-libav \
	gstreamer1.0-plugins-base \
	gstreamer1.0-plugins-good \
	gstreamer1.0-plugins-bad \
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
