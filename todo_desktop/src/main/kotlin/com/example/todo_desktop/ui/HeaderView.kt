package com.example.todo_desktop.ui
import com.example.todo_desktop.app.Styles
import com.example.todo_desktop.ui.Banner
import javafx.scene.Parent
import tornadofx.*
import javafx.scene.layout.HBox
import javafx.scene.paint.Color


class HeaderView : View ("Header View") {
    val osName = System.getProperty("os.name")

    val mBanner : Banner by inject()

    override val root = vbox {
        menubar() {
            if (osName.startsWith("Mac")) {
                useSystemMenuBarProperty().set(true)
            }
            menu("Appearence") {
                item("Font")
                item("Banner Background").action {
                    colorpicker { setValue(Color.CORAL); }
                }
                item("Text Color")
            }
            menu("Help") {
                item("Help Page")
                item("Report an Issue")
            }
        }
        add(mBanner)
    }
}