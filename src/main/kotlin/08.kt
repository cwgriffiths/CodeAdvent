import java.nio.file.Files
import java.nio.file.Path

fun main() {
    var rowLen = 0
    var colHeight: Int
    val forestArr = Files.readString(Path.of("08.txt")).trim().split("\n").let {
        colHeight = it.size
        it.map {
            rowLen = it.trim().length
            it.trim().toCharArray().map {
                it.digitToInt()
            }
        }.flatten()
    }
    val forest = Forest(forestArr, rowLen, colHeight)
    println(forest)
    println(forest.visibleTreeCount())
    println(forest.getHighestScenicScore())
}

data class Forest(val trees: List<Int>, val rowLen: Int, val colHeight: Int) {
    private fun getTreeHeight(x: Int, y: Int) = trees[y * rowLen + x]
    private fun indexIsVisible(i: Int): Boolean {
        val x = i % rowLen
        val y = i / rowLen
        if (x == 0 || y == 0 || x == rowLen - 1 || y == colHeight - 1) return true
        return Direction.values().any {
            tallestTreeInDirection(x + it.x, y + it.y, it)  < trees[i]
        }
    }

    fun visibleTreeCount() = trees.filterIndexed { index, _ -> indexIsVisible(index) }.count()

    private fun tallestTreeInDirection(x: Int, y: Int, direction: Direction): Int {
        if (x == 0 || y == 0 || x == rowLen - 1 || y == colHeight - 1) return getTreeHeight(x, y)
        return tallestTreeInDirection(x + direction.x, y + direction.y, direction).coerceAtLeast(getTreeHeight(x, y))
    }

    private fun calculateScenicScore(index: Int): Int{
        val height = trees[index]
        val x = index % rowLen
        val y = index / rowLen
        return Direction.values().map {
            var tempX = x + it.x
            var tempY = y + it.y
            var i = 0
            while(tempX in 0 until rowLen && tempY in 0 until colHeight){
                i++
                if(getTreeHeight(tempX, tempY) >= height) break
                tempX += it.x
                tempY += it.y
            }
            i
        }.let {
            println(it)
            it.reduce { sum, element -> sum * element }
        }
    }

    fun getHighestScenicScore(): Int {
        var highestScore = 0
        trees.forEachIndexed { index, _ ->
            val score = calculateScenicScore(index)
            if(score > highestScore) highestScore = score
        }
        return highestScore
    }

    enum class Direction(val x: Int, val y: Int) {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0),
    }
}