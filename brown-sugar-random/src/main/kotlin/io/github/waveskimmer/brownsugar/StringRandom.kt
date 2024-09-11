package io.github.waveskimmer.brownsugar

import kotlin.random.Random

object StringRandom {

    fun numeric(width:Int): String =
     List(width) { index ->
            when (index) {
                0 -> Random.nextInt(1, 10)
                else -> Random.nextInt(10)
            }
        }
        .map { it.toString() }
       .joinToString("")
}