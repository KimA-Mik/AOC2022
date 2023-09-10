package aoc

import kotlin.math.max

class AOC08 {

    private data class Tree(val height: Int, var isVisible: Boolean = false)

    private val grid = mutableListOf<MutableList<Tree>>()

    //Thanks for square input grid
    fun countVisibleTrees(input: List<String>): Int {
        for (line in input) {
            grid.add(mutableListOf())
            line.forEach {
                val tree = Tree(it.code - '0'.code)
                grid.last().add(tree)
            }
        }

        //Mark outer trees as visible
        for (y in grid.indices) {
            grid[y].first().isVisible = true
            grid[y].last().isVisible = true
        }
        grid.first().forEach { it.isVisible = true }
        grid.last().forEach { it.isVisible = true }

        for (y in grid.indices) {
            var highestInRow = grid[y].first().height
            var highestInCol = grid.first()[y].height
            var highestInRowReversed = grid[y].last().height
            var highestInColReversed = grid.last()[y].height
            for (x in grid[y].indices) {
                //Left To Right
                if (grid[y][x].height > highestInRow) {
                    grid[y][x].isVisible = true
                    highestInRow = grid[y][x].height
                }

                //Top to bottom
                if (grid[x][y].height > highestInCol) {
                    grid[x][y].isVisible = true
                    highestInCol = grid[x][y].height
                }
            }


            for (x in grid[y].indices.reversed()) {
                //Right to left
                if (grid[y][x].height > highestInRowReversed) {
                    grid[y][x].isVisible = true
                    highestInRowReversed = grid[y][x].height
                }

                //Bottom to top
                if (grid[x][y].height > highestInColReversed) {
                    grid[x][y].isVisible = true
                    highestInColReversed = grid[x][y].height
                }
            }
        }

        var res = 0
        grid.forEach { row ->
            row.forEach { tree ->
                if (tree.isVisible) {
                    res += 1
                }
            }
        }
        return res
    }

    data class SceneryTree(val height: Int, var score: Int)

    fun computeHighestScenicScore(): Int {
        val scores = MutableList(grid.size) { MutableList(grid.first().size) { 1 } }
        val trees = mutableListOf<SceneryTree>()

        //Left to right
        for (y in grid.indices) {
            trees.clear()
            for (x in grid[y].indices) {
                var maxCheckedHeight = 0
                for (t in trees.indices.reversed()) {
                    if (trees[t].height > maxCheckedHeight)
                        trees[t].score += 1
                    maxCheckedHeight = max(maxCheckedHeight, trees[t].height)
                }
                trees.add(SceneryTree(grid[y][x].height, 0))
            }
            trees.forEachIndexed { index, tree -> scores[y][index] *= tree.score }
        }

        //Top to bottom
        for (y in grid.first().indices) {
            trees.clear()
            for (x in grid.indices) {
                var maxCheckedHeight = 0
                for (t in trees.indices.reversed()) {
                    if (trees[t].height > maxCheckedHeight)
                        trees[t].score += 1
                    maxCheckedHeight = max(maxCheckedHeight, trees[t].height)
                }
                trees.add(SceneryTree(grid[x][y].height, 0))
            }
            trees.forEachIndexed { index, tree -> scores[index][y] *= tree.score }
        }

        //Right to left
        for (y in grid.indices) {
            trees.clear()
            for (x in grid[y].indices.reversed()) {
                var maxCheckedHeight = 0
                for (t in trees.indices.reversed()) {
                    if (trees[t].height > maxCheckedHeight)
                        trees[t].score += 1
                    maxCheckedHeight = max(maxCheckedHeight, trees[t].height)
                }
                trees.add(SceneryTree(grid[y][x].height, 0))
            }
            trees.reversed().forEachIndexed { index, tree -> scores[y][index] *= tree.score }
        }

        //Bottom to top
        for (y in grid.first().indices) {
            trees.clear()
            for (x in grid.indices.reversed()) {
                var maxCheckedHeight = 0
                for (t in trees.indices.reversed()) {
                    if (trees[t].height > maxCheckedHeight)
                        trees[t].score += 1
                    maxCheckedHeight = max(maxCheckedHeight, trees[t].height)
                }
                trees.add(SceneryTree(grid[x][y].height, 0))
            }
            trees.reversed().forEachIndexed { index, tree -> scores[index][y] *= tree.score }
        }

        return scores.flatten().max()
    }
}