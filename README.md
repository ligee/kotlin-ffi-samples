kjnr-samples
============

Kotlin JNR samples

Contains 2 samples of JNR usage from Kotlin:

1. _pidtest_ - direct port of JNR GetPid sample (https://github.com/jnr/jnr-ffi-examples/tree/master/getpid/src/main/java/getpid)

2. _armatest_ - example of using sample wrapper around Armadillo (http://arma.sourceforge.net/) lib,
   reproducing tutorial sample from the lib. (wrapper could be taken from https://github.com/ligee/armadillo-4ffi, compiled
   library should be placed into the folder where jnr-ffi loader may find it, e.g. in the project root)
