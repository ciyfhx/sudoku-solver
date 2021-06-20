@file:Suppress("JAVA_MODULE_DOES_NOT_DEPEND_ON_MODULE")
package com.ciyfhx.sudoku.test

import com.ciyfhx.sudoku.game.Sudoku
import com.ciyfhx.sudoku.game.solver.BacktrackingSolver
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertTrue


class TestSudokuSolver {

    private val sampleSudoku = arrayOf(
        0,0,0,  0,0,0, 2,0,0,
        0,8,0,  0,0,7, 0,9,0,
        6,0,2,  0,0,0, 5,0,0,

        0,7,0,  0,6,0, 0,0,0,
        0,0,0,  9,0,1, 0,0,0,
        0,0,0,  0,2,0, 0,4,0,

        0,0,5,  0,0,0, 6,0,3,
        0,9,0,  4,0,0, 0,7,0,
        0,0,6,  0,0,0, 0,0,0,
    )
    private val sampleSudokuSolution = arrayOf(
        9,5,7,  6,1,3, 2,8,4,
        4,8,3,  2,5,7, 1,9,6,
        6,1,2,  8,4,9, 5,3,7,

        1,7,8,  3,6,4, 9,5,2,
        5,2,4,  9,7,1, 3,6,8,
        3,6,9,  5,2,8, 7,4,1,

        8,4,5,  7,9,2, 6,1,3,
        2,9,1,  4,3,6, 8,7,5,
        7,3,6,  1,8,5, 4,2,9,
    )

    @Test
    fun solveSudoku(){
        val sudoku = Sudoku(sampleSudoku)
        val solver = BacktrackingSolver()

        assertTrue(solver.solve(sudoku))

        val solvedSolutionArray = sudoku.state.map { it.value }.toTypedArray()

        assertContentEquals(sampleSudokuSolution, solvedSolutionArray)
    }

}