
package armatest

import jnr.ffi.*
import jnr.ffi.Pointer

public trait LibArma4ffi {
    fun release_mat(mat: Pointer): Unit
    fun randu_mat(rows: Int, cols: Int): Pointer
    fun mul_mat(a: Pointer, b: Pointer): Pointer
    fun t_mat(mat: Pointer): Pointer
    fun print_mat(mat: Pointer): Unit
}

fun main(args: Array<String>) {
    val libkarma = LibraryLoader.create(javaClass<LibArma4ffi>()).load("arma4ffi")

    println("start")

    val A = libkarma.randu_mat(4,5)
    val B = libkarma.randu_mat(4,5)
    val Bt = libkarma.t_mat(B)
    libkarma.release_mat(B)
    val ABt = libkarma.mul_mat(A, Bt)
    libkarma.release_mat(A)
    libkarma.print_mat(ABt)
    libkarma.release_mat(ABt)

    println("done")
}
