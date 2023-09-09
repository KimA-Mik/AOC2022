package aoc

import java.util.*

private const val MAX_SIZE = 100000u

class AOC07 {
    private data class Dir(var name: String, val dirs: MutableList<Dir>, val files: MutableList<File>) {

        constructor(name: String) : this(name, mutableListOf(), mutableListOf())
    }

    private data class File(val name: String, val size: UInt)

    private val root = Dir("/")
    private val fileStack = Stack<Dir>()

    fun constructFileSystemFromCommands(input: List<String>) {
        for (line in input) {
            val c = line.first()
            when (c) {
                '$' -> executeCommand(line)
                else -> readListLine(line)
            }
        }
    }

    fun calculateSizeofSmallDirs(): UInt = calculateSizeofSmallDirs(root)


    fun calculateMinSpace(): UInt {
        var res: UInt = UInt.MAX_VALUE
        val occupiedSpace = dirSize(root)
        val totalSpace = 70000000u
        val neededSpace = 30000000u
        val spaceToClean: UInt = neededSpace + occupiedSpace - totalSpace

        val queue: Queue<Dir> = LinkedList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val curDir = queue.poll()
            queue.addAll(curDir.dirs)
            val curSize = dirSize(curDir)
            if (curSize > spaceToClean && curSize < res) {
                res = curSize
            }
        }

        return res
    }


    private fun calculateSizeofSmallDirs(dir: Dir): UInt {
        var res = 0u

        val curSize = dirSize(dir)
        if (curSize < MAX_SIZE)
            res += curSize

        for (d in dir.dirs) {
            res += calculateSizeofSmallDirs(d)
        }

        return res
    }

    private fun executeCommand(line: String) {
        val tokens = line.split(' ')
        when (tokens[1]) {
            "cd" -> {
                when (tokens[2]) {
                    "/" -> {
                        fileStack.clear()
                        fileStack.push(root)
                    }

                    ".." -> fileStack.pop()
                    else -> {
                        fileStack.push(
                            fileStack.peek()
                                .dirs.first { it.name == tokens[2] }
                        )
                    }
                }
            }

            "ls" -> {}
            else -> println("Unknown command ${tokens[1]}")
        }
    }

    private fun readListLine(line: String) {
        val tokens = line.split(' ')
        val firstChar = line.first()
        when {
            //Assume it is a file
            firstChar.isDigit() ->
                fileStack.peek().files.add(
                    File(
                        tokens[1],
                        tokens[0].toUInt()
                    )
                )

            //Assume it is a dir
            firstChar.isLetter() ->
                fileStack.peek().dirs.add(
                    Dir(tokens[1])
                )

            else -> println("Unknown line $line")
        }
    }

    private fun dirSize(dir: Dir): UInt {
        var res = 0u

        dir.files.forEach { res += it.size }
        dir.dirs.forEach { res += dirSize(it) }

        return res
    }

}
