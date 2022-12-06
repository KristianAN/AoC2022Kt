data class CharAndIndex(val char: Char, val index: Int)
data class Window(val windowSize: Int, val window: List<CharAndIndex?>) {

    fun update(char: Char, index: Int): Window =
        Window(windowSize, this.window.drop(1).plus(CharAndIndex(char, index)))

    fun nonEq() = this.window.map { it?.char ?: "" }.toSet().size == windowSize && noNull()

    fun len() = this.window.last()?.index?.plus(1) ?: 0

    private fun noNull() = this.window.filterNotNull().size == windowSize

    companion object {
        fun new(windowSize: Int): Window = Window(windowSize, List(windowSize) { _ -> null })
    }
}

fun String.uniqueWindow(windowSize: Int): Window = uniqueWindow(Window.new(windowSize), this, 0)

tailrec fun uniqueWindow(window: Window, input: String, index: Int): Window {
    if (window.nonEq()) return window
    return uniqueWindow(window.update(input[index], index), input, index + 1)
}

fun main() {
    fun part1(input: List<String>): Int = input.first().uniqueWindow(4).len()

    fun part2(input: List<String>): Int = input.first().uniqueWindow(14).len()

    val input = readInput("day06")
    println(part1(input))
    println(part2(input))
}