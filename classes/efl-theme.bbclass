# Add helper support for an Edje theme fileset to be installed on the target.
#
# Presume a few overrideable defaults and that each .edj at toplevel of the
# ${S}/theme directory is an installable theme and that the recipe's install
# specifies the whole lot.  USUALLY, this will be a theme named "default",
# but it could be different.
#
# This is fine because unless someone overrides it on us, we'll be stuffing
# them in /usr/share/efl-themes/${PN} which equates to having subsets of
# that theming in place.  It will also be packaged as part of the main FILES
# declaration as typically this would be the package- but you can make an
# app that has a theme and the code and it will do the right thing as well,
# giving you a default theme for that app.

EFL_THEME_SRC ?= "${S}/theme"
EFL_THEME_PATH ?= "/usr/share/efl-themes"
EFL_THEME_DEST = "${D}${EFL_THEME_PATH}/${PN}"

# If they've not got a build dir specified
B ?= "${WORKDIR}/build"

# Require the native install of EFL for this recipe.  We need edje_cc to assemble things with.
DEPENDS += "efl-native"

# Compile the theme as defined above or redefined by the user in their recipe...
compile_efl_theme() {
    for theme in ${EFL_THEME_SRC}/*.edc ; do
        theme=$(basename $theme .edc)
        ${RECIPE_SYSROOT_NATIVE}/usr/bin/edje_cc -fd ${EFL_THEME_SRC}/fonts -id ${EFL_THEME_SRC}/images ${EFL_THEME_SRC}/$theme.edc ${B}/$theme.edj
    done
}
do_compile[postfuncs] += " compile_efl_theme"

# Install the theme as defined above or redefined by the user in their recipe...
install_efl_theme() {
    install -d ${EFL_THEME_DEST}
    install -m 0644 ${B}/*.edj ${EFL_THEME_DEST}
}
do_install[postfuncs] += " install_efl_theme"

FILES_${PN} += "${EFL_THEME_PATH}"