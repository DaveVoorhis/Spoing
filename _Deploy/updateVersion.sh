#!/bin/bash

oldVersion=1.000
newVersion=1.001

sed -i '' -e "s/return $oldVersion;/return $newVersion;/" ../Demo/src/org/reldb/spoing/demo/version/Version.java
sed -i '' -e "s/Version=$oldVersion/Version=$newVersion/" ../_Deploy/version.txt

echo "Be sure to use 'ant' to build new libraries."
