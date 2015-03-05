
package ffibench.jnrcb

import jnr.ffi.types.*
import ffibench.measurements.*
import jnr.ffi.Pointer
import jnr.ffi.mapper.ToNativeContext
import jnr.ffi.mapper.FromNativeContext
import jnr.ffi.mapper.FromNativeConverter
import jnr.ffi.mapper.ToNativeConverter

public class KTCallBack() {

    public trait Callback_int_int {
        jnr.ffi.annotations.Delegate
        public fun callback(param: Int): Int
    }

    class object {

        ToNativeConverter.ToNative(nativeType = javaClass<Callback_int_int>())
        public fun toNative(fn: ((Int) -> Int)?, context: ToNativeContext): Callback_int_int? {
            return if (fn != null) object : Callback_int_int { override fun callback(param: Int): Int = fn(param) } else null
        }

        FromNativeConverter.FromNative(nativeType = javaClass<Callback_int_int>())
        public fun fromNative(value: Callback_int_int?, context: FromNativeContext): kotlin.Function1<Int,Int>? {
            return null
        }
    }
}

public trait LibFfiBench {
    fun kffis_func_callback_int(cb: KTCallBack.Callback_int_int): Int
}

fun LibFfiBench.kffis_func_callback_int_w(cb: (Int) -> Int): Int =
        kffis_func_callback_int(object : KTCallBack.Callback_int_int { override fun callback(param: Int): Int = cb(param) })

fun measureAll(repeats: Int) {
    val libffi = jnr.ffi.LibraryLoader.create(javaClass<LibFfiBench>()).load("kotlinffibench")
    val calibration: Long = 0 //unasserted_measure({ dummy() }, repeats)

    println("JNR CB results ($repeats repeats, calibrated to ${calibration}us)")

    val callback = { (a: Int) -> a % 42 + 1  }

    println("callback->int: ${assert_equals_measure({ libffi.kffis_func_callback_int_w(callback) }, 1, repeats, calibration)}us")
}
