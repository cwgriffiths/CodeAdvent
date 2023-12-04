package advent22

import java.util.*

fun getPriority(a: Char) : Int {
    if(a in 'a'..'z') return a.code - 'a'.code+1
    return a.code - 'A'.code+1 + 26
}

fun main(){
    val sc = Scanner(System.`in`)
    var sum = 0
    while(sc.hasNextLine()){
        val word = sc.nextLine()
        /*val left = word.substring(0, word.length/2)
        val right = word.substring(word.length/2)
        for(it in left){
            if(it in right){
                sum += advent22.getPriority(it)
                println(it)
                break
            }
        }*/
        val word2 = sc.nextLine()
        val word3 = sc.nextLine()
        for(it in word){
            if(it in word2 && it in word3){
                sum += getPriority(it)
                println(it)
                break
            }
        }
    }
    println(sum)
}