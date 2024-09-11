package io.github.waveskimmer.brownsugar

import io.github.waveskimmer.brownsugar.StringRandom.alpha
import io.github.waveskimmer.brownsugar.StringRandom.alphaNumeric
import io.github.waveskimmer.brownsugar.StringRandom.numeric
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StringRandomTest {
    @Test
    fun `Generate numeric`() {
        fun test(width: Int) {
            val value = numeric(width)
            try {
                assertTrue { value.size == 0 || value.isNumeric }
                assertEquals(value.size, width)
            } catch (t: Throwable) {
                println("Failed for width: $width and result = $value")
                throw t
            }
        }

        test(0)
        test(1)
        test(234)
        assertThrows<IllegalArgumentException> {
            test(-1)
        }
    }

    @Test
    fun `generate alpha`() {
        fun test(width: Int) {
            val value = alpha(width)
            try {
                assertTrue { value.size == 0 || value.isAlpha }
                assertEquals(value.size, width)
            } catch (t: Throwable) {
                println("Failed for width: $width and result = $value")
                throw t
            }
        }

        test(0)
        test(1)
        test(123)
    }

    @Test
    fun `generate alphaNumeric`() {
        fun test(width: Int) {
            val value = alphaNumeric(width)
            try {
                assertTrue { value.size == 0 || value.isAlphaNumeric }
                assertEquals(value.size, width)
            } catch (t: Throwable) {
                println("Failed for width: $width and result = $value")
                throw t
            }
        }

        test(0)
        test(1)
        test(123)
    }
}