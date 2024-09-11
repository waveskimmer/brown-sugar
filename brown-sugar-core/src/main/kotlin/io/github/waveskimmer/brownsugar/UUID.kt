package io.github.waveskimmer.brownsugar

import java.util.UUID

/**
 * extension method to convert UUID to string.
 */
fun CharSequence.toUUID(): UUID = UUID.fromString(this.toString())

/**
 * extension method to convert or generate a UUID
 */
fun String?.asUUID(): UUID =
    when (this) {
        null -> UUID.randomUUID()
        else ->
            try {
                UUID.fromString(this)
            } catch (t: Throwable) {
                UUID.nameUUIDFromBytes(this.toByteArray())
            }
    }
