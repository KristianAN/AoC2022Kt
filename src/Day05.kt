import java.util.*

fun main() {
    fun part1(input: List<String>): String =
        loadInput(input, 8, 9).moveOne().second.topToString()



    fun part2(input: List<String>): String =
        loadInput(input, 8, 9).moveAll().second.topToString()

    val input = readInput("day05")
    println(part1(input))
    println(part2(input))
}

fun loadQueues(input: List<String>, stacks: Int): List<Stack<Char>> {
    val queues = mutableListOf<Stack<Char>>()
    (0 until stacks).forEach { _ -> queues.add(Stack()) }
    input.reversed().forEach {
        it.forEachIndexed { index, char ->
            if (char != ' ' && char != '[' && char != ']') queues[index / 4].add(char)
        }
    }

    return queues
}

fun loadInput(input: List<String>, stackHeight: Int, stacks: Int): Pair<List<String>, List<Stack<Char>>> =
    Pair(input.subList(stackHeight + 2, input.size), loadQueues(input.subList(0, stackHeight), stacks))

fun Pair<List<String>, List<Stack<Char>>>.moveOne(): Pair<List<String>, List<Stack<Char>>> {
    this.first.forEach {
        val moves = it.parseMoves()
        this.second.moveOne(moves.amount, moves.from, moves.to)
    }
    return this
}

fun Pair<List<String>, List<Stack<Char>>>.moveAll(): Pair<List<String>, List<Stack<Char>>> {
    this.first.forEach {
        val moves = it.parseMoves()
        this.second.moveAll(moves.amount, moves.from, moves.to)
    }
    return this
}

fun List<Stack<Char>>.topToString(): String =
    this.map { it.last() }.joinToString("")


fun List<Stack<Char>>.moveAll(amount: Int, from: Int, to: Int) {
    val toMove = (0 until (amount)).map { _ ->
        this[from - 1].pop()
    }

    toMove.reversed().forEach { this[to- 1].add(it)}
}

fun List<Stack<Char>>.moveOne(amount: Int, from: Int, to: Int) {
    (0 until (amount)).forEach { _ ->
        this[to - 1].add(this[from - 1].pop())
    }
}

fun String.parseMoves(): Moves {
    val nums = this.split(" ").filter { it.all(Char::isDigit) }.map { it.toInt() }
    return Moves(nums[0], nums[1], nums[2])
}

data class Moves(
    val amount: Int,
    val from: Int,
    val to: Int
)