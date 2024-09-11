package io.github.waveskimmer.brownsugar

import java.util.Optional

// Note: given that Optional.empty() returns a constant for all types, we need to return an
// Optional<T & Any).
fun <T> T?.toOptional(): Optional<T & Any> = Optional.ofNullable<T>(this)

val <T> Optional<T>.value: T?
    get() = when {
        this.isPresent -> this.get()
        else -> null
    }

interface MaybeApi {
    val present: Boolean
    val empty: Boolean
        get() = !present
}

/**
 * Maybe is just a singleton collection containing 0 or 1 elements
 */
@JvmInline
value class Maybe<T>(val value: T?) : Collection<T>, MaybeApi {

    override val present: Boolean
        get() = (value != null)

    override val size: Int
        get() = when {
            present -> 1
            else -> 0
        }

    override fun contains(element: T): Boolean = (value == element)

    override fun containsAll(elements: Collection<T>): Boolean =
        when (elements.size) {
            0 -> isEmpty()
            1 -> contains(elements.first())
            else -> false
        }

    override fun isEmpty(): Boolean = empty

    override fun iterator(): Iterator<T> = MaybeIterator(value)
}

internal class MaybeIterator<T>(private var value: T?) : Iterator<T> {
    override fun hasNext(): Boolean = value != null

    override fun next(): T = when (value) {
        null -> throw throw ArithmeticException("Index overflow has happened.")
        else -> value!!.also { value = null }
    }
}

fun <T> Optional<T>.toMaybe(): Maybe<T> = Maybe(this.value)
fun <T> T?.toMaybe() = Maybe(this)

// Note: These are experimental, so please give feedback.  Not that an
// extension method shouldn't eclipse a class method, but I'll need to test it.
val Any?.present: Boolean
    get() = when (this) {
        is Optional<*> -> isPresent
        is MaybeApi -> present
        else -> (this != null)
    }

val Any?.empty: Boolean
    get() = !present
