package advent22

import java.math.BigInteger
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

data class Monkey(
    val items : MutableList<BigInteger>,
    val opSymbol : Char,
    val opNum: BigInteger,
    val testSymbol: Char,
    val testNum: Int,
    val throwTarget: Pair<Int, Int>,
    var numInspected: Int = 0
    ){
    fun takeTurn(){
        while(items.isNotEmpty()) {
            numInspected++
            var item = items[0]
            items.removeAt(0)
            val tempOpNum = if (opNum != (0.toBigInteger())) opNum else item
            when (opSymbol) {
                '+' -> item = (item.plus(tempOpNum)).mod(Monkeys.modulus)
                '*' -> item = (item * tempOpNum).mod(Monkeys.modulus)
            }
            //item /= 3
            val targetMonkey = if(item.remainder(testNum.toBigInteger()) == 0.toBigInteger()) throwTarget.first else throwTarget.second
            Monkeys.passToMonkey(targetMonkey, item)
        }

    }
}

class Monkeys() {

    constructor(s : String) : this() {
        val monkeyStrings = s.trim().split("advent22.Monkey")
        monkeyStrings.forEach{ monkeyString ->
            val sc = Scanner(monkeyString.trim())
            if(sc.hasNextLine()) {
                sc.nextLine()
                val items = mutableListOf<BigInteger>()
                val itemString = sc.nextLine().split(":")[1].trim().split(", ")
                itemString.forEach{items.add(it.toBigInteger())}
                val op = sc.nextLine().split("=")[1].trim().split(" ")
                val opSymbol = op[1].trim()
                val opNum = op[2].trim().toIntOrNull() ?: 0
                val testSymbol = "/"
                val testLine = sc.nextLine().split(" ")
                val testNum = testLine[testLine.size-1].trim().toInt()
                val trueTarget = sc.nextLine().split("monkey")[1].trim().toInt()
                val falseTarget = sc.nextLine().split("monkey")[1].trim().toInt()
                val monkey = Monkey(items, opSymbol[0], opNum.toBigInteger(), testSymbol[0], testNum, Pair(trueTarget, falseTarget))
                monkeys.add(monkey)
            }
        }
    }

    fun runGame(turns: Int){
        for(i in 0 until turns){
            monkeys.forEach{it.takeTurn()}
            if(i%20==0){
                println("Turn $i")
                for (i in 0 until monkeys.size){
                    println("advent22.Monkey $i inspected ${monkeys[i].numInspected} items")
                }
            }
        }

        monkeys.sortBy { it.numInspected }

        for (i in 0 until monkeys.size){
            println("advent22.Monkey $i inspected ${monkeys[i].numInspected} items")
        }
    }

    companion object {
        private val monkeys = mutableListOf<Monkey>()
        val modulus = 9699690.toBigInteger() //Product of multiplying all the divisors

        fun passToMonkey(monkeyNum: Int, item: BigInteger){
            monkeys[monkeyNum].items.add(item)
        }
    }

}

fun main(){
    val file = Files.readString(Path.of("2022/11.txt"))
    val monkeys = Monkeys(file)

    monkeys.runGame(10000)



}



