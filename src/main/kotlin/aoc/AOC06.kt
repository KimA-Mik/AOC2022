package aoc

class AOC06 {

    fun getStartOfPocketMarkerPosition(input: String): Int = getXDistinctCharactersPosition(input, 4)

    fun getStartOfMessageMarkerPosition(input: String): Int = getXDistinctCharactersPosition(input, 14)

    private fun getXDistinctCharactersPosition(input: String, lengthOfSequence: Int): Int {
        var res = 0
        val set = mutableSetOf<Char>()

        loop@ for (i in 0..input.length - lengthOfSequence) {
            set.clear()
            res = i
            for (j in i..<i + lengthOfSequence) {
                if (!set.add(input[j])) {
                    continue@loop
                }
            }
            break@loop
        }

        return res + lengthOfSequence
    }
}