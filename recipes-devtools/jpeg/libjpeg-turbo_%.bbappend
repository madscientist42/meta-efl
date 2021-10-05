# Add nativesdk support to help with tools that need this...
BBCLASSEXTEND += " nativesdk"

# Extend the class dependencies to including nasm-native...
DEPENDS:class-nativesdk += " \
    nasm-native \
    "
