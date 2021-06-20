package com.ciyfhx.sudoku.game

import com.ciyfhx.sudoku.game.solver.BacktrackingCountSolution
import com.ciyfhx.sudoku.game.solver.BacktrackingSolver
import java.util.*
import kotlin.random.Random


const val SIZE_OF_BOARD = 9
const val SIZE_OF_GRID = 3

class Sudoku(grid: Array<Int>) {

    lateinit var state: Array<SudokuCell>
        private set

    private lateinit var rowsFlags: Array<BitSet>
    private lateinit var colsFlags: Array<BitSet>
    private lateinit var gridsFlags: Array<BitSet>

    private var sudokuListener: SudokuListener? = null

    fun setSudokuListener(listener: SudokuListener){
        this.sudokuListener = listener
    }

    init {
        this.init(grid)
    }

    private fun init(grid: Array<Int>){
        state = Array(SIZE_OF_BOARD * SIZE_OF_BOARD) {
            val cellValue = grid[it]
            SudokuCell.values()[cellValue]
        }
        rowsFlags = Array(SIZE_OF_BOARD){ BitSet(SIZE_OF_BOARD) }
        colsFlags = Array(SIZE_OF_BOARD){ BitSet(SIZE_OF_BOARD) }
        gridsFlags = Array(SIZE_OF_BOARD){ BitSet(SIZE_OF_BOARD) }
        //Update flags
        for((i, cell) in state.withIndex()){
            val x = i % SIZE_OF_BOARD
            val y = i / SIZE_OF_BOARD

            if(cell != SudokuCell.EMPTY){
                this.setCell(cell, x, y)
            }
        }
    }

    fun setCell(cell: SudokuCell, x: Int, y: Int) : Boolean{

        if(cell == SudokuCell.EMPTY){
            clearCell(x, y)
            return true
        }

        val oldCell = state[y * SIZE_OF_BOARD + x]
        //Clear the old cell if the cell is not empty
        if(oldCell != SudokuCell.EMPTY){
            clearCell(x, y)
        }
        if(checkPositionIsInvalid(cell, x, y)){
            //Set back the old cell
            setCellNoCheck(oldCell, x, y)
            return false
        }

        setCellNoCheck(cell, x, y)

        return true

    }

    private fun setCellNoCheck(cell: SudokuCell, x: Int, y: Int){
        state[y * SIZE_OF_BOARD + x] = cell

        colsFlags[x].set(cell.ordinal)
        rowsFlags[y].set(cell.ordinal)
        val gridIndex = getGridIndex(x, y)
        gridsFlags[gridIndex].set(cell.ordinal)
        sudokuListener?.onSet(cell, x, y)
    }

    private fun checkPositionIsInvalid(cell: SudokuCell, x: Int, y: Int): Boolean {
        return colsFlags[x].get(cell.ordinal) || rowsFlags[y].get(cell.ordinal) ||
                gridsFlags[getGridIndex(x, y)].get(cell.ordinal)
    }

    inline fun getCell(x: Int, y: Int) =
         state[y * SIZE_OF_BOARD + x]

    private inline fun getGridIndex(x: Int, y: Int) =
        (y / SIZE_OF_GRID * SIZE_OF_GRID) + (x / SIZE_OF_GRID)

    private fun clearCell(x: Int, y: Int){
        val oldCell = state[y * SIZE_OF_BOARD + x]
        state[y * SIZE_OF_BOARD + x] = SudokuCell.EMPTY

        colsFlags[x].clear(oldCell.ordinal)
        rowsFlags[y].clear(oldCell.ordinal)
        val gridIndex = getGridIndex(x, y)
        gridsFlags[gridIndex].clear(oldCell.ordinal)
    }


    override fun toString(): String {
        val builder = StringBuilder()
        for(y in 0 until SIZE_OF_BOARD){
            for(x in 0 until SIZE_OF_BOARD){
                builder.append(getCell(x, y).value)
            }
            builder.append("\n")
        }
        return builder.toString()
    }

    companion object {
        fun generateRandomSudoku(): Sudoku{
            val solver = BacktrackingSolver().also { it.shuffleSearchValues = true }
            val completedSodoku = Sudoku(
                Array(SIZE_OF_BOARD * SIZE_OF_BOARD) { 0 }
            )
            solver.solve(completedSodoku)

            return completedSodoku

        }

        fun generateUnsolvedRandomSudoku(): Sudoku {
            val completedSudoku = generateRandomSudoku()

            println(completedSudoku)

            val solverCount = BacktrackingCountSolution()

            var removedCell: SudokuCell
            var solutionCount: Int
            var x: Int
            var y: Int
            do {
                x = Random.nextInt(SIZE_OF_BOARD)
                y = Random.nextInt(SIZE_OF_BOARD)

                //Removed cell
                removedCell = completedSudoku.getCell(x, y)
                completedSudoku.setCell(SudokuCell.EMPTY, x, y)

                solutionCount = solverCount.countSolutions(completedSudoku)

            }while(solutionCount == 1)

            completedSudoku.setCell(removedCell, x, y)
            println()
            println(completedSudoku)

            return completedSudoku

        }
    }

    interface SudokuListener{
        fun onSet(newCell: SudokuCell, x: Int, y: Int)
    }

}

fun main() {
//    val sudoku = Sudoku()
//    while(true){
//        println(sudoku)
//
//        val input = readLine()!!.split(" ")
//        val x = input[0].toInt()
//        val y = input[1].toInt()
//
//        sudoku.setCell(SudokuCell.EIGHT, x, y)
//
//    }
}