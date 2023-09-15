package aoc

import kotlin.math.abs

class AOC09 {
    private data class Position(var x: Int = 0, var y: Int = 0) {
        fun add(rhs: Position): Position {
            return Position(x = x + rhs.x, y = y + rhs.y)
        }
    }

    private val dirs = mapOf(
        "R" to Position(x = 1, y = 0),
        "L" to Position(x = -1, y = 0),
        "U" to Position(x = 0, y = 1),
        "D" to Position(x = 0, y = -1)
    )

    fun calculateVisitedPositions(input: List<String>): Int {
        val headPosition = Position()
        var tailPosition = Position()
        val visitedPositions = mutableSetOf(tailPosition)
        for (line in input) {
            val tokens = line.split(' ')
            val dir = dirs[tokens[0]]!!
            val length = tokens[1].toInt()

            repeat(length) {
                headPosition.x += dir.x
                headPosition.y += dir.y

                //Diagonal movement
                if (abs(headPosition.x - tailPosition.x) == 2 &&
                    abs(headPosition.y - tailPosition.y) == 1 ||
                    abs(headPosition.x - tailPosition.x) == 1 &&
                    abs(headPosition.y - tailPosition.y) == 2
                ) {
                    //Is where a better way to reduce value to 1 with original sign?
                    var xDir = headPosition.x - tailPosition.x
                    var sign = if (xDir > 0) 1 else -1
                    xDir /= xDir * sign
                    var yDir = headPosition.y - tailPosition.y
                    sign = if (yDir > 0) 1 else -1
                    yDir /= yDir * sign
                    val correction = Position(
                        x = xDir,
                        y = yDir
                    )
                    tailPosition = tailPosition.add(correction)
                    visitedPositions.add(tailPosition)
                }
                //Straight movement
                //Just dir, it doesn't work for part 2
                //Could be simplified like in part 2
                else if (
                    abs(headPosition.x - tailPosition.x) > 1 ||
                    abs(headPosition.y - tailPosition.y) > 1
                ) {
                    tailPosition = tailPosition.add(dir)
                    visitedPositions.add(tailPosition)
                }
            }
        }
        return visitedPositions.size
    }

    fun calculateVisitedPositionsForLongerRope(input: List<String>): Int {
        val knotsPositions = MutableList(10) { Position() }
        val visitedPositions = mutableSetOf(knotsPositions.last())

        for (line in input) {
            val tokens = line.split(' ')
            val dir = dirs[tokens[0]]!!
            val length = tokens[1].toInt()

            repeat(length) {
                knotsPositions.first().x += dir.x
                knotsPositions.first().y += dir.y

                for (i in 1..<knotsPositions.size) {
                    val headPosition = knotsPositions[i - 1]
                    val tailPosition = knotsPositions[i]
                    //Not just dir
                    if (
                        abs(headPosition.x - tailPosition.x) > 1 ||
                        abs(headPosition.y - tailPosition.y) > 1
                    ) {
                        var sign: Int
                        var xDir = headPosition.x - tailPosition.x
                        if (xDir != 0) {
                            sign = if (xDir > 0) 1 else -1
                            xDir /= xDir * sign
                        }
                        var yDir = headPosition.y - tailPosition.y
                        if (yDir != 0) {
                            sign = if (yDir > 0) 1 else -1
                            yDir /= yDir * sign
                        }
                        val correction = Position(
                            x = xDir,
                            y = yDir
                        )
                        knotsPositions[i] = tailPosition.add(correction)
                    }
                }
                visitedPositions.add(knotsPositions.last())
                //rope visualisation, used im debug purposes
                //drawRope(knotsPositions, visitedPositions)
            }
        }
        return visitedPositions.size
    }

    private fun drawRope(rope: MutableList<Position>, visitedPositions: Set<Position>) {
        val rows = 21
        val cols = 26
        val sb = StringBuilder(rows * cols)
        sb.append(".".repeat(rows * cols))
        val start = Position(11, 5)

        for (pos in visitedPositions) {
            val x = pos.x + start.x
            val y = pos.y + start.y
            sb[y * cols + x] = '#'
        }
        sb[start.y * cols + start.x] = 's'

        for (knot in rope.indices.reversed()) {
            val pos = rope[knot]
            val x = pos.x + start.x
            val y = pos.y + start.y
            sb[y * cols + x] = knot.toString()[0]
        }

        val head = rope.first()
        val headX = head.x + start.x
        val headY = head.y + start.y
        sb[headY * cols + headX] = 'H'

        for (y in rows - 1 downTo 0) {
            for (x in 0..<cols) {
                print(sb[y * cols + x])
            }
            println()
        }
        println()
    }
}