import java.util.*
import kotlin.collections.ArrayList

fun main(){
    val sc = Scanner(System.`in`)
    val elves = ArrayList<Int>()
    elves.add(0)
    while(sc.hasNextLine()){
        val line = sc.nextLine().toIntOrNull()
        if(line == null){
            elves.add(0)
            continue
        }
        elves[elves.size-1] += line
    }
    var sum = 0
    for(i in 1..3) {
        sum += elves.max()
        elves[elves.indices.find { elves[it] == elves.max() }!!] = 0
        println(i)
    }
    println(sum)
}