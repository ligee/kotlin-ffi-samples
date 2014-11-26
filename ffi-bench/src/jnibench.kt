
package ffibench.jni

import ffibench.measurements.*

public class LibFfiBench {
    native fun funcIntInt(param: Int): Int = null!!
    native fun funcStringInt(param: String): Int = null!!
    native fun funcIntString(param: Int): String = null!!
    // native fun func_struct1_int(param: struct1): Int = null!!
    // native fun func_int_struct1(param: Int): struct1 = null!!
    // native fun func_callback_int(cb: Callback_int_int): Int = null!!

    {
        System.loadLibrary("kotlinffibench")
    }
}

class struct1(i: Int = 0, s: String = String()) {
    public var int_field: Int = i
    public var string_field: String = s
}

fun measureAll(repeats: Int) {
    val libffi = LibFfiBench()
    val calibration: Long = 0 //unasserted_measure({ dummy() }, repeats)

    println("JNI results ($repeats repeats, calibrated to ${calibration}us)")

    println("int->int: ${assert_equals_measure({
        libffi.funcIntInt(33)
    }, 33, repeats, calibration)}us")

    println("string->int: ${assert_equals_measure({ libffi.funcStringInt("from kotlin") }, 11, repeats, calibration)}us")

    println("int->string: ${assert_equals_measure({ libffi.funcIntInt(1) }, "greetings from native", repeats, calibration)}us")
}
