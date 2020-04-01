package com.csvviewer.gui

import com.csvviewer.python.InterpreterFinder
import com.csvviewer.python.CsvLoader
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField

class MainController {

    @FXML
    private lateinit var csv: TextField

    @FXML
    private lateinit var interpreter: TextField

    @FXML
    private lateinit var tableView: TableView<ObservableList<String>>

    private val data = FXCollections.observableArrayList<ObservableList<String>>()

    private lateinit var pathToInterpreter: String

    fun initialize() {

        val foundPath = InterpreterFinder.findPathToPythonInterpreter()
        if (foundPath == "")
            interpreter.promptText = "Can't find path to interpreter. Enter by yourself"
        else {
            interpreter.promptText = "Found path to interpreter"
            pathToInterpreter = foundPath
            CsvLoader.pathToInterpreter = pathToInterpreter
        }
    }

    /*
     * Function blocks main thread while loading file
     * It does not affect on functionality of program
     * because user can work with one table at the current moment
     */
    @FXML
    fun onLoad() {

        // prepare table for new data
        tableView.columns.clear()
        tableView.items.clear()

        if (CsvLoader.pathToInterpreter == "")
            CsvLoader.pathToInterpreter = interpreter.text

        val table = CsvLoader.getTable(pathToCsv = csv.text)

        if (table.columns.isEmpty() && table.rows.isEmpty()) {
            csv.promptText = "Enter valid path"
            csv.clear()
            return
        }

        // adding columns
        val tableColumns = table.columns.map { name ->
            TableColumn<ObservableList<String>, String>(name)
        }

        // estimated max number of columns of the same size to fit in the window without scroll
        if (tableColumns.size <= 6) {
            tableView.columnResizePolicy = TableView.CONSTRAINED_RESIZE_POLICY
        }

        tableColumns.forEach { col ->
            col.setCellValueFactory {
                ReadOnlyObjectWrapper(it.value[tableColumns.indexOf(col)])
            }
            tableView.columns.add(col)
        }

        // populating table with data
        table.rows.map {
            it.items
        }.forEach {
            data.add(FXCollections.observableArrayList(it))
        }
        tableView.items = data

        // clear text fields
        csv.clear()
        interpreter.clear()
        interpreter.promptText = ""
        csv.promptText = "Path to csv"

        // set resize property to default value
        tableView.columnResizePolicy = TableView.UNCONSTRAINED_RESIZE_POLICY
    }
}