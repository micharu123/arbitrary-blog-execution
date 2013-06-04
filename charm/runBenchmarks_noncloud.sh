#!/bin/sh

# The following script will run our binaries and output the time it took to execute 
# said binaries.

# An array of binaries for cloudNonOutsrc.
BINS[0]="benchWatersNon"
BINS[1]="benchCKRSNon"
BINS[2]="benchHIBENon"
BINS[3]="benchLWNon"
BINS[4]="benchBSWNon"
BINS[5]="benchSWNon"
BINS[6]="benchBGWNon"
BINS[7]="benchDSENon"
BINS[8]="benchDFANon"

for i in 5
do
    START=$(date +%s)
    printf "\nStarting ${BINS[$i]} execution! Start time: ${START}.\n"

    # Do something. No DFA, no DSE.
    ./${BINS[$i]} 10 10 fixed

    END=$(date +%s)
    DIFF=$(( $END - $START ))

    printf "It took ${BINS[$i]} ${DIFF} seconds to execute. End time: ${END}.\n\n"
done

