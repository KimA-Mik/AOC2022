package aoc

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.random.Random
import kotlin.test.assertEquals

class AOCTest {

    private fun getSampleLines(dayNum: Int): List<String> {
        val path = "./input/%02d/sample.txt".format(dayNum)
        return File(path).readLines()
    }

    @Test
    fun testDay01() {
        val aoc01 = AOC01()
        val sample = getSampleLines(1)
        assertEquals(24000, aoc01.countMaxCalories(sample))
        assertEquals(45000, aoc01.countTopThreeCalories(sample))
    }

    @Test
    fun testDay02() {
        val aoc02 = AOC02()
        val sample = getSampleLines(2)
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
        val sample = getSampleLines(3)
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
        val sample = getSampleLines(4)
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

    @Test
    fun testDay05() {
        val aoc05 = AOC05()
        val sample = getSampleLines(5)

        aoc05.parseInputFile(sample)
        assertEquals("CMZ", aoc05.evaluateOperations())
        aoc05.parseInputFile(sample)
        assertEquals("MCD", aoc05.evaluateOperationsWithMultiplePickup())
    }

    @Test
    fun testDay06() {
        val aoc06 = AOC06()
        val sample = getSampleLines(6)

        assertEquals(7, aoc06.getStartOfPocketMarkerPosition(sample[0]))
        assertEquals(5, aoc06.getStartOfPocketMarkerPosition(sample[1]))
        assertEquals(6, aoc06.getStartOfPocketMarkerPosition(sample[2]))
        assertEquals(10, aoc06.getStartOfPocketMarkerPosition(sample[3]))
        assertEquals(11, aoc06.getStartOfPocketMarkerPosition(sample[4]))

        assertEquals(19, aoc06.getStartOfMessageMarkerPosition(sample[0]))
        assertEquals(23, aoc06.getStartOfMessageMarkerPosition(sample[1]))
        assertEquals(23, aoc06.getStartOfMessageMarkerPosition(sample[2]))
        assertEquals(29, aoc06.getStartOfMessageMarkerPosition(sample[3]))
        assertEquals(26, aoc06.getStartOfMessageMarkerPosition(sample[4]))
    }

    @Test
    fun testDay07() {
        val aoc07 = AOC07()
        val sample = getSampleLines(7)
        aoc07.constructFileSystemFromCommands(sample)
        assertEquals(95437u, aoc07.calculateSizeofSmallDirs())
        assertEquals(24933642u, aoc07.calculateMinSpace())
    }

    @Test
    fun testDay08() {
        val aoc08 = AOC08()
        val sample = getSampleLines(8)
        assertEquals(21, aoc08.countVisibleTrees(sample))
        assertEquals(8, aoc08.computeHighestScenicScore())
    }

    @Test
    fun testDay09() {
        val aoc09 = AOC09()
        val sample = getSampleLines(9)
        assertEquals(13, aoc09.calculateVisitedPositions(sample))
        assertEquals(1, aoc09.calculateVisitedPositionsForLongerRope(sample))
        val largerSample = File("./input/09/larger_sample.txt").readLines()
        assertEquals(36, aoc09.calculateVisitedPositionsForLongerRope(largerSample))
    }

    @Test
    fun testDay10() {
        val aoc10 = AOC10()
        val sample = getSampleLines(10)
        aoc10.executeProgram(sample)
        assertEquals(13140, aoc10.signalStrengthDump)
        val screen = "##..##..##..##..##..##..##..##..##..##.." +
                "###...###...###...###...###...###...###." +
                "####....####....####....####....####...." +
                "#####.....#####.....#####.....#####....." +
                "######......######......######......####" +
                "#######.......#######.......#######....."

        aoc10.printScreen()
        assertEquals(screen, aoc10.getScreenString())
    }

    @Test
    fun testMonkeys() {
        val div = 19L
        var a = 10L
        var b = a % div
        repeat(20) {
            val mul = Random.nextLong(1, 6)
            a *= mul
            b = (b * mul) % div
            assertEquals(a % div, b)
        }
    }

    @Test
    fun testDay11() {
        val aoc11 = AOC11()
        val sample = getSampleLines(11)
        aoc11.loadMonkeys(sample)
        assertEquals(10605, aoc11.doMonkeyBusiness())
        aoc11.loadMonkeys(sample)
        assertEquals(10605, aoc11.doMonkeyStuff())
        aoc11.loadMonkeys(sample)
        assertEquals(2713310158L, aoc11.duMoreMonkeyBusiness())
    }
}