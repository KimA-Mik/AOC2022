package aoc

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class AOCTest {

    @Test
    fun testDay01() {
        val aoc01 = AOC01()
        val sample = File("./input/01/sample.txt").readLines()
        assertEquals(24000, aoc01.countMaxCalories(sample))
        assertEquals(45000, aoc01.countTopThreeCalories(sample))
    }


    @Test
    fun testDay02() {
        val aoc02 = AOC02()
        val sample = File("./input/02/sample.txt").readLines()
        var sum = 0
        sample.forEach { sum += aoc02.evaluateStrategy(it) }
        assertEquals(15, sum)

        sum = 0
        sample.forEach { sum += aoc02.evaluateCorrectStrategy(it) }
        assertEquals(12, sum)
    }

}