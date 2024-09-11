package io.github.waveskimmer.brownsugar

/**
 * convenience attribute as size is used everywhere but string (nod to groovy)
 */
val String.size: Int
    get() = length

fun ByteArray.toUTF8() = this.toString(Charsets.UTF_8)
fun String.toByteArray() = this.toByteArray(Charsets.UTF_8)

/**
 * tests for a numeric string.   An empty string is not numeric as it
 * cannot be transformed to an integer.
 */
val String.isNumeric: Boolean
    get() = size > 0 && all { it.isDigit() }

val String.isAlpha: Boolean
    get() = size > 0 && all { it.isLetter() }

val String.isAlphaNumeric: Boolean
    get() = size > 0 && all { it.isLetter() || it.isDigit() }