
package ffibench.jnr

import jnr.ffi.types.*
import ffibench.measurements.*

public trait LibFfiBench {
    fun kffis_func_int_int(param: Int): Int
    fun kffis_func_string_int(param: String): Int
    fun kffis_func_int_string(param: Int): String
    fun kffis_func_struct1_int(jnr.ffi.annotations.In param: struct1): Int
    fun kffis_func_int_struct1(param: Int): struct1
    fun kffis_func_callback_int(cb: Callback_int_int): Int
}

open class struct1(runtime: jnr.ffi.Runtime) : jnr.ffi.Struct(runtime) {
    public val int_field: jnr.ffi.Struct.Signed32 = super.Signed32()
    public open val string_field: jnr.ffi.Struct.AsciiStringRef = super.AsciiStringRef(256)
}

public trait Callback_int_int {
    jnr.ffi.annotations.Delegate
    public fun callback(param: Int): Int
}

public class callback1: Callback_int_int{
    public override fun callback(param: Int): Int {
        return param % 42 + 1;
    }
}

fun measureAll(repeats: Int) {
    val libffi = jnr.ffi.LibraryLoader.create(javaClass<LibFfiBench>()).load("kotlinffibench")

    val calibration: Long = 0 //unasserted_measure({ dummy() }, repeats)

    println("JNR results ($repeats repeats, calibrated to ${calibration}us)")

    println("int->int: ${assert_equals_measure({ libffi.kffis_func_int_int(33) }, 34, repeats, calibration)}us")

    val from_str = "from kotlin"
    println("string->int: ${assert_equals_measure({ libffi.kffis_func_string_int(from_str) }, 11, repeats, calibration)}us")

    println("int->string: ${assert_equals_measure({ libffi.kffis_func_int_string(1) }, "greetings from native", repeats, calibration)}us")

    var st1 = libffi.kffis_func_int_struct1(22)
    assert_equals( st1.int_field.get(), 42)
    assert_equals( st1.string_field.get() , "greetings from native")
    println("int->struct1: ${unasserted_measure({ libffi.kffis_func_int_struct1(22) }, repeats, calibration)}us")

    val runtime = jnr.ffi.Runtime.getRuntime(libffi)
    val st2 = struct1(runtime)
    st2.int_field.set(15)
    st2.string_field.set("hi back")
    assert_equals( libffi.kffis_func_struct1_int(st2), 22)
    println("struct1->int: ${unasserted_measure({ libffi.kffis_func_struct1_int(st2) }, repeats, calibration)}us")

    val callback = callback1()
    println("callback->int: ${assert_equals_measure({ libffi.kffis_func_callback_int(callback) }, 1, repeats, calibration)}us")

    println("struct2 size: ${jnr.ffi.Struct.size(st2)}")
}