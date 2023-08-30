import aoc.*
import java.io.File

fun main() {
    day01()
    day02()
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
    println("AoC Day 01 Puzzle 1 - $sum")

    sum = 0
    input.forEach { sum += aoC02.evaluateCorrectStrategy(it) }
    println("AoC Day 01 Puzzle 2 - $sum")

}