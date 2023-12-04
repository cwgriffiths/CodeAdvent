package advent23

import java.io.File
import kotlin.math.pow

fun main() {
    val rows = File("04.txt").readLines().map {
        it.substring(it.indexOf(':')).split('|').map {
            it.trim().split(" ").mapNotNull { it.toIntOrNull() }.toSet()
        }
    }
    val pt1Ans = rows.sumOf {
        it[0].filter { num -> num in it[1] }.let {
            if (it.isNotEmpty()) {
                2.0.pow(it.size - 1).toInt()
            } else 0
        }
    }
    println(pt1Ans)
    val pt2Arr = rows.mapIndexed { index, it ->
        val cardCount = it[0].filter { num -> num in it[1] }.size
        1 to (1..cardCount).map { index + it }
    }.toMutableList()
    pt2Arr.forEach {
        repeat(it.first) { _ ->
            it.second.forEach { index ->
                if (pt2Arr.getOrNull(index) != null) {
                    pt2Arr[index] = pt2Arr[index].copy(
                        first = pt2Arr[index].first + 1
                    )
                }
            }
        }
    }
    println(pt2Arr.sumOf { it.first })
}