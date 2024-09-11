package io.github.waveskimmer.brownsugar

/**
 * convenience attribute as size is used everywhere but string (nod to groovy)
 */
val String.size: Int
    get() = length

fun ByteArray.toUTF8() = this.toString(Charsets.UTF_8)
fun String.toByteArray() = this.toByteArray(Charsets.UTF_8)

fun String.isNumeric() = size > 0 && all { it.isDigit() }