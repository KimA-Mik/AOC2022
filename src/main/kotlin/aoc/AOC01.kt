package aoc


class AOC01 {
    fun countMaxCalories(lines: List<String>): Int {
        var max = 0
        var cur = 0
        for (line in lines) {
            if (line.isEmpty()) {
                max = cur.coerceAtLeast(max)
                cur = 0
                continue
            }

            cur += line.toInt()
        }
        return max
    }

    fun countTopThreeCalories(lines: List<String>): Int {
        val values = mutableListOf<Int>()

        var cur = 0
        for (line in lines) {
            if (line.isEmpty()) {
                values.add(cur)
                cur = 0
                continue
            }
            cur += line.toInt()
        }
        if (values.last() != cur)
            values.add(cur)
        values.sort()
        return values.takeLast(3).sum()
    }
}