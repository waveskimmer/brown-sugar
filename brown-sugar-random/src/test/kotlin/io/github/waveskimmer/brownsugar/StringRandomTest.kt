package io.github.waveskimmer.brownsugar

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
                assertTrue { value.size == 0 || value.isNumeric() }
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
}