#!/bin/bash

DEVICE=camera
VENDOR=oplus

export originalPath=$(pwd)

if [ $(pwd) == "${ANDROID_BUILD_TOP}" ] ; then export originalPath=$(pwd)/device/$VENDOR/$DEVICE/patches/ ; fi

applyPatches() {
    export counter=0

    cd $originalPath
    for i in $(ls -d */); do
        path=$(tr _ / <<< "$i")
        cd $originalPath
        cd ./../../../../"$path"
        git am "$originalPath"/"$i"*.patch
        [[ $? -ne 0 ]] && (( counter++ ))
        git am --abort &> /dev/null
    done
    
    echo "* Missed patches: $counter"
}

applyPatches

echo -e "\e[36m* Apply patches done!\e[m"

sleep 2
