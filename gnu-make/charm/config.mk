# Automatically generated by configure - do not modify
# Configured with: './configure.sh' '--enable-pairing-relic'
all:
prefix=/tmp/android-toolchain/sysroot/usr
bindir=${prefix}/bin
libdir=${prefix}/lib
incdir=${prefix}/include
mandir=${prefix}/share/man
datadir=${prefix}/share/charm
sysconfdir=${prefix}/etc
docdir=${prefix}/share/doc/charm
confdir=${prefix}/etc/charm
ARCH=x86
VERSION=0.42
CONFIG_UNAME_RELEASE=""
TOOLS=
MAKE=make
INSTALL=install
INSTALL_DIR=install -d -m0755 -p
INSTALL_DATA=install -m0644 -p
INSTALL_PROG=install -m0755 -p
INT_MOD=yes
ECC_MOD=yes
PAIR_MOD=yes
BUILD_MIRACL=no
BUILD_RELIC=yes
BUILD_PBC=no
underlib=relic
CURVE=mnt
CC=/tmp/android-toolchain/bin/arm-linux-androideabi-gcc
CXX=/tmp/android-toolchain/bin/arm-linux-androideabi-g++
AR=/tmp/android-toolchain/bin/arm-linux-androideabi-ar
AS=/tmp/android-toolchain/bin/arm-linux-androideabi-as
LD=/tmp/android-toolchain/bin/arm-linux-androideabi-ld
NM=/tmp/android-toolchain/bin/arm-linux-androideabi-nm
RANLIB=/tmp/android-toolchain/bin/arm-linux-androideabi-ranlib
HOST_CC=gcc
LIBTOOL=
INCLUDES=-I./relic/ -I/tmp/android-toolchain/sysroot/usr/include/relic -I/tmp/android-toolchain/sysroot/usr/include/relic/low
CHARM_LIB=libCharmRelic
CFLAGS=--sysroot=/tmp/android-toolchain/sysroot -DBUILD_RELIC=1 -DBUILD_MIRACL=0 -O2 -g -Wall  -fstack-protector-all -Wendif-labels -march=armv7-a -mfloat-abi=softfp -DANDROID -fomit-frame-pointer -fno-strict-aliasing -finline-limit=64 -ffunction-sections -funwind-tables -D_GNU_SOURCE -pipe -std=c99
CXXFLAGS=--sysroot=/tmp/android-toolchain/sysroot -DBUILD_RELIC=1 -DBUILD_MIRACL=0 -O2 -g -Wall  -fstack-protector-all -Wendif-labels
HELPER_CFLAGS=
LDFLAGS=-Wl,--fix-cortex-a8 -Wl,--no-undefined -Wl,-z,noexecstack -Wl,--fix-cortex-a8 
ARLIBS_BEGIN=
ARLIBS_END=
LIBS+=
LIBS_TOOLS+=
