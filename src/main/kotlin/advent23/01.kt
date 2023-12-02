package advent23

import java.io.File

fun main() {
    val ans = File("01.txt").readLines().map {
        it.trim().let {
            var updatedString = it
            numbers.forEachIndexed { index, s ->
                updatedString = updatedString.replace(s, s + index.toString() + s)
            }
            updatedString
        }.toCharArray().map {
            it.digitToIntOrNull()
        }.filterNotNull()
    }.sumOf {
        "${it.first()}${it.last()}".toInt()
    }
    println(ans)
}

val numbers: List<String> = listOf(
    "zero",
    "one",
    "two",
    "three",
    "four",
    "five",
    "six",
    "seven",
    "eight",
    "nine",
    "ten",
    "eleven",
    "twelve",
)