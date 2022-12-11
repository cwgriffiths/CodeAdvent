import java.util.*

enum class Opponent{
    A,B,C
}


enum class Player{
    X{
        override fun score(opponent : Opponent): Int {
            return when(opponent){
                Opponent.A -> 4
                Opponent.B -> 1
                Opponent.C -> 7
            }
        }
    },
    Y {
        override fun score(opponent: Opponent): Int {
            return when(opponent){
                Opponent.A -> 8
                Opponent.B -> 5
                Opponent.C -> 2
            }
        }
    },
    Z {
        override fun score(opponent: Opponent): Int {
            return when(opponent){
                Opponent.A -> 3
                Opponent.B -> 9
                Opponent.C -> 6
            }
        }
    };

    abstract fun score(opponent: Opponent): Int
}

/*
enum class Player{
    X{
        override fun score(opponent : Opponent): Int {
            return when(opponent){
                Opponent.A -> 3
                Opponent.B -> 1
                Opponent.C -> 2
            }
        }
    },
    Y {
        override fun score(opponent: Opponent): Int {
            return when(opponent){
                Opponent.A -> 3 + 1
                Opponent.B -> 3 + 2
                Opponent.C -> 3 + 3
            }
        }
    },
    Z {
        override fun score(opponent: Opponent): Int {
            return when(opponent){
                Opponent.A -> 6 + 2
                Opponent.B -> 6 + 3
                Opponent.C -> 6 + 1
            }
        }
    };

    abstract fun score(opponent: Opponent): Int
}
 */

fun main(){
    val sc = Scanner(System.`in`)
    var sum = 0
    while(sc.hasNextLine()){
        val (o, p) = sc.nextLine().split(" ")
        val player = Player.valueOf(p)
        val opponent = Opponent.valueOf(o)
        sum+=player.score(opponent)
    }
    println(sum)
}
