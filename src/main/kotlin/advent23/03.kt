package advent23

import java.io.File

fun main() {
    val array = File("03.txt").readLines().map {
        it.trim().toCharArray().toList()
    }
    println(array)
    val gearIndexes = mutableListOf<Pair<Int, Int>>()
    val numbers = buildList {
        array.forEachIndexed { index, chars ->
            chars.joinToString("").let {
                Regex("\\*").findAll(it).forEach {
                    gearIndexes.add(it.range.first to index)
                }
                Regex("\\d+").findAll(it).forEach {
                    add(PartNumber(number = it.value.toInt(), startIndex = it.range.first to index))
                }
            }
        }
    }
    val pt1Ans = numbers.filter {
        array.searchConnectingIndexesForNonEmpty(it.startIndex, it.endIndex)
    }.sumOf {
        it.number
    }
    println(pt1Ans)
    val pt2Ans = gearIndexes.sumOf {
        numbers.pointAdjacentToTwo(it)
    }
    println(pt2Ans)
}

fun List<List<Char>>.searchConnectingIndexesForNonEmpty(startIndex: Pair<Int, Int>, endIndex: Pair<Int, Int>): Boolean {
    val connectingPoints = buildConnectingPointList(startIndex, endIndex)
    return connectingPoints.any {
        this.getOrNull(it.second)?.getOrNull(it.first) !in listOf('.', null)
    }
}

fun List<PartNumber>.pointAdjacentToTwo(index: Pair<Int, Int>): Int {
    val connectingPoints = buildConnectingPointList(index)
    val adjacent = this.filter { part ->
        ((part.startIndex.first..part.endIndex.first).map { it to part.endIndex.second }).any {
            it in connectingPoints
        }
    }
    return if (adjacent.size == 2) adjacent[0].number * adjacent[1].number else 0
}

fun buildConnectingPointList(startIndex: Pair<Int, Int>, endIndex: Pair<Int, Int> = startIndex) = buildList {
    add(startIndex.copy(first = startIndex.first - 1))
    add(endIndex.copy(first = endIndex.first + 1))
    addAll((startIndex.first - 1..endIndex.first + 1).map { it to startIndex.second - 1 })
    addAll((startIndex.first - 1..endIndex.first + 1).map { it to startIndex.second + 1 })
}

data class PartNumber(val number: Int, val startIndex: Pair<Int, Int>) {
    val endIndex = startIndex.copy(first = startIndex.first + number.toString().length - 1)
}