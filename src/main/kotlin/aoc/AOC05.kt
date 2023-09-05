package aoc

import java.util.*
import kotlin.collections.List

class AOC05 {
    private val stacks = mutableListOf<Stack<Char>>()
    private val operations = mutableListOf<Operation>()
    fun parseInputFile(input: List<String>) {
        stacks.clear()
        operations.clear()
        var stage2 = false
        for (line in input) {
            if (line.isEmpty()) {
                stage2 = true
                continue
            }

            if (stage2) {
                parseOperationLine(line)
            } else {
                parseDrawingLine(line)
            }
        }

        stacks.forEach {
            it.reverse()
        }
    }

    private data class Operation(
        val count: Int,
        val from: Int,
        val to: Int
    )

    fun evaluateOperations(): String {
        for (op in operations) {
            repeat(op.count) {
                stacks[op.to].push(
                    stacks[op.from].pop()
                )
            }
        }

        return getTopCrates()
    }

    fun evaluateOperationsWithMultiplePickup(): String {
        val craneStack = Stack<Char>()
        for (op in operations) {
            repeat(op.count) {
                craneStack.push(
                    stacks[op.from].pop()
                )
            }

            while (craneStack.isNotEmpty()) {
                stacks[op.to].push(
                    craneStack.pop()
                )
            }
        }
        return getTopCrates()
    }

    private fun parseDrawingLine(line: String) {
        var col = 0
        var spaces = 0
        for (c in line) {
            if (c.isWhitespace()) {
                spaces += 1
                if (spaces > 3) {
                    col += 1
                    spaces -= 4
                }
            } else if (c.isLetter()) {
                insertCharToStacks(c, col)
                col += 1
                spaces = 0
            }
        }
    }

    private fun parseOperationLine(line: String) {
        val tokens = line.split(' ').filter { token ->
            token.any { char ->
                char.isDigit()
            }
        }.map { it.toInt() }

        operations.add(
            Operation(
                tokens[0],
                tokens[1] - 1,
                tokens[2] - 1
            )
        )
    }

    private fun insertCharToStacks(c: Char, col: Int) {
        while (col >= stacks.size)
            stacks.add(Stack())

        stacks[col].push(c)
    }

    private fun getTopCrates(): String {
        val sb = StringBuilder()
        for (stack in stacks) {
            sb.append(stack.peek())
        }
        return sb.toString()
    }
}