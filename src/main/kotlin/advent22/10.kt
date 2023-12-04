package advent22

import java.util.*
import kotlin.collections.ArrayList

enum class Instructions(val value: Int) {
    noop(0),
    addx(1),
}

fun main() {
    val queue = ArrayList<Pair<Int, Int>>()
    val sc = Scanner(System.`in`)

    while(sc.hasNextLine()){
        val line = sc.nextLine()
        val split = line.split(" ")
        val instruction = split[0]
        val value = if(split.size>1) split[1].toInt() else 0
        if(instruction == "noop"){
            queue.add(Pair(Instructions.noop.value, value))
        }else {
            queue.add(Pair(Instructions.noop.value, value))
            queue.add(Pair(Instructions.valueOf(instruction).value, value))
        }
    }

    var cycle = 0
    val values = IntArray(queue.size+1)
    values[cycle] = 1

    queue.forEach{
        if(it.first == Instructions.addx.value){
            values[cycle + 1] = values[cycle] + it.second
        }else{
            values[cycle + 1] = values[cycle]
        }
        cycle++
    }

    /*
    val toSum = listOf(20,60,100,140,180,220)
    var sum = 0

    toSum.forEach{
        val num = values[it-1] * it
        sum+=num
        println("${values[it-1]} * $it = $num")
    }
    */
    println(buildString(values))
}

fun buildString(values: IntArray): String{
    val sb = StringBuilder()
    for(i in values.indices) {
        if (i%40 in values[i] - 1..values[i] + 1) {
            sb.append("#")
        } else {
            sb.append(".")
        }
        if ((i + 1) % 40 == 0) {
            sb.append("\n")
        }
    }
        return sb.toString()
}
