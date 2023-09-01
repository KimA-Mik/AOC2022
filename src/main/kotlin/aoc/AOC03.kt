package aoc

class AOC03 {
    private val offset = 'a'.code - 1
    private val leftSet = mutableSetOf<Char>()
    private val rightSet = mutableSetOf<Char>()

    private fun getPriorityNumber(c: Char): Int {
        return if (c.isUpperCase())
            (c + 32).code - offset + 26
        else
            c.code - offset

    }

    fun analyseRucksack(input: String): Int {
        var res = 0
        leftSet.clear()
        rightSet.clear()

        val right = input.subSequence(input.length / 2, input.length)
        val left = input.subSequence(0, input.length / 2)

        left.forEach { leftSet.add(it) }
        right.forEach { rightSet.add(it) }

        rightSet.filter { c ->
            leftSet.any { it == c }
        }.forEach {
            res += getPriorityNumber(it)
        }

        return res
    }

    fun analyseSimilarities(input: List<String>): Int {
        if (input.size != 3)
            return 0
        leftSet.clear()
        rightSet.clear()

        input[0].forEach { leftSet.add(it) }
        input[1].forEach { rightSet.add(it) }
        var resSet = leftSet.intersect(rightSet)
        rightSet.clear()
        input[2].forEach { rightSet.add(it) }
        resSet = resSet.intersect(rightSet)

        return getPriorityNumber(resSet.first())
    }
}