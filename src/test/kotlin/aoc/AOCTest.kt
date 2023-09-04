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

    @Test
    fun testDay03() {
        val aoc03 = AOC03()
        val sample = File("./input/03/sample.txt").readLines()
        var sum = 0
        sample.forEach { sum += aoc03.analyseRucksack(it) }
        assertEquals(157, sum)

        sum = 0
        for (i in sample.indices step 3) {
            sum += aoc03.analyseSimilarities(sample.subList(i, i + 3))
        }
        assertEquals(70, sum)
    }

    @Test
    fun testDay04() {
        val aoc04 = AOC04()
        val sample = File("./input/04/sample.txt").readLines()
        var sum = 0
        sample.forEach {
            if (aoc04.doesSectorFullyContainOther(it)) {
                sum += 1
            }
        }
        assertEquals(2, sum)

        sum = 0
        sample.forEach {
            if (aoc04.doSectorsOverlap(it)) {
                sum += 1
            }
        }
        assertEquals(4, sum)
    }
}