
package ffibench.bridj

import org.bridj.*
import org.bridj.ann.*
import org.bridj.Pointer.*
import ffibench.measurements.*

Library("kotlinffibench")
public class LibFfiBench {
    public native fun kffis_func_int_int(param: Int): Int = null!!
    public native fun kffis_func_string_int(param: Pointer<Byte>): Int = null!!
    public native fun kffis_func_int_string(param: Int): Pointer<Byte> = null!!
    public native fun kffis_func_struct1_int(param: Pointer<struct1>): Int = null!!
    public native fun kffis_func_int_struct1(param: Int): Pointer<struct1> = null!!
    //public native fun kffis_func_callback_int(cb: Callback_int_int): Int = null!!

    {
        BridJ.register()
    }
}

class struct1(pointer: Pointer<StructObject>? = null) : StructObject(pointer) {
    Field(0) public fun int_field(v: Int): struct1 { this.io.setIntField(this, 0, v); return this }
    Field(0) public fun int_field(): Int = this.io.getIntField(this, 0)
    Field(1) public fun string_field(s: String): struct1 { this.io.setPointerField(this, 1, pointerToCString(s)); return this }
    Field(1) public fun string_field(): Pointer<Byte> = this.io.getPointerField(this, 1)
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
    val libffi = LibFfiBench()

    val calibration: Long = 0 //unasserted_measure({ dummy() }, repeats)

    println("BridJ results ($repeats repeats, calibrated to ${calibration}us)")

    println("int->int: ${assert_equals_measure({ libffi.kffis_func_int_int(33) }, 33, repeats, calibration)}us")

    println("string->int: ${assert_equals_measure({ libffi.kffis_func_string_int(pointerToCString("from kotlin")) }, 11, repeats, calibration)}us")

    println("int->string: ${assert_equals_measure({ libffi.kffis_func_int_int(1) }, "greetings from native", repeats, calibration)}us")

    var st1 = libffi.kffis_func_int_struct1(22)
 //   assert_equals( st1.get().int_field(), 42)
 //   assert_equals( st1.get().string_field(), "greetings from native")
    println("int->struct1: ${unasserted_measure({ libffi.kffis_func_int_struct1(22) }, repeats, calibration)}us")

//    st1.get().int_field(10)
//    st1.get().string_field("hi back")
//    assert_equals( libffi.kffis_func_struct1_int(st1), 10 + 7)
    println("struct1->int: ${unasserted_measure({ libffi.kffis_func_struct1_int(st1) }, repeats, calibration)}us")
/*
    val callback = callback1()
    println("callback->int: ${assert_equals_measure({ libffi.kffis_func_callback_int(callback) }, 1, repeats, calibration)}us")
*/
}