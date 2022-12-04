fun List<String>.rangeToList(): List<Int> = this.map {num -> num.toInt()}.let {
    (it[0]..it[1]).toList()
}

fun List<List<Int>>.overlaps(): Boolean = this[0].intersect(this[1].toSet()).let {
    it.size == this[0].size || it.size == this[1].size
}

fun List<List<Int>>.containsItem(): Boolean = this[0].intersect(this[1].toSet()).isNotEmpty()

fun main() {
    fun part1(input: List<String>): Int = input.fold(0) { acc, s ->
        if (s.split(",").map {
            it.split("-").rangeToList()
        }.overlaps()) acc + 1 else acc
    }

    fun part2(input: List<String>): Int = input.fold(0) { acc, s ->
        if (s.split(",").map {
            it.split("-").rangeToList()
        }.containsItem()) acc + 1 else acc
    }

    val input = readInput("day04")
    println(part1(input))
    println(part2(input))
}
