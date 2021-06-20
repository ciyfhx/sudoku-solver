package com.ciyfhx.sudoku.game

enum class SudokuCell {
    EMPTY,
    ONE, TWO, THREE, FOUR,
    FIVE, SIX, SEVEN, EIGHT, NINE;

    val value get() = this.ordinal

    companion object {
        val possibleValues = arrayOf(
            ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
            EIGHT, NINE
        )
    }

}