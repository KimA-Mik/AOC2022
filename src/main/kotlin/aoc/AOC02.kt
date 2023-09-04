package aoc

class AOC02 {
    private enum class RSP(val score: Int) {
        Rock(1),
        Paper(2),
        Scissors(3)
    }

    fun evaluateStrategy(str: String): Int {
        var res = 0
        val ops = str.split(' ')
        val opponentsMove = when (ops[0]) {
            "A" -> RSP.Rock
            "B" -> RSP.Paper
            "C" -> RSP.Scissors
            else -> throw Exception()
        }

        val playersMove = when (ops[1]) {
            "X" -> RSP.Rock
            "Y" -> RSP.Paper
            "Z" -> RSP.Scissors
            else -> throw Exception()
        }

        res += playersMove.score

        if (playersMove == opponentsMove) {
            return res + 3
        }

        if (playersMove == RSP.Rock)
            return if (opponentsMove == RSP.Scissors)
                res + 6
            else
                res

        if (playersMove == RSP.Scissors)
            return if (opponentsMove == RSP.Paper)
                res + 6
            else
                res

        return if (opponentsMove == RSP.Rock)
            res + 6
        else
            res
    }

    fun evaluateCorrectStrategy(str: String): Int {
        var res = 0
        val ops = str.split(' ')
        val opponentsMove = when (ops[0]) {
            "A" -> RSP.Rock
            "B" -> RSP.Paper
            "C" -> RSP.Scissors
            else -> throw Exception()
        }

        val playersMove = when (ops[1]) {
            "X" -> {
                when (opponentsMove) {
                    RSP.Rock -> RSP.Scissors
                    RSP.Scissors -> RSP.Paper
                    RSP.Paper -> RSP.Rock
                }
            }
            "Y" -> {
                res += 3
                opponentsMove
            }

            "Z" -> {
                res += 6
                when (opponentsMove) {
                    RSP.Rock -> RSP.Paper
                    RSP.Scissors -> RSP.Rock
                    RSP.Paper -> RSP.Scissors
                }
            }
            else -> throw Exception()
        }

        res += playersMove.score

        return res
    }
}