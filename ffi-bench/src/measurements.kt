
package ffibench.measurements


fun asserted_measure<T>(f: () -> T, assert_f: (v: T) -> Unit, repeats: Int, calibration: Long = 0): Long {
    var r = repeats - 1;
    var res = f()
    assert_f( res)
    val start = System.nanoTime()
    while (r-- > 0)
        res = f()
    val time = (System.nanoTime() - start) / 1000 - calibration;
    assert_f( res)
    return time
}

fun assert_equals<T>(v: T, expected: T) {
    assert(v == expected, "assertion failed: expected '$expected' but got '$v'")
}

fun unasserted_measure<T>(f: () -> T, repeats: Int, calibration: Long = 0): Long
        = asserted_measure( f, { (_) -> ; }, repeats, calibration)

fun assert_equals_measure<T>(f: () -> T, expected: T, repeats: Int, calibration: Long = 0): Long
        = asserted_measure( f, { (v) -> assert_equals(v, expected) }, repeats, calibration)

fun dummy() {}

