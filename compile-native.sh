#!/bin/sh

CXX="g++ -std=c++11 -shared -fPIC -O3 -Wall -Werror -I$JAVA_HOME/include/ -I$JAVA_HOME/include/linux/"
CC="gcc -fPIC -shared -Wall -Werror -I$JAVA_HOME/include/ -I$JAVA_HOME/include/linux/"

$CC src/native/src/com/bluedevel/zlib/FastDeflater.c -o src/main/resources/libfastdeflater.so -lz

