DESCRIPTION = "Enlightenment Desktop Environment Set"
PR = "r1"

inherit packagegroup

# FIXME -- Peeled out for now because it doesn't build right:
#
# epour
#
RDEPENDS:${PN} = " \
    enlightenment \
    entrance \
    econnman \
    ecrire \
    ephoto \
    equate \
    eruler \
    evisum \
    express \
    rage \
    terminology \
    "
