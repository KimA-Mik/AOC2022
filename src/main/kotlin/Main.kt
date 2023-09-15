import aoc.*
import java.io.File

fun main() {
    day01()
    day02()
    day03()
    day04()
    day05()
    day06()
    day07()
    day08()
    day09()
    day10()
}

private fun getInputLines(dayNum: Int): List<String> {
    val path = "./input/%02d/input.txt".format(dayNum)
    return File(path).readLines()
}

fun day01() {
    val input = getInputLines(1)
    val aoC01 = AOC01()
    println("AoC Day 01 Puzzle 1 - ${aoC01.countMaxCalories(input)}")
    println("AoC Day 01 Puzzle 2 - ${aoC01.countTopThreeCalories(input)}\n")
}

fun day02() {
    val input = getInputLines(2)
    val aoC02 = AOC02()
    var sum = 0

    input.forEach { sum += aoC02.evaluateStrategy(it) }
    println("AoC Day 02 Puzzle 1 - $sum")

    sum = 0
    input.forEach { sum += aoC02.evaluateCorrectStrategy(it) }
    println("AoC Day 02 Puzzle 2 - $sum\n")
}

fun day03() {
    val input = getInputLines(3)
    val aoc03 = AOC03()
    var sum = 0

    input.forEach { sum += aoc03.analyseRucksack(it) }
    println("AoC Day 03 Puzzle 1 - $sum")

    sum = 0
    for (i in input.indices step 3) {
        sum += aoc03.analyseSimilarities(input.subList(i, i + 3))
    }
    println("AoC Day 03 Puzzle 3 - $sum\n")
}

fun day04() {
    val input = getInputLines(4)
    val aoc04 = AOC04()
    var sum = 0

    input.forEach {
        if (aoc04.doesSectorFullyContainOther(it)) {
            sum += 1
        }
    }
    println("AoC Day 04 Puzzle 1 - $sum")

    sum = 0
    input.forEach {
        if (aoc04.doSectorsOverlap(it)) {
            sum += 1
        }
    }
    println("AoC Day 04 Puzzle 2 - $sum\n")
}

fun day05() {
    val aoc05 = AOC05()
    val input = getInputLines(5)
    aoc05.parseInputFile(input)
    println("AoC Day 05 Puzzle 1 - ${aoc05.evaluateOperations()}")
    aoc05.parseInputFile(input)
    println("AoC Day 05 Puzzle 2 - ${aoc05.evaluateOperationsWithMultiplePickup()}\n")
}

fun day06() {
    val aoc06 = AOC06()
    val input = getInputLines(6)
    println("AoC Day 06 Puzzle 1 - ${aoc06.getStartOfPocketMarkerPosition(input[0])}")
    println("AoC Day 06 Puzzle 2 - ${aoc06.getStartOfMessageMarkerPosition(input[0])}\n")
}

fun day07() {
    val aoC07 = AOC07()
    val input = getInputLines(7)
    aoC07.constructFileSystemFromCommands(input)
    println("AoC Day 07 Puzzle 1 - ${aoC07.calculateSizeofSmallDirs()}")
    println("AoC Day 07 Puzzle 2 - ${aoC07.calculateMinSpace()}\n")
}

fun day08() {
    val aoc08 = AOC08()
    val input = getInputLines(8)
    println("AoC Day 08 Puzzle 1 - ${aoc08.countVisibleTrees(input)}")
    println("AoC Day 08 Puzzle 2 - ${aoc08.computeHighestScenicScore()}\n")
}

fun day09() {
    val aoc09 = AOC09()
    val input = getInputLines(9)
    println("AoC Day 09 Puzzle 1 - ${aoc09.calculateVisitedPositions(input)}")
    println("AoC Day 09 Puzzle 2 - ${aoc09.calculateVisitedPositionsForLongerRope(input)}\n")
}

fun day10() {
    val aoc10 = AOC10()
    val input = getInputLines(10)
    aoc10.executeProgram(input)
    println("AoC Day 10 Puzzle 1 - ${aoc10.signalStrengthDump}")
    println("AoC Day 10 Puzzle 2:")
    aoc10.printScreen()
    println()
}