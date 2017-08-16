# fastzlib
Fast zlib compression on the jvm

## Background

Currently the JVM provides a bundled version of zlib. Intel and Cloudflare have both provided customised tuned
codebases for the x86 platforms. Unfortunately, you cannot easily override this, which will hopefully be fixed
in jdk9. Nonetheless, for anyone not on jdk9, you cannot take advantage of faster compression options which
are available.

So, this project makes that possible.

## Setting up

This requires some extra work over and above a normal JAR import to get it all working.

1. Compile zlib for your platform, https://github.com/cloudflare/zlib
2. Compile the native Java bindings for this zlib (code assumes zlib 1.2.8) - use `./compile-native.sh`.
   You will need a C compiler, the JAVA_HOME environment variable set up, and a 
3. Use gradle to compile the code into a JAR
4. Run the target code, providing LD_LIBRARY_PATH environment variable on the java command line to allow java
   to find the zlib implementation.

## Results

Results in a 30+% reduction in compression time for the same quality level.

Proper benchmark results pending.


