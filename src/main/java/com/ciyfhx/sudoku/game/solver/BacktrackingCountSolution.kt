package com.ciyfhx.sudoku.game.solver

import com.ciyfhx.sudoku.game.SIZE_OF_BOARD
import com.ciyfhx.sudoku.game.Sudoku
import com.ciyfhx.sudoku.game.SudokuCell

class BacktrackingCountSolution{

    private val possibleValues = SudokuCell.possibleValues.clone()
    private var count = 0

    fun countSolutions(sudoku: Sudoku): Int {
        count = 0
        backtrack(sudoku)
        return count
    }

    private tailrec fun backtrack(sudoku: Sudoku, index: Int = 0){
        if(index >= SIZE_OF_BOARD * SIZE_OF_BOARD){
            count++
            return
        }
        val x = index % SIZE_OF_BOARD
        val y = index / SIZE_OF_BOARD
        val currentCell = sudoku.getCell(x, y)
        if(currentCell == SudokuCell.EMPTY){
            for(guessCell in possibleValues){
                if(sudoku.setCell(guessCell, x, y)){
                    backtrack(sudoku, index + 1)
                }
            }
            sudoku.setCell(SudokuCell.EMPTY, x, y)
        }else{
            backtrack(sudoku, index + 1)
        }
    }

}