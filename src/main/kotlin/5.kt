import java.util.*

data class Stacks(val stacks : List<Stack<Char>> = listOf(Stack(),Stack(),Stack(),Stack(),Stack(),Stack(),Stack(),Stack(),Stack(),)){
    fun move(from: Int, to: Int){
            stacks[to].push(stacks[from].pop())
    }

    fun moveMultiple(from: Int, to: Int, count: Int){
        val builder = StringBuilder()
        for(i in 0 until count){
            builder.append(stacks[from].pop())
        }
        builder.reverse()
        push(builder.toString(), to)
    }

    fun push(chars: String, to: Int){
        chars.forEach {stacks[to].push(it)}
    }

    override fun toString(): String {
        val builder = StringBuilder()
        stacks.forEach {builder.append(if(it.isNotEmpty())it.peek() else "")}
        return builder.toString()
    }
}

fun main() {
    val stacks = Stacks()
    build(stacks)

    val sc = Scanner(System.`in`)
    while (sc.hasNextLine()) {
        val line = sc.nextLine().split(" ")
        val count = line[1].toInt()
        val from = line[3].toInt()
        val to = line[5].toInt()
        //for(i in 1..count){
          //  stacks.move(from-1, to-1)
        //}
        stacks.moveMultiple(from-1, to-1, count)
    }
    println(stacks)

}

fun build(stacks : Stacks){
    stacks.push("JHPMSFNV", 0)
    stacks.push("SRLMJDQ", 1)
    stacks.push("NQDHCSQB", 2)
    stacks.push("RSCL", 3)
    stacks.push("MVTPFB", 4)
    stacks.push("TRQNC", 5)
    stacks.push("GVR", 6)
    stacks.push("CZSPDLR", 7)
    stacks.push("DSJVGPBF", 8)
}