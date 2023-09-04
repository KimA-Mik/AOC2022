package aoc

class AOC04 {

    fun doesSectorFullyContainOther(input: String): Boolean {
        val ranges = parseInputToRanges(input)
        if (ranges[0] <= ranges[2] && ranges[1] >= ranges[3])
            return true
        if (ranges[0] >= ranges[2] && ranges[1] >= ranges[3])
            return true
        return false
    }

    fun doSectorsOverlap(input: String): Boolean {
        val ranges = parseInputToRanges(input)

        if (ranges[0] <= ranges[2] && ranges[1] >= ranges[2])
            return true
        if (ranges[1] >= ranges[3] && ranges[0] <= ranges[3])
            return true

        if (ranges[2] <= ranges[0] && ranges[3] >= ranges[0])
            return true
        if (ranges[3] >= ranges[1] && ranges[2] <= ranges[1])
            return true

        return false
    }

    private fun parseInputToRanges(input: String): List<Int> {
        val ranges = mutableListOf<Int>()
        input.split(',').forEach { elf ->
            elf.split('-').forEach { border ->
                ranges.add(border.toInt())
            }
        }
        return ranges
    }
}