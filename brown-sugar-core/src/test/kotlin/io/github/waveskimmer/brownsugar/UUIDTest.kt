package io.github.waveskimmer.brownsugar

import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class UUIDTest {

    @Test
    fun `String toUUID works`() {
        val uuid = UUID.randomUUID()
        assertTrue { uuid.toString().toUUID() == uuid }
    }

    @Test
    fun `String toUUID fails with bad uuid`() {
        assertFailsWith<IllegalArgumentException> {
            "random ramblings".toUUID()
        }
    }

    @Test
    fun `asUUID works on null`() {
        val uuid = (null as String?).asUUID()
        assertEquals(uuid.toString().toUUID(), uuid)
    }

    @Test
    fun `asUUID works on String of UUID`() {
        val uuid = UUID.randomUUID()
        assertEquals(uuid.toString().asUUID(), uuid)
    }

    @Test
    fun `asUUID works any String`() {
        val quote = """
            To succeed in life, you need three things: 
            a wishbone, a backbone, and a funny bone.
            --Reba McEntire
        """.trimIndent()
        assertEquals(quote.asUUID(), UUID.nameUUIDFromBytes(quote.toByteArray()))
    }
}