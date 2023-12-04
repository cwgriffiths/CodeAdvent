package advent22

import java.nio.file.Files
import java.nio.file.Path

fun main(){
    val string = Files.readString(Path.of("2022/06.txt"))
    val arr = CharArray(14)
    var found : Boolean
    for (i in 0..string.length){
        arr[i % arr.size] = string[i]
        if(i>=4){
            println(arr)
            found = true
            arr.forEach {
                    target -> val c = arr.count { it == target }
                    if(c!=1) found = false
            }
            if(found){
                println(arr)
                println(i+1)
                break
            }
        }

    }
}