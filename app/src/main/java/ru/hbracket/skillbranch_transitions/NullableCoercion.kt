package ru.hbracket.skillbranch_transitions

class NullableCoercion {

    fun <T> T?.coerce(): T {
        return this ?: throw Interrupt()
    }

    class Interrupt : Exception()

}

fun <R> nullable(f: NullableCoercion.() -> R): R? {
    val ctx = NullableCoercion()
    return try {
        ctx.f()
    } catch (_: NullableCoercion.Interrupt) {
        null
    }
}