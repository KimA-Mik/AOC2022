package aoc

import java.util.LinkedList
import java.util.Queue

//Parser is ready
class AOC11 {
    private class Monkey(
        val items: Queue<Int>,
        val inspection: MonkeyInspection,
        val test: Int,
        val trueMonkey: Int,
        val falseMonkey: Int
    )

    private class MonkeyBuilder() {
        private var _items: Queue<Int>? = null
        private var _inspection: MonkeyInspection? = null
        private var _test: Int = 0
        private var _trueMonkey = -1
        private var _falseMonkey = -1

        fun addItems(items: Queue<Int>): MonkeyBuilder {
            _items = items
            return this
        }

        fun addInspection(inspection: MonkeyInspection): MonkeyBuilder {
            _inspection = inspection
            return this
        }

        fun addTest(test: Int): MonkeyBuilder {
            _test = test
            return this
        }

        fun addTrueMonkey(trueMonkey: Int): MonkeyBuilder {
            _trueMonkey = trueMonkey
            return this
        }

        fun addFalseMonkey(falseMonkey: Int): MonkeyBuilder {
            _falseMonkey = falseMonkey
            return this
        }

        fun buildMonkey(): Monkey {
            if (_items == null
                || _inspection == null
                || _test == 0
                || _trueMonkey < 0
                || _falseMonkey < 0
            ) {
                throw Exception()
            }
            return Monkey(
                _items!!,
                _inspection!!,
                _test,
                _trueMonkey,
                _falseMonkey
            )
        }

        fun clear() {
            _items = null
            _inspection = null
            _test = 0
            _trueMonkey = -1
            _falseMonkey = -1
        }
    }

    private class MonkeyInspection(
        val operation: Operation,
        val leftOperand: Int,
        val rightOperand: Int
    ) {
        enum class Operation {
            OP_PLUS,
            OP_MUL
        }

        fun perform(value: Int): Int {
            val lhs = if (leftOperand > 0) leftOperand else value
            val rhs = if (rightOperand > 0) rightOperand else value

            return when (operation) {
                Operation.OP_MUL -> lhs * rhs
                Operation.OP_PLUS -> lhs + rhs
            }
        }
    }


    private val monkeys = mutableListOf<Monkey>()
    fun loadMonkeys(input: List<String>) {
        monkeys.clear()
        val mb = MonkeyBuilder()
        for (line in input) {
            val trimmedLine = line.trim()
            when {
                trimmedLine.startsWith("Monkey") -> {
                    mb.clear()
                }

                trimmedLine.startsWith("Starting items") -> {
                    val items = LinkedList<Int>()
                    populateQueue(trimmedLine, items)
                    mb.addItems(items)
                }

                trimmedLine.startsWith("Operation") -> {
                    val inspection = parseOperation(trimmedLine.split('=')[1])
                    mb.addInspection(inspection)
                }

                trimmedLine.startsWith("Test") -> {
                    val test = trimmedLine.split(' ').last().toInt()
                    mb.addTest(test)
                }

                trimmedLine.startsWith("If true") -> {
                    val trueMonkey = trimmedLine.split(' ').last().toInt()
                    mb.addTrueMonkey(trueMonkey)
                }

                trimmedLine.startsWith("If false") -> {
                    val falseMonkey = trimmedLine.split(' ').last().toInt()
                    mb.addFalseMonkey(falseMonkey)
                    monkeys.add(mb.buildMonkey())
                }
            }
        }
    }

    fun doMonkeyBusiness(): Int {
        val resultList = MutableList(monkeys.size) { 0 }
        repeat(20) {
            monkeys.forEachIndexed { i, monkey ->
                while (monkey.items.isNotEmpty()) {
                    resultList[i] += 1
                    var item = monkey.items.poll()!!
                    item = monkey.inspection.perform(item)
                    item /= 3
                    val test = item % monkey.test == 0
                    val newMonkey = if (test) monkey.trueMonkey else monkey.falseMonkey
                    monkeys[newMonkey].items.add(item)
                }
            }
        }
        var result = 1
        resultList.sortedDescending().take(2).forEach { result *= it }
        return result
    }

