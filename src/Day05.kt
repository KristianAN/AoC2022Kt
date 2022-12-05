import java.util.*

fun main() {
    fun part1(input: List<String>): String = loadInput(input, 8, 9).move { stacks, i, i2, i3 ->
            moveOne(stacks, i, i2, i3)
        }.second.topToString()

    fun part2(input: List<String>): String = loadInput(input, 8, 9).move { stacks, i, i2, i3 ->
            moveAll(stacks, i, i2, i3)
        }.second.topToString()

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

fun List<Stack<Char>>.topToString(): String =
    this.map { it.last() }.joinToString("")

fun Pair<List<String>, List<Stack<Char>>>.move(moveFunc: (List<Stack<Char>>, Int, Int, Int) -> Unit): Pair<List<String>, List<Stack<Char>>>  {
    this.first.forEach { move -> move.parseMoves()
        .let { moveFunc(this.second, it.amount, it.from, it.to) }
    }
    return this
}

fun moveAll(input: List<Stack<Char>>, amount: Int, from: Int, to: Int) = (0 until (amount)).map { _ ->
        input[from - 1].pop() }.reversed().forEach { input[to - 1].add(it) }

fun moveOne(input: List<Stack<Char>>, amount: Int, from: Int, to: Int) = (0 until (amount)).forEach { _ ->
        input[to - 1].add(input[from - 1].pop()) }

fun String.parseMoves(): Moves = this.split(" ").filter { it.all(Char::isDigit) }
    .map { it.toInt() }.let { Moves(it[0], it[1], it[2]) }

data class Moves(val amount: Int, val from: Int, val to: Int)