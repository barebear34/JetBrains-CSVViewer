package com.csvviewer

import javafx.application.Application
import javafx.fxml.FXMLLoader.load
import javafx.scene.Scene
import javafx.stage.Stage


class App: Application() {

    override fun start(primaryStage: Stage?) {

        primaryStage?.scene = Scene(load(javaClass.getResource("/fxml/MainScreen.fxml")))
        primaryStage?.isResizable = false
        primaryStage?.title = "CSV-Viewer"
        primaryStage?.show()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(App::class.java)
        }
    }
}