package com.ciyfhx.sudoku.game.solver

import com.ciyfhx.sudoku.game.Sudoku

interface Solver {

    fun solve(sudoku: Sudoku): Boolean

}