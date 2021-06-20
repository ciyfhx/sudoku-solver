package com.ciyfhx.sudoku

import com.ciyfhx.sudoku.game.Sudoku
import com.google.inject.AbstractModule

class DataModule : AbstractModule() {
    override fun configure() {
        bind(SudokuData::class.java)
            .toInstance(SudokuData())
    }
}

class SudokuData{
    var sudoku = Sudoku.generateUnsolvedRandomSudoku()
}