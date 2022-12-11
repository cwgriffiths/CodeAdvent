import java.nio.file.Files
import java.nio.file.Path
import java.util.*

data class Monkey(
    val items : MutableList<Long>,
    val opSymbol : Char,
    val opNum: Long,
    val testSymbol: Char,
    val testNum: Long,
    val throwTarget: Pair<Int, Int>,
    var numInspected: Int = 0
    ){
    fun takeTurn(){
        while(items.isNotEmpty()) {
            numInspected++
            var item = items[0]
            items.removeAt(0)
            val tempOpNum = if (opNum != 0L) opNum else item
            when (opSymbol) {
                '+' -> item = (item + tempOpNum) % (Monkeys.modulus)
                '*' -> item = (item * tempOpNum) % (Monkeys.modulus)
            }
            //item /= 3
            val targetMonkey = if(item % testNum == 0L) throwTarget.first else throwTarget.second
            Monkeys.passToMonkey(targetMonkey, item)
        }

    }
}

class Monkeys() {

    constructor(s : String) : this() {
        val monkeyStrings = s.trim().split("Monkey")
        monkeyStrings.forEach{ monkeyString ->
            val sc = Scanner(monkeyString.trim())
            if(sc.hasNextLine()) {
                sc.nextLine()
                val items = mutableListOf<Long>()
                val itemString = sc.nextLine().split(":")[1].trim().split(", ")
                itemString.forEach{items.add(it.toLong())}
                val op = sc.nextLine().split("=")[1].trim().split(" ")
                val opSymbol = op[1].trim()
                val opNum = op[2].trim().toIntOrNull() ?: 0
                val testSymbol = "/"
                val testLine = sc.nextLine().split(" ")
                val testNum = testLine[testLine.size-1].trim().toInt()
                val trueTarget = sc.nextLine().split("monkey")[1].trim().toInt()
                val falseTarget = sc.nextLine().split("monkey")[1].trim().toInt()
                val monkey = Monkey(items, opSymbol[0], opNum.toLong(), testSymbol[0], testNum.toLong(), Pair(trueTarget, falseTarget))
                monkeys.add(monkey)
            }
        }
    }

    fun runGame(turns: Int){
        for(i in 0 until turns){
            monkeys.forEach{it.takeTurn()}
            if(i%20==0){
                println("Turn $i")
                for (j in 0 until monkeys.size){
                    println("Monkey $j inspected ${monkeys[j].numInspected} items")
                }
            }
        }

        monkeys.sortBy { it.numInspected }

        for (i in 0 until monkeys.size){
            println("Monkey $i inspected ${monkeys[i].numInspected} items")
        }
    }

    companion object {
        private val monkeys = mutableListOf<Monkey>()
        const val modulus = 9699690L //Product of multiplying all the divisors

        fun passToMonkey(monkeyNum: Int, item: Long){
            monkeys[monkeyNum].items.add(item)
        }
    }

}

fun main(){
    val file = Files.readString(Path.of("11.txt"))
    val monkeys = Monkeys(file)
    monkeys.runGame(10000)
}



