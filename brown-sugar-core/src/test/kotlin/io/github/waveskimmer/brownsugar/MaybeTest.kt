package io.github.waveskimmer.brownsugar

import java.util.Optional
import org.junit.jupiter.api.assertThrows
import kotlin.test.*

class MaybeTest {

    @Test
    fun `toOptional present works`() {
        val s = "The way to get started is to quit talking and begin doing. --Walt Disney"
        val o = s.toOptional()
        assertEquals(o, Optional.of(s))
        assertEquals(o.value, s)
        assertTrue(o.present)

        val m = o.toMaybe()
        assertEquals(m, Maybe(s))
    }

    @Test
    fun `toOptional empty works`() {
        val maybe = (null as String?).toOptional()
        assertEquals(maybe, Optional.empty())
        assertFalse(maybe.present)
        assertEquals(maybe.value, null)
    }

    @Test
    fun `present and empty work`() {
        assertTrue { "abc".present }
        assertFalse { "abc".empty }

        assertFalse { null.present }
        assertTrue { null.empty }
    }

    @Test
    fun `name collision is not bad`() {
        class NameCollision(val name: String?) {
            val present: Boolean
                get() = (name != null)
        }

        val collision = NameCollision(null)
        assertFalse { collision.present }

        // this uses the extension method - so there is still room
        // for confusion
        assertFalse { collision.empty }
    }

    @Test
    fun `Maybe present works as designed`() {
        val o = "abc"
        val m = Maybe(o)
        assertTrue { m.present }
        assertFalse { m.empty }
        assertFalse { m.isEmpty() }
        assertEquals(m.size, 1)
        assertEquals(m.value, o)
        assertTrue { m.contains(o) }
        assertTrue { m.containsAll(listOf(o)) }
        assertFalse { m.containsAll(listOf(o, "xyz")) }

        fun <T> testIterator(m: Maybe<T>) {
            m.forEach {
                assertEquals(it, m.value)
                return
            }
            fail("loop didn't run")
        }
        testIterator(m)
    }

    @Test
    fun `maybe present iterator works`() {
        val o = "abc"
        val m = Maybe(o)
        val i = m.iterator()
        assertTrue { i.hasNext() }
        assertEquals(i.next(), o)
        assertFalse { i.hasNext() }
        assertThrows<ArithmeticException> {
            i.next()
        }
    }

    @Test
    fun `test maybe when empty`() {
        val not = (null as String?).toMaybe()
        assertFalse { not.present }
        assertTrue { not.empty }
        assertTrue { not.isEmpty() }
        assertEquals(not.size, 0)
        assertFalse { not.contains("abc") }
        assertTrue { not.containsAll(emptyList()) }
        assertFalse { not.iterator().hasNext() }
        assertThrows<ArithmeticException> {
            not.iterator().next()
        }

        not.forEach {
            fail("shouldn't be here: $it")
        }
    }
}