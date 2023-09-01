import aoc.*
import java.io.File

fun main() {
    day01()
    day02()
    day03()
}

fun day01() {
    val input = File("./input/01/input.txt").readLines()
    val aoC01 = AOC01()
    println("AoC Day 01 Puzzle 1 - ${aoC01.countMaxCalories(input)}")
    println("AoC Day 01 Puzzle 2 - ${aoC01.countTopThreeCalories(input)}\n")
}

fun day02() {
    val input = File("./input/02/input.txt").readLines()
    val aoC02 = AOC02()
    var sum = 0

    input.forEach { sum += aoC02.evaluateStrategy(it) }
    println("AoC Day 02 Puzzle 1 - $sum")

    sum = 0
    input.forEach { sum += aoC02.evaluateCorrectStrategy(it) }
    println("AoC Day 02 Puzzle 2 - $sum\n")
}

fun day03() {
    val input = File("./input/03/input.txt").readLines()
    val aoc03 = AOC03()
    var sum = 0

    input.forEach { sum += aoc03.analyseRucksack(it) }
    println("AoC Day 03 Puzzle 1 - $sum")

    sum = 0
    for (i in input.indices step 3) {
        sum += aoc03.analyseSimilarities(input.subList(i, i + 3))
    }
    println("AoC Day 03 Puzzle 3 - $sum")
}