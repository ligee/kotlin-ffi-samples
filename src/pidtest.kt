
package getpid

import jnr.ffi.*
import jnr.ffi.types.pid_t


public trait LibC {
    pid_t fun getpid() : Long
    pid_t fun getppid() : Long
}

fun main(args: Array<String>) {
    val libc = LibraryLoader.create(javaClass<LibC>()).load("c")

    println("pid=${libc.getpid()} parent pid=${libc.getppid()}")
}

