
package ffibench.swig

import kotlin.platform.platformStatic
import ffibench.measurements.*

public class benchFunctionsJNI {
    class object {
        {
            System.loadLibrary("bench_functions_swig")
        }

        public native platformStatic fun kffis_func_int_int(jarg1: Int): Int
        public native platformStatic fun kffis_func_string_int(jarg1: String): Int
        public native platformStatic fun kffis_func_int_string(jarg1: Int): String
        public native platformStatic fun kffis_struct1_int_field_set(jarg1: Long, jarg1_: kffis_struct1, jarg2: Int): Unit
        public native platformStatic fun kffis_struct1_int_field_get(jarg1: Long, jarg1_: kffis_struct1): Int
        public native platformStatic fun kffis_struct1_string_field_set(jarg1: Long, jarg1_: kffis_struct1, jarg2: String): Unit
        public native platformStatic fun kffis_struct1_string_field_get(jarg1: Long, jarg1_: kffis_struct1): String
        public native platformStatic fun new_kffis_struct1(): Long
        public native platformStatic fun delete_kffis_struct1(jarg1: Long): Unit
        public native platformStatic fun kffis_func_struct1_int(jarg1: Long, jarg1_: kffis_struct1): Int
        public native platformStatic fun kffis_func_int_struct1(jarg1: Int): Long
        public native platformStatic fun kffis_func_callback_int(jarg1: Long): Int
    }
}

public class libffi {
    class object {
        public fun kffis_func_int_int(param: Int): Int {
            return benchFunctionsJNI.kffis_func_int_int(param)
        }

        public fun kffis_func_string_int(param: String): Int {
            return benchFunctionsJNI.kffis_func_string_int(param)
        }

        public fun kffis_func_int_string(param: Int): String {
            return benchFunctionsJNI.kffis_func_int_string(param)
        }

        public fun kffis_func_struct1_int(param: kffis_struct1): Int {
            return benchFunctionsJNI.kffis_func_struct1_int(kffis_struct1.getCPtr(param), param)
        }

        public fun kffis_func_int_struct1(param: Int): kffis_struct1? {
            val cPtr = benchFunctionsJNI.kffis_func_int_struct1(param)
            return if ((cPtr == 0L)) null else kffis_struct1(cPtr, false)
        }

        public fun kffis_func_callback_int(cb: SWIGTYPE_p_f_int__int): Int {
            return benchFunctionsJNI.kffis_func_callback_int(SWIGTYPE_p_f_int__int.getCPtr(cb))
        }
    }

}


public fun kffis_struct1(): kffis_struct1 {
    return kffis_struct1(benchFunctionsJNI.new_kffis_struct1(), true)
}

public class kffis_struct1(private var swigCPtr: Long, protected var swigCMemOwn: Boolean) {

    protected fun finalize() {
        delete()
    }

    synchronized public fun delete() {
        if (swigCPtr != 0L) {
            if (swigCMemOwn) {
                swigCMemOwn = false
                benchFunctionsJNI.delete_kffis_struct1(swigCPtr)
            }
            swigCPtr = 0
        }
    }

    public var int_field: Int
        set(value: Int) { benchFunctionsJNI.kffis_struct1_int_field_set(swigCPtr, this, value) }
        get(): Int { return benchFunctionsJNI.kffis_struct1_int_field_get(swigCPtr, this) }

    public var string_field: String
        set(value: String) { benchFunctionsJNI.kffis_struct1_string_field_set(swigCPtr, this, value) }
        get(): String { return benchFunctionsJNI.kffis_struct1_string_field_get(swigCPtr, this) }

    class object {

        public fun getCPtr(obj: kffis_struct1?): Long {
            return if ((obj == null)) 0 else obj.swigCPtr
        }
    }

}


public class SWIGTYPE_p_f_int__int(cPtr: Long = 0, futureUse: Boolean = false) {
    private var swigCPtr: Long = cPtr

    class object {

        public fun getCPtr(obj: SWIGTYPE_p_f_int__int?): Long {
            return if ((obj == null)) 0 else obj.swigCPtr
        }
    }
}


fun measureAll(repeats: Int) {
    val calibration: Long = 0 //unasserted_measure({ dummy() }, repeats)

    println("SWIG results ($repeats repeats, calibrated to ${calibration}us)")

    println("int->int: ${assert_equals_measure({ libffi.kffis_func_int_int(33) }, 33, repeats, calibration)}us")

    val from_str = "from kotlin"
    println("string->int: ${assert_equals_measure({ libffi.kffis_func_string_int(from_str) }, 11, repeats, calibration)}us")

    println("int->string: ${assert_equals_measure({ libffi.kffis_func_int_int(1) }, "greetings from native", repeats, calibration)}us")

    var st1 = libffi.kffis_func_int_struct1(22)
    assert_equals( st1!!.int_field, 42)
    assert_equals( st1!!.string_field , "greetings from native")
    println("int->struct1: ${unasserted_measure({ libffi.kffis_func_int_struct1(22) }, repeats, calibration)}us")

    st1!!.int_field = 10
    st1!!.string_field = "hi back"
    assert_equals( libffi.kffis_func_struct1_int(st1!!), 10 + 7)
    println("struct1->int: ${unasserted_measure({ libffi.kffis_func_struct1_int(st1!!) }, repeats, calibration)}us")

//    val callback = callback1()
//    println("callback->int: ${assert_equals_measure({ libffi.kffis_func_callback_int(callback) }, 1, repeats, calibration)}us")

}
