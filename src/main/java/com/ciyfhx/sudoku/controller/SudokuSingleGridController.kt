package com.ciyfhx.sudoku.controller

import com.ciyfhx.sudoku.game.SudokuCell
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import java.net.URL
import java.util.*

class SudokuSingleGridController : Initializable {

    @FXML
    private lateinit var cell1: Label
    @FXML
    private lateinit var cell2: Label
    @FXML
    private lateinit var cell3: Label
    @FXML
    private lateinit var cell4: Label
    @FXML
    private lateinit var cell5: Label
    @FXML
    private lateinit var cell6: Label
    @FXML
    private lateinit var cell7: Label
    @FXML
    private lateinit var cell8: Label
    @FXML
    private lateinit var cell9: Label

    private var gridListener: GridListener? = null

    fun setGridListener(listener: GridListener){
        this.gridListener = listener
    }

    private fun callGridListener(index: Int){
        gridListener?.onSelect(index)
    }

    override fun initialize(url: URL?, resources: ResourceBundle?) {
        cell1.setOnMouseClicked{ callGridListener(0) }
        cell2.setOnMouseClicked{ callGridListener(1) }
        cell3.setOnMouseClicked{ callGridListener(2) }
        cell4.setOnMouseClicked{ callGridListener(3) }
        cell5.setOnMouseClicked{ callGridListener(4) }
        cell6.setOnMouseClicked{ callGridListener(5) }
        cell7.setOnMouseClicked{ callGridListener(6) }
        cell8.setOnMouseClicked{ callGridListener(7) }
        cell9.setOnMouseClicked{ callGridListener(8) }
    }

    fun displayFixedGrid(numbers: Array<SudokuCell>){

        if(numbers[0] != SudokuCell.EMPTY){
            cell1.styleClass.add("fixed")
        }else{
            cell1.styleClass.remove("fixed")
        }
        if(numbers[1] != SudokuCell.EMPTY){
            cell2.styleClass.add("fixed")
        }else{
            cell2.styleClass.remove("fixed")
        }
        if(numbers[2] != SudokuCell.EMPTY){
            cell3.styleClass.add("fixed")
        }else{
            cell3.styleClass.remove("fixed")
        }
        if(numbers[3] != SudokuCell.EMPTY){
            cell4.styleClass.add("fixed")
        }else{
            cell4.styleClass.remove("fixed")
        }
        if(numbers[4] != SudokuCell.EMPTY){
            cell5.styleClass.add("fixed")
        }else{
            cell5.styleClass.remove("fixed")
        }
        if(numbers[5] != SudokuCell.EMPTY){
            cell6.styleClass.add("fixed")
        }else{
            cell6.styleClass.remove("fixed")
        }
        if(numbers[6] != SudokuCell.EMPTY){
            cell7.styleClass.add("fixed")
        }else{
            cell7.styleClass.remove("fixed")
        }
        if(numbers[7] != SudokuCell.EMPTY){
            cell8.styleClass.add("fixed")
        }else{
            cell8.styleClass.remove("fixed")
        }
        if(numbers[8] != SudokuCell.EMPTY){
            cell9.styleClass.add("fixed")
        }else{
            cell9.styleClass.remove("fixed")
        }

        displayGrid(numbers)
    }

    fun displayGrid(numbers: Array<SudokuCell>){
        displaySudokuCellInLabel(cell1, numbers[0])
        displaySudokuCellInLabel(cell2, numbers[1])
        displaySudokuCellInLabel(cell3, numbers[2])
        displaySudokuCellInLabel(cell4, numbers[3])
        displaySudokuCellInLabel(cell5, numbers[4])
        displaySudokuCellInLabel(cell6, numbers[5])
        displaySudokuCellInLabel(cell7, numbers[6])
        displaySudokuCellInLabel(cell8, numbers[7])
        displaySudokuCellInLabel(cell9, numbers[8])
    }

    private fun displaySudokuCellInLabel(label: Label, cell: SudokuCell){
        if(cell != SudokuCell.EMPTY){
            label.text = cell.value.toString()
        }else{
            label.text = ""
        }
    }
    fun interface GridListener {
        fun onSelect(index: Int)
    }




}