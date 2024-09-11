package io.github.waveskimmer.brownsugar

import kotlin.random.Random

object StringRandom {

    private fun generate(width: Int, f: (Int) -> Char): String =
        List(width, f).joinToString("")

    /**
     * numeric string (all digits with no leading zero) of specified
     * width
     */
    fun numeric(width: Int) = generate(width) { index ->
        when (index) {
            0 -> Random.nextInt(1, 10)
            else -> Random.nextInt(10)
        }
            .let { '0' + it }
    }

    /**
     * alpha string of specified width
     */
    fun alpha(width: Int): String = generate(width) {
        Random.nextInt(26 * 2).let {
            when {
                it < 26 -> 'a' + it
                else -> 'A' + (it - 26)
            }
        }
    }

    fun alphaNumeric(width: Int): String = generate(width) {
        Random.nextInt(26 * 2 + 10).let {
            when {
                it < 26 -> 'a' + it
                it < 26 * 2 -> 'A' + (it - 26)
                else -> '0'
            }
        }
    }
}