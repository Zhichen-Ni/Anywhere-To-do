package com.example.todo_desktop.ui
import com.example.todo_desktop.app.Styles
import com.example.todo_desktop.service.RunCommandService
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.stage.Stage
import tornadofx.*
import java.io.IOException


class MainApp: App(MainView::class, Styles::class) {
    init {
        reloadStylesheetsOnFocus()
    }
}

fun main() {
    val mRunCommandService = RunCommandService()

    val commands = arrayOf("git", "status")

    //println(mRunCommandService.runCommand(commands))
    launch<MainApp>()
}
