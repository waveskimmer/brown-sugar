package io.github.waveskimmer.brownsugar

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringTest {

    @Test
    fun `size works`() {
        assertEquals("".size, 0)
        assertEquals("abc".size, 3)
    }

    @Test
    fun `toUTF8 works`() {
        val quote = """
            If you cannot do great things, do small things 
            in a great way.
            --Napoleon Hill
        """.trimIndent()

        assertEquals(quote.toByteArray().toUTF8(), quote)
    }

    @Test
    fun `isNumeric works`() {
        assertFalse { "abc".isNumeric() }
        assertTrue { "123".isNumeric() }
        assertFalse { "".isNumeric() }
    }
}