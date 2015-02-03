import jnr.ffi.*
import jnr.ffi.types.*
import jnr.ffi.annotations.Out
import jnr.ffi.annotations.Transient
import jnr.ffi.Struct.SignedLong
import jnr.ffi.Struct.time_t

/**
 * Retrieves the current system time using gettimeofday(3)
 */
public class Gettimeofday {
    public class Timeval(runtime: Runtime) : Struct(runtime) {
        public val tv_sec: time_t = time_t()
        public val tv_usec: SignedLong = SignedLong()
    }

    public trait LibC {
        public fun gettimeofday(Out Transient tv: Timeval, unused: Pointer?): Int
    }

    class object {

        public fun main(args: Array<String>) {
            val libc = LibraryLoader.create(javaClass<LibC>()).load("c")
            val runtime = Runtime.getRuntime(libc)

            val tv = Timeval(runtime)
            libc.gettimeofday(tv, null)
            System.out.println("gettimeofday tv.tv_sec=" + tv.tv_sec.get() + " tv.tv_usec=" + tv.tv_usec.get())
        }
    }
}

fun main(args: Array<String>) = Gettimeofday.main(args)
