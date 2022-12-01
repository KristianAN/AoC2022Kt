fun main() {
    fun part1(input: List<String>): Int =
        input.foldIndexed(MaxCalories(0,0)) { index, acc, s ->
            if (s.isEmpty()) {
                val calories = input.subList(acc.startIndex, index).fold(0) { accum, n -> n.toInt() + accum }
                if (calories > acc.currentMax) MaxCalories(calories, index + 1)
                else MaxCalories(acc.currentMax, index + 1)
            } else {
                acc
            }
        }.currentMax


    fun part2(input: List<String>): Int =
        input.foldIndexed(MaxCalories2.emptyMC()) { index, acc: MaxCalories2, s ->
            if (s.isEmpty()) {
                val calories = input.subList(acc.startIndex, index).fold(0) { accum, n -> n.toInt() + accum }
                MaxCalories2(acc.currentMax.update(calories), index + 1)
            } else {
                acc
            }
        }.currentMax.sum()

    // test if implementation meets criteria from the description, like:

    val input = readInput("day01")
    println(part1(input))
    println(part2(input))
}

data class MaxCalories(
    val currentMax: Int,
    val startIndex: Int,
)

data class MaxCalories2(
    val currentMax: MaxValues,
    val startIndex: Int,
) {
    companion object {
        fun emptyMC() = MaxCalories2(MaxValues.emptyMV(), 0)
    }
}

data class MaxValues(
    val first: Int,
    val second: Int,
    val third: Int,
) {
    fun update(num: Int): MaxValues =
        if (num > first) MaxValues(num, first, second)
        else if (num > second) MaxValues(first, num, second)
        else if (num > third) MaxValues(first, second, num)
        else this

    fun sum() = first + second + third

   companion object {
       fun emptyMV() = MaxValues(0, 0, 0)
   }

}
