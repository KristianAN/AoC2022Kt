fun Char.valueOf(): Int =
    if (this.isLowerCase()) this.code.minus(96) else this.code.minus(38)

fun main() {
    fun part1(input: List<String>): Int = input.map { s ->
        s.chunked(s.length / 2).let { it[0].toSet().intersect(it[1].toSet()) }.toCharArray()[0]
    }.sumOf { it.valueOf() }

    fun part2(input: List<String>): Int = input.chunked(3).map { rucksacks ->
        rucksacks[0].first { char -> (rucksacks[1].contains(char) && rucksacks[2].contains(char)) }
    }.sumOf { it.valueOf() }

    val input = readInput("day03")
    println(part1(input))
    println(part2(input))
}

