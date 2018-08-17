package co.m.co.gamesource.core

/**
 *
 * @author Oscar Gallon on 8/15/18.
 */
sealed class Either<out E, out V> where E : Throwable {

    data class Error<out E>(val error: E) : Either<E, Nothing>() where E : Throwable

    /** * Represents ". */
    data class Value<out V>(val value: V) : Either<Nothing, V>()

    val isValue get() = this is Value<V>
    val isError get() = this is Error<E>

    fun <E> createError(a: E) where E : Throwable = Either.Error(a)
    fun <V> createValue(b: V) = Either.Value(b)

    fun either(
            fnE: (E) -> Any,
            fnV: (V) -> Any
    ): Any =
            when (this) {
                is Error -> fnE(error)
                is Value -> fnV(value)
            }
}

// Credits to Alex Hart -> https://proandroiddev.com/kotlins-nothing-type-946de7d464fb
// Composes 2 functions
fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, E, V> Either<E, V>.flatMap(fn: (V) -> Either<E, T>): Either<E, T> where E : Throwable =
        when (this) {
            is Either.Error -> Either.Error(error)
            is Either.Value -> fn(value)
        }

fun <T, E, V> Either<E, V>.map(fn: (V) -> (T)): Either<E, T> where E : Throwable = this.flatMap(
        fn.c(::createValue)
)

