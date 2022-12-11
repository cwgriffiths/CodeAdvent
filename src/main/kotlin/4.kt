import java.util.*

fun main(){
    val sc = Scanner(System.`in`)
    var sum = 0
    while(sc.hasNextLine()) {
        val (p1, p2) = sc.nextLine().split(",")
        val (p1num1, p1num2) = p1.split("-")
        val (p2num1, p2num2) = p2.split("-")
        val p1Range = p1num1.toInt()..p1num2.toInt()
        val p2Range = p2num1.toInt()..p2num2.toInt()
        if(p1Range.first in p2Range || p1Range.last in p2Range || p2Range.first in p1Range || p2Range.last in p1Range) {
            sum ++
        }
    }
    println(sum)
}