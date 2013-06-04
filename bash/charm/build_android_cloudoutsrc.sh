#!/bin/sh

# Set our global parameters for compiling Android armv7-a binaries.
TOOLCHAIN="/tmp/android-toolchain/bin/arm-linux-androideabi-g++" 
CXXFLAGS="-DANDROID -Wall -O2 -funroll-loops -fexceptions -frtti -fomit-frame-pointer   -I/tmp/android-toolchain/sysroot/usr/include   -I/tmp/android-toolchain/sysroot/usr/include/relic/   -I/tmp/android-toolchain/sysroot/usr/include/relic/low   -I/tmp/android-toolchain/sysroot/usr/include/charm"
LDFLAGS="-L/tmp/android-toolchain/sysroot/usr/lib -llog" 
STATIC_LIBS="/tmp/android-toolchain/sysroot/usr/lib/libCharmRelic.a   /opt/android-ndk-r8e/sources/cxx-stl/gnu-libstdc++/4.7/libs/armeabi-v7a/libgnustl_static.a"

# Build all asymmetric tests.
${TOOLCHAIN} ${CXXFLAGS} ${LDFLAGS} TestWATERS.cpp benchWATERS.cpp ${STATIC_LIBS} -o benchWaters

${TOOLCHAIN} ${CXXFLAGS} ${LDFLAGS} TestBSWOut2.cpp benchBSWOut.cpp ${STATIC_LIBS} -o benchBSWOut

${TOOLCHAIN} ${CXXFLAGS} ${LDFLAGS} TestLWOut2.cpp benchLWOut.cpp ${STATIC_LIBS} -o benchLWOut

${TOOLCHAIN} ${CXXFLAGS} ${LDFLAGS} TestDSEOut2.cpp benchDSEOut.cpp ${STATIC_LIBS} -o benchDSEOut

${TOOLCHAIN} ${CXXFLAGS} ${LDFLAGS} TestCKRSOut2.cpp benchCKRSOut.cpp ${STATIC_LIBS} -o benchCKRSOut

${TOOLCHAIN} ${CXXFLAGS} ${LDFLAGS} TestHIBEOut2.cpp benchHIBEOut.cpp ${STATIC_LIBS} -o benchHIBEOut

${TOOLCHAIN} ${CXXFLAGS} ${LDFLAGS} TestSWOut2.cpp benchSWOut.cpp ${STATIC_LIBS} -o benchSWOut

${TOOLCHAIN} ${CXXFLAGS} ${LDFLAGS} TestBGWOut2.cpp benchBGWOut.cpp ${STATIC_LIBS} -o benchBGWOut

${TOOLCHAIN} ${CXXFLAGS} ${LDFLAGS} TestDFAOut2.cpp benchDFAOut.cpp ${STATIC_LIBS} -o benchDFAOut

# Run the scripts like this:
#./TestWATERS 100 100 fixed
#./TestBSWOut2 100 100 fixed
#./TestLWOut2 100 100 fixed
#./TestDSEOut2 100 100 fixed
#./TestCKRSOut2 100 100 fixed
#./TestHIBEOut2 100 100 fixed
#./TestSWOut2 100 100 fixed
#./TestBGWOut2 100 100 fixed
#./TestDFAOut2 100 500 1 fixed
