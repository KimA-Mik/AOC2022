package aoc

import kotlin.math.abs

class AOC10 {

    private var cycleCount = 0
    private var registerX = 1
    private var _signalStrengthDump = 0
    val signalStrengthDump
        get() = _signalStrengthDump

    private val screenWidth = 40
    private val screenHeight = 6
    private val screenBuilder = StringBuilder((screenWidth + 1) * screenHeight)
    fun getScreenString() = screenBuilder.toString()

    fun executeProgram(program: List<String>) {
        screenBuilder.clear()
        cycleCount = 0
        registerX = 1
        _signalStrengthDump = 0

        for (line in program) {
            val tokens = line.split(' ')
            val op = tokens.first()

            when (op) {
                "noop" -> {
                    executeCycle()
                }

                "addx" -> {
                    repeat(2) { executeCycle() }
                    registerX += tokens[1].toInt()
                }

                else -> throw Exception()
            }
        }
    }

    fun printScreen() {
        for (y in 0..<screenHeight) {
            for (x in 0..<screenWidth) {
                print(screenBuilder[y * screenWidth + x])
            }
            println()
        }
    }

    private fun executeCycle() {
        drawPixel()
        cycleCount += 1
        if ((cycleCount - 20) % 40 == 0) {
            _signalStrengthDump += registerX * cycleCount
        }
    }

    private fun drawPixel() {
        val line = cycleCount / 40
        val spritePos = line * 40 + registerX
        val pixel = if (abs(spritePos - cycleCount) > 1)
            '.'
        else
            '#'
        screenBuilder.append(pixel)
    }
}