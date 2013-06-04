#!/bin/sh

# The following script will run our binaries and output the time it took to execute 
# said binaries.

# An array of binaries.
BINS[0]="benchWaters"
BINS[1]="benchBSWOut"
BINS[2]="benchLWOut"
BINS[3]="benchSWOut"
BINS[4]="benchBGWOut"
BINS[5]="benchCKRSOut"
BINS[6]="benchHIBEOut"
BINS[7]="benchDSEOut"
BINS[8]="benchDFAOut"

for i in 3
do
    START=$(date +%s)
    printf "\nStarting ${BINS[$i]} execution! Start time: ${START}.\n"

    # Do something. No DFA, no DSE.
    ./${BINS[$i]} 10 10 fixed

    END=$(date +%s)
    DIFF=$(( $END - $START ))

    printf "It took ${BINS[$i]} ${DIFF} seconds to execute. End time: ${END}.\n\n"
done

