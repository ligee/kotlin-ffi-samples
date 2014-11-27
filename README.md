Kotlin FFI samples
==================

Contains samples of FFI usage from Kotlin:

1. _pidtest_ - direct port of JNR GetPid sample (https://github.com/jnr/jnr-ffi-examples/tree/master/getpid/src/main/java/getpid)

2. _armatest_ - example of using sample wrapper around Armadillo (http://arma.sourceforge.net/) lib via JNR,
   reproducing tutorial sample from the lib. (wrapper could be taken from https://github.com/ligee/armadillo-4ffi, compiled
   library should be placed into the folder where jnr-ffi loader may find it, e.g. in the project root)

3. _ffi-bench_ - benchmarking various FFI approaches:
     - JNI
     - JNR
     - BridJ - _works with some problems, namely value extraction from struct doesn't work_

Results:
--------

JNR results (10000 repeats, calibrated to 0us)

    int->int: 2732us
    string->int: 48087us
    int->string: 2288us
    int->struct1: 19321us
    struct1->int: 7321us
    callback->int: 11167us

JNI results (10000 repeats, calibrated to 0us)

    int->int: 2009us
    string->int: 6210us
    int->string: 2756us
    int->struct1: 17029us
    struct1->int: 8899us
    callback->int: 7004us

BridJ results (10000 repeats, calibrated to 0us)

    int->int: 2158us
    string->int: 50564us
    int->string: 2098us
    int->struct1: 11100us
    struct1->int: 5650us
    callback->int: 10994us
