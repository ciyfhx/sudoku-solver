package com.ciyfhx.sudoku

import com.google.inject.Guice
import com.google.inject.Injector
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage


class Main : Application() {
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val injector: Injector = Guice.createInjector(DataModule())
        val loader = FXMLLoader(javaClass.classLoader.getResource("sudoku-gui.fxml")).apply {
             setControllerFactory {
                 injector.getInstance(it)
             }
        }
        val root = loader.load<Parent>()
        primaryStage.title = "Sudoku Solver"
        primaryStage.scene = Scene(root, 700.0, 700.0)
        primaryStage.show()
    }

}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}
