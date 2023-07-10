package com.example.todo_desktop.ui

import tornadofx.*
import javax.swing.text.html.ImageView
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient

class Banner : View ("Banner")  {
    override val root = vbox {
        vbox{
            setPrefSize(1200.0, 110.0)
            setStyle( "-fx-background-color: linear-gradient(to top left, #000000, #b51d1d)")
        }
        /*
        vbox{
            setPrefSize(1200.0, 5.0)
            setStyle( "-fx-background-color: linear-gradient(#000000 20%, #ff8c00 65%, #000000 100%")
        }
         */
    }
}