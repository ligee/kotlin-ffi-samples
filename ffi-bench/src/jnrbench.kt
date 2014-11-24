
package ffibench.jnr

import jnr.ffi.*
import jnr.ffi.types.*

public trait LibFfiBench {
    fun kffis_func_int_int(param: Int): Int
    fun kffis_func_string_int(param: String): Int
    fun kffis_func_int_string(param: Int): String
    fun kffis_func_struct1_int(param: struct1): Int
    fun kffis_func_int_struct1(param: Int): struct1
    fun kffis_func_callback_int(cb: Callback_int_int): Int
}

class struct1(runtime: jnr.ffi.Runtime) : Struct(runtime) {
    public var int_field: Int = 0
    public var string_field: String = String()
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

fun measure<T>(f: () -> T, repeats: Int): Long {
    val start = System.nanoTime()
    var r = repeats;
    while (r-- > 0) f()
    return (System.nanoTime() - start) / 1000;
}

fun assert_equals<T>(f: () -> T, v: T) {
    val res = f()
    assert(res == v, "assertion failed: expected '$v' but got '$res'")
}

fun assert_and_measure<T>(f: () -> T, v: T, repeats: Int): Long {
    assert_equals(f, v)
    return measure( f, repeats )
}

fun measureAll(repeats: Int) {
    val libffi = LibraryLoader.create(javaClass<LibFfiBench>()).load("kotlinffibench")

    println("int->int: ${assert_and_measure({ libffi.kffis_func_int_int(33) }, 33, repeats)}us")

    println("string->int: ${assert_and_measure({ libffi.kffis_func_string_int("from kotlin") }, 11, repeats)}us")

    println("int->string: ${assert_and_measure({ libffi.kffis_func_int_int(1) }, "greetings from native", repeats)}us")

    var st1 = libffi.kffis_func_int_struct1(22)
    assert_equals( { () -> st1.int_field }, 42)
    assert_equals( { () -> st1.string_field }, "greetings from native")
    println("int->struct1: ${measure({ libffi.kffis_func_int_struct1(22) }, repeats)}us")

    st1.int_field = 10
    st1.string_field = "hi back"
    assert_equals( { () -> libffi.kffis_func_struct1_int(st1) }, 10 + 7)
    println("struct1->int: ${measure({ libffi.kffis_func_struct1_int(st1) }, repeats)}us")

    val callback = callback1()
    println("callback->int: ${assert_and_measure({ libffi.kffis_func_callback_int(callback) }, 1, repeats)}us")

}