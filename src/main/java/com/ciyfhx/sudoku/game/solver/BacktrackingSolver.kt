package com.ciyfhx.sudoku.game.solver

import com.ciyfhx.sudoku.game.SIZE_OF_BOARD
import com.ciyfhx.sudoku.game.Sudoku
import com.ciyfhx.sudoku.game.SudokuCell

class BacktrackingSolver : Solver{

    private val possibleValues = SudokuCell.possibleValues.clone()
    var shuffleSearchValues = false

    override fun solve(sudoku: Sudoku): Boolean {
        return backtrack(sudoku)
    }

    private tailrec fun backtrack(sudoku: Sudoku, index: Int = 0): Boolean{
        if(index >= SIZE_OF_BOARD * SIZE_OF_BOARD) return true
        val x = index % SIZE_OF_BOARD
        val y = index / SIZE_OF_BOARD
        val currentCell = sudoku.getCell(x, y)
        if(currentCell == SudokuCell.EMPTY){
            //Randomize search
            if(shuffleSearchValues)possibleValues.shuffle()

            for(guessCell in possibleValues){
                if(sudoku.setCell(guessCell, x, y)){
                    val valid = backtrack(sudoku, index + 1)
                    if(valid){
                        return true
                    }
                }
            }
            sudoku.setCell(SudokuCell.EMPTY, x, y)
            return false
        }else{
            return backtrack(sudoku, index + 1)
        }
    }

}