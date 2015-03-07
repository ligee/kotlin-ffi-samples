
package ffibench

fun main(args: Array<String>) {
    val repeats = 10000
    jnrcb.measureAll(repeats)
    jnr.measureAll(repeats)
    jni.measureAll(repeats)
    bridj.measureAll(repeats)
//    swig.measureAll(repeats)
}