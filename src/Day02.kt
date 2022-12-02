fun main() {
    fun part1(input: List<String>): Int =
        input.fold(0) { acc, s ->
            val moves = s.split(" ")
            acc + play(Move.fromInput(moves[0]), Move.fromInput(moves[1]))
        }

    fun part2(input: List<String>): Int =
        input.fold(0) { acc, s ->
            val moves = s.split(" ")
            acc + decode(Move.fromInput(moves[0]), Move.fromInput(moves[1]))
        }
    // test if implementation meets criteria from the description, like:

    val input = readInput("day02")
    println(part1(input))
    println(part2(input))
}

enum class Move(val value: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    companion object {
        fun fromInput(input: String): Move = when (input) {
            "A", "X"-> ROCK
            "B", "Y" -> PAPER
            "C", "Z" -> SCISSORS
            else -> throw Exception("Invalid input: $input")
        }
    }

}
fun play(move1: Move, move2: Move): Int = when (move1) {
        Move.ROCK -> if (move2 == Move.ROCK) move2.value + 3 else if (move2 == Move.PAPER) move2.value + 6 else move2.value
        Move.PAPER -> if (move2 == Move.PAPER) move2.value + 3 else if (move2 == Move.SCISSORS) move2.value + 6 else move2.value
        Move.SCISSORS -> if (move2 == Move.SCISSORS) move2.value + 3 else if (move2 == Move.ROCK) move2.value + 6 else move2.value
    }

fun decode(move1: Move, move2: Move): Int = when (move1) {
        Move.ROCK -> if (move2 == Move.ROCK) Move.SCISSORS.value else if (move2 == Move.PAPER) Move.ROCK.value + 3 else Move.PAPER.value + 6
        Move.PAPER -> if (move2 == Move.ROCK) Move.ROCK.value else if (move2 == Move.PAPER) Move.PAPER.value + 3 else Move.SCISSORS.value + 6
        Move.SCISSORS -> if (move2 == Move.ROCK) Move.PAPER.value else if (move2 == Move.PAPER) Move.SCISSORS.value + 3 else Move.ROCK.value + 6
    }