    data class MonkeyTracker2000(val worryLevels: MutableList<Int>)

    //additional
    fun doMonkeyStuff(): Int {
        val trackers = List<Queue<MonkeyTracker2000>>(monkeys.size) { LinkedList() }
        //Init Monkey tracker
        monkeys.forEachIndexed { i, monkey ->
            while (monkey.items.isNotEmpty()) {
                val item = monkey.items.poll()!!
                trackers[i].add(MonkeyTracker2000(MutableList(monkeys.size) { item }))
            }
        }

        //Use Monkey tracker to properly track
        val resultList = MutableList(monkeys.size) { 0 }
        repeat(20) {
            monkeys.forEachIndexed { i, monkey ->
                while (trackers[i].isNotEmpty()) {
                    resultList[i] += 1
                    val item = trackers[i].poll()!!
                    //HZ
                    for (j in item.worryLevels.indices) {
                        val inspection = monkey.inspection.perform(item.worryLevels[j]) / 3
                        val newVal = inspection % monkeys[j].test
                        item.worryLevels[j] = newVal
                    }
                    val test = item.worryLevels[i] == 0
                    val newMonkey = if (test)
                        monkey.trueMonkey
                    else
                        monkey.falseMonkey
                    trackers[newMonkey].add(item)
                }
            }
        }
        var result = 1
        resultList.sortedDescending().take(2).forEach { result *= it }
        return result
    }

    fun duMoreMonkeyBusiness(): Long {

        val trackers = List<Queue<MonkeyTracker2000>>(monkeys.size) { LinkedList() }
        //Init Monkey tracker
        monkeys.forEachIndexed { i, monkey ->
            while (monkey.items.isNotEmpty()) {
                val item = monkey.items.poll()!!
                trackers[i].add(MonkeyTracker2000(MutableList(monkeys.size) { item }))
            }
        }

        //Use Monkey tracker to properly track
        val resultList = MutableList(monkeys.size) { 0L }
        repeat(10000) {
            monkeys.forEachIndexed { i, monkey ->
                while (trackers[i].isNotEmpty()) {
                    resultList[i] += 1L
                    val item = trackers[i].poll()!!
                    //HZ
                    for (j in item.worryLevels.indices) {
                        val inspection = monkeys[j].inspection.perform(item.worryLevels[j])
                        val newVal = inspection % monkeys[j].test
                        item.worryLevels[j] = newVal
                    }
                    val test = item.worryLevels[i] == 0
                    val newMonkey = if (test)
                        monkey.trueMonkey
                    else
                        monkey.falseMonkey
                    trackers[newMonkey].add(item)
                }
            }
        }
        var result = 1L
        resultList.sortedDescending().take(2).forEach { result *= it }
        return result
    }

    private fun populateQueue(input: String, items: Queue<Int>) {
        var curNum = 0
        for (c in input) {
            if (!c.isDigit()) {
                if (curNum == 0) {
                    continue
                } else {
                    items.add(curNum)
                    curNum = 0
                }
            } else {
                curNum = curNum * 10 + c.code - '0'.code
            }
        }
        items.add(curNum)
    }

    private fun parseOperation(input: String): MonkeyInspection {
        val tokens = input.trim().split(' ')
        val lhs = if (tokens[0] == "old") 0 else tokens[0].toInt()
        val op = when (tokens[1]) {
            "+" -> MonkeyInspection.Operation.OP_PLUS
            "*" -> MonkeyInspection.Operation.OP_MUL
            else -> throw IllegalArgumentException()
        }
        val rhs = if (tokens[2] == "old") 0 else tokens[2].toInt()
        return MonkeyInspection(
            leftOperand = lhs,
            operation = op,
            rightOperand = rhs
        )
    }
}