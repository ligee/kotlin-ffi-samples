
package ffibench

fun main(args: Array<String>) {
    val repeats = 10000
    jnr.measureAll(repeats)
}