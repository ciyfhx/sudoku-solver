package com.ciyfhx.sudoku.controller

import com.ciyfhx.sudoku.SudokuData
import com.ciyfhx.sudoku.game.SIZE_OF_BOARD
import com.ciyfhx.sudoku.game.SIZE_OF_GRID
import com.ciyfhx.sudoku.game.Sudoku
import com.ciyfhx.sudoku.game.SudokuCell
import com.ciyfhx.sudoku.game.solver.BacktrackingSolver
import com.google.inject.Inject
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.layout.GridPane
import java.net.URL
import java.util.*
import kotlin.concurrent.thread


class SudokuGUIController : Initializable, Sudoku.SudokuListener {

    @Inject
    private lateinit var sudokuData: SudokuData

    @FXML
    private lateinit var contentGridPane: GridPane

    private lateinit var gridControllers: Array<SudokuSingleGridController>

    override fun initialize(url: URL?, resources: ResourceBundle?) {
        sudokuData.sudoku.setSudokuListener(this)

        // Load grid
        gridControllers = Array(SIZE_OF_BOARD){ i ->
            val x = i % SIZE_OF_GRID
            val y = i / SIZE_OF_GRID
            val loader = FXMLLoader(javaClass.classLoader.getResource("sudoku-single-grid.fxml"))
            val gridPane = loader.load<GridPane>()
            contentGridPane.add(gridPane, x, y, 1, 1)
            loader.getController()
        }

        gridControllers.onEachIndexed { controllerIndex, controller ->
            val controllerX = controllerIndex % SIZE_OF_GRID
            val controllerY = controllerIndex / SIZE_OF_GRID
            controller.setGridListener{ index ->
                val xOffset = index % SIZE_OF_GRID
                val yOffset = index / SIZE_OF_GRID
                val x = xOffset + (controllerX * SIZE_OF_GRID)
                val y = yOffset + (controllerY * SIZE_OF_GRID)
                showSudokuNumberSelectorDialog(x, y)
            }
        }


        updateSudoku(true)

        thread {
            val solver = BacktrackingSolver()
            solver.solve(sudokuData.sudoku)
        }
    }

    private fun updateSudoku(initialSetup: Boolean = false){
        //Update numbers
        for(i in 0 until SIZE_OF_BOARD){
            val gridData = lookupGridIndices[i].map { sudokuData.sudoku.state[it] }.toTypedArray()
            if(!initialSetup)gridControllers[i].displayGrid(gridData)
            else gridControllers[i].displayFixedGrid(gridData)
        }
    }

    private fun showSudokuNumberSelectorDialog(x: Int, y: Int){
        val dialog = SudokuNumberSelectorDialog(contentGridPane.scene.window)
        dialog.showAndWait().ifPresent {
            if(!sudokuData.sudoku.setCell(it, x, y)){
                val alert = Alert(AlertType.INFORMATION).apply {
                    title = "Wrong move"
                    contentText = "Wrong move"
                }
                alert.showAndWait()
            }
            updateSudoku()
        }
    }

    companion object {
        var lookupGridIndices: Array<Array<Int>> = Array(SIZE_OF_BOARD){
            when(it + 1){
                1 -> arrayOf( 0,  1,  2,
                              9, 10, 11,
                             18, 19, 20)
                2 -> arrayOf( 3,  4,  5,
                             12, 13, 14,
                             21, 22, 23)
                3 -> arrayOf( 6,  7,  8,
                             15, 16, 17,
                             24, 25, 26)

                4 -> arrayOf(27, 28, 29,
                             36, 37, 38,
                             45, 46, 47)
                5 -> arrayOf(30, 31, 32,
                             39, 40, 41,
                             48, 49, 50)
                6 -> arrayOf(33, 34, 35,
                             42, 43, 44,
                             51, 52, 53)

                7 -> arrayOf(54, 55, 56,
                             63, 64, 65,
                             72, 73, 74)
                8 -> arrayOf(57, 58, 59,
                             66, 67, 68,
                             75, 76, 77)
                9 -> arrayOf(60, 61, 62,
                             69, 70, 71,
                             78, 79, 80)
                else -> throw IndexOutOfBoundsException()
            }
        }

    }

    override fun onSet(newCell: SudokuCell, x: Int, y: Int) {
        Thread.sleep(10)
        Platform.runLater {
            updateSudoku()
        }
    }



}