Kotlin FFI samples
==================

Contains samples of FFI usage from Kotlin:

1. _pidtest_ - direct port of JNR GetPid sample (https://github.com/jnr/jnr-ffi-examples/tree/master/getpid/src/main/java/getpid)

2. _armatest_ - example of using sample wrapper around Armadillo (http://arma.sourceforge.net/) lib via JNR,
   reproducing tutorial sample from the lib. (wrapper could be taken from https://github.com/ligee/armadillo-4ffi, compiled
   library should be placed into the folder where jnr-ffi loader may find it, e.g. in the project root)

3. _ffi-bench_ - benchmarking various FFI approaches:
     - JNI - _not implemented yet_
     - JNR
     - BridJ - _not implemented yet_

*Note:* - at the moment native libs for JNR should be copied/symlinked to the project root folder

