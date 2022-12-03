import java.util.InputMismatchException

fun main() {
    fun part1(input: List<String>): Int = input.fold(0) { acc, s ->
        if (s.length == 1)  acc
        else {
            val char = s.substring(0, s.length / 2).find { char ->
                s.substring(s.length / 2, s.length).contains(char)
            }
            if (char != null) {
                if (char.isUpperCase())  acc + (char.code - 38)
                else acc + (char.code - 96)
            } else {
                acc
            }
        }
    }

    fun part2(input: List<String>): Int = input.chunked(3).map {
        it[0].first { char -> (it[1].contains(char) && it[2].contains(char)) }.let { char ->
            if (char.isLowerCase()) char.code.minus(96) else char.code.minus(38)
        }
    }.sumOf { it }

    // test if implementation meets criteria from the description, like:

    val input = readInput("day03")
    println(part1(input))
    println(part2(input))
}

