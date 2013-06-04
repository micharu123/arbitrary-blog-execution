#!/bin/bash

set -x
export VERBOSE=0
path_to_relic=$1
path_to_inc=/tmp/android-toolchain/sysroot/usr/include/relic
path_to_lib=/tmp/android-toolchain/sysroot/usr/lib

relic_lib=lib
relic_inc=$path_to_relic/include
my_relic_inc=include

# To build on android, make sure you put the android-toolchain on your path!
# Also make sure you can access the toolchain-android-standalone-toolchain.cmake file.

# build using GMP backend and link statically 
cmake -DCMAKE_TOOLCHAIN_FILE=/home/micharu123/Documents/auto-tools-droid/RELIC/toolchain-android-standalone-toolchain.cmake -DWORD=32 -DSEED=UDEV -DOPSYS=DROID -DVERBS=off -DDEBUG=off -DTRACE=off -DSHLIB=off -DWITH="ALL" -DCHECK=off -DARITH=easy -DBENCH=0 -DTESTS=0 -DSTBIN=off -DFP_METHD="BASIC;COMBA;COMBA;MONTY;LOWER;MONTY" -DFP_QNRES=off -DEC_METHD="PRIME" -DPC_METHD="PRIME" -DEP_METHD="BASIC;LWNAF;COMBS;INTER" -DPP_METHD="INTEG;INTEG;LAZYR;OATEP" -DFP_PRIME=256 -DEP_KBLTZ=on -DALLOC=DYNAMIC -DBN_PRECI=256 -DCOMP="-O2 -funroll-loops -fomit-frame-pointer" -DARCH=ARM $path_to_relic/

make
install -d $path_to_inc
install -d $path_to_inc/low
install -d $path_to_lib
# install the lib files
install -m 0644 $relic_lib/* $path_to_lib
# install header files for relic
install -m 0644 $relic_inc/*.h $path_to_inc
install -m 0644 $my_relic_inc/*.h $path_to_inc
install -m 0644 $relic_inc/low/*.h $path_to_inc/low
set +x
