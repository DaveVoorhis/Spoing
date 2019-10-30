#!/bin/sh

echo 'Clean'
rm lib/spoing/*
mkdir lib/spoing

echo 'Build swt_linux'
pushd ../swtNative/swt_linux
ant -S
popd
cp ../swtNative/swt_linux/lib/* ../_Deploy/lib/swt/linux_64

echo 'Build swt_macos'
pushd ../swtNative/swt_macos
ant -S
popd
cp ../swtNative/swt_macos/lib/* ../_Deploy/lib/swt/macos_64

echo 'Build swt_win'
pushd ../swtNative/swt_win
ant -S
popd
cp ../swtNative/swt_win/lib/* ../_Deploy/lib/swt/win_64

echo 'Build spoingPlatformWeb'
pushd ../spoingPlatformWeb
ant -S
popd
cp ../spoingPlatformWeb/lib/* ../_Deploy/lib/spoing

echo 'Build spoingPlatformDesktop'
pushd ../spoingPlatformDesktop
ant -S
popd
cp ../spoingPlatformDesktop/lib/* ../_Deploy/lib/spoing

echo 'Build spoingPlatformAll'
pushd ../spoingPlatformAll
ant -S
popd
cp ../spoingPlatformAll/lib/* ../_Deploy/lib/spoing

echo 'Build spoingUtilities'
pushd ../spoingUtilities
ant -S
popd
cp ../spoingUtilities/lib/* ../_Deploy/lib/spoing

echo 'Build launchWeb'
pushd ../launchWeb
ant -S
popd
