
package ffibench.jni

import ffibench.measurements.*

public class LibFfiBench {
    {
        System.loadLibrary("kotlinffibench")
    }

    native fun funcIntInt(param: Int): Int
    native fun funcStringInt(param: String): Int
    native fun funcIntString(param: Int): String
    native fun funcStruct1Int(param: struct1): Int
    native fun funcIntStruct1(param: Int): struct1
    native fun funcCallbackInt(cb: callback1): Int
}

class struct1(i: Int = 0, s: String = String()) {
    public var int_field: Int = i
    public var string_field: String = s
}

public class callback1 {
    public fun callback(param: Int): Int {
        return param % 42 + 1;
    }
}

fun measureAll(repeats: Int) {
    val libffi = LibFfiBench()
    val calibration: Long = 0 //unasserted_measure({ dummy() }, repeats)

    println("JNI results ($repeats repeats, calibrated to ${calibration}us)")

    println("int->int: ${assert_equals_measure({ libffi.funcIntInt(33) }, 34, repeats, calibration)}us")

    println("string->int: ${assert_equals_measure({ libffi.funcStringInt("from kotlin") }, 11, repeats, calibration)}us")

    println("int->string: ${assert_equals_measure({ libffi.funcIntString(1) }, "greetings from native", repeats, calibration)}us")

    var st1 = libffi.funcIntStruct1(22)
    assert_equals( st1.int_field, 42)
    assert_equals( st1.string_field , "greetings from native")
    println("int->struct1: ${unasserted_measure({ libffi.funcIntStruct1(22) }, repeats, calibration)}us")

    st1.int_field = 10
    st1.string_field = "hi back"
    assert_equals( libffi.funcStruct1Int(st1), 10 + 7)
    println("struct1->int: ${unasserted_measure({ libffi.funcStruct1Int(st1) }, repeats, calibration)}us")

    val callback = callback1()
    println("callback->int: ${assert_equals_measure({ libffi.funcCallbackInt(callback) }, 1, repeats, calibration)}us")
}
