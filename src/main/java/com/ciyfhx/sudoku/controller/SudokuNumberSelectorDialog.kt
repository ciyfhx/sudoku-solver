package com.ciyfhx.sudoku.controller

import com.ciyfhx.sudoku.game.SudokuCell
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.stage.Modality
import javafx.stage.Window
import java.net.URL
import java.util.*

class SudokuNumberSelectorDialog(
    owner: Window
) : Dialog<SudokuCell>(), Initializable{

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

    private var selectedCell = SudokuCell.ONE


    init {
        val loader = FXMLLoader(javaClass.classLoader.getResource("sudoku-number-selector-dialog.fxml")).apply {
            setController(this@SudokuNumberSelectorDialog)
        }
        val dialogPane = loader.load<DialogPane>()

        initOwner(owner)
        initModality(Modality.APPLICATION_MODAL)

        isResizable = true
        title = "Number select"
        this.dialogPane = dialogPane
        setResultConverter {
            if(!Objects.equals(ButtonBar.ButtonData.OK_DONE, it.buttonData)) null
            else selectedCell
        }
    }


    override fun initialize(url: URL?, resources: ResourceBundle?) {
        cell1.setOnMouseClicked{
            clearSelected()
            cell1.styleClass.add("selected")
            setSelectedCellValue(SudokuCell.ONE)
        }
        cell2.setOnMouseClicked{
            clearSelected()
            cell2.styleClass.add("selected")
            setSelectedCellValue(SudokuCell.TWO)
        }
        cell3.setOnMouseClicked{
            clearSelected()
            cell3.styleClass.add("selected")
            setSelectedCellValue(SudokuCell.THREE)
        }
        cell4.setOnMouseClicked{
            clearSelected()
            cell4.styleClass.add("selected")
            setSelectedCellValue(SudokuCell.FOUR)
        }
        cell5.setOnMouseClicked{
            clearSelected()
            cell5.styleClass.add("selected")
            setSelectedCellValue(SudokuCell.FIVE)
        }
        cell6.setOnMouseClicked{
            clearSelected()
            cell6.styleClass.add("selected")
            setSelectedCellValue(SudokuCell.SIX)
        }
        cell7.setOnMouseClicked{
            clearSelected()
            cell7.styleClass.add("selected")
            setSelectedCellValue(SudokuCell.SEVEN)
        }
        cell8.setOnMouseClicked{
            clearSelected()
            cell8.styleClass.add("selected")
            setSelectedCellValue(SudokuCell.EIGHT)
        }
        cell9.setOnMouseClicked{
            clearSelected()
            cell9.styleClass.add("selected")
            setSelectedCellValue(SudokuCell.NINE)
        }
    }

    private fun clearSelected(){
        cell1.styleClass.remove("selected")
        cell2.styleClass.remove("selected")
        cell3.styleClass.remove("selected")
        cell4.styleClass.remove("selected")
        cell5.styleClass.remove("selected")
        cell6.styleClass.remove("selected")
        cell7.styleClass.remove("selected")
        cell8.styleClass.remove("selected")
        cell9.styleClass.remove("selected")
    }

    private fun setSelectedCellValue(cell: SudokuCell) {
        this.selectedCell = cell
    }


}