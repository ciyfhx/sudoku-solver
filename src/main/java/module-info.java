module com.ciyfhx.sudoku.solver {
    requires java.base;
    requires kotlin.stdlib;

    requires com.google.guice;

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens com.ciyfhx.sudoku;
    opens com.ciyfhx.sudoku.controller;

}