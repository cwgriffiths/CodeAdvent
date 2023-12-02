package advent23

import java.io.File

fun main() {
    val maxCubes = mapOf(
       "red" to 12,
        "green" to 14,
        "blue" to 13,
    )
    File("02.txt").readLines().map { line ->
        val id = line.substringBefore(":").substringAfter(" ").toInt()
        val cubeMap = buildMap {
            line.split(";").forEach { round ->
                maxCubes.keys.forEach {
                    round.substringBefore(" $it", "").substringAfterLast(" ").trim().toIntOrNull()?.let { count ->
                        this[it] = kotlin.math.max((this[it] ?: 0), count)
                    }
                }
            }
        }
        Game(id, cubeMap)
    }.let {
        val possibleGames = it.filter {
            it.cubes.all { (color, count) ->
                count <= maxCubes[color]!!
            }
        }.sumOf { it.id }
        val minCubes = it.map {
            it.cubes["red"]!! * it.cubes["blue"]!! * it.cubes["green"]!!
        }.sum()
        println(possibleGames)
        println(minCubes)
    }
}

data class Game(
    val id: Int,
    val cubes: Map<String, Int>
)