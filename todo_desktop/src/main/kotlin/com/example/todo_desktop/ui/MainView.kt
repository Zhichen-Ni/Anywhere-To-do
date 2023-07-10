package com.example.todo_desktop.ui

import com.example.todo_desktop.service.RunCommandService
import com.example.todo_desktop.service.ServerConfigUpdateService
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos.CENTER
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.BorderPane
import tornadofx.*
import java.io.File
import javax.swing.text.html.ListView


class MainView: View() {
    //val ToDoListView: ToDoListView by inject<ToDoListView>()

    val runCommandSerivce : RunCommandService = RunCommandService()

    val a = javaClass.getResourceAsStream("main_view.fxml")

    override val root : BorderPane by fxml(a)

    val toDoList : ListView by fxid("toDo_content_list")

    val myList : com.example.todo_desktop.ui.ToDoListView by inject()

    val subjectList : SubjectListView by inject()

    val headerView : HeaderView by inject()

    val userID = SimpleStringProperty()

    val password = SimpleStringProperty()

    val serverConfigUpdateService: ServerConfigUpdateService = ServerConfigUpdateService()

    init {
        root.setPrefSize(1200.0, 720.0)
        title = "Anywhere ToDo Login"
        root.left = hbox {
            vbox {
                setPrefSize(400.0, 720.0)
            }
            //alignment = CENTER
            vbox {
                setPrefSize(400.0, 720.0)
                vbox{
                    setPrefSize(400.0, 150.0)
                }
                form {
                    setPrefSize(400.0, 500.0)
                    fieldset {
                        field("User ID:") {
                            textfield(userID) {
                                id = "myTextField"
                                text = "Enter your user ID"
                                setOnMouseClicked { text = "" }
                                setOnKeyPressed {
                                }
                            }
                        }
                        field("Password:") {
                            textfield(password) {
                                id = "myTextField"
                                text = "Enter your password"
                                setOnMouseClicked { text = "" }
                                setOnKeyPressed {
                                }
                            }
                        }
                        hbox {
                            setPrefSize(400.0, 50.0)
                            alignment = CENTER
                            button("Login") {
                                setPrefSize(100.0, 30.0)
                                action {
                                    serverConfigUpdateService.updateCredential(userID, password)
                                    if (attemptSync()) {
                                        title = "Anywhere ToDo"
                                        root.top = headerView.root
                                        root.center = myList.root
                                        root.left = subjectList.root
                                    }
                                }
                            }
                            vbox {
                                setPrefSize(20.0, 50.0)
                            }
                            button("Sign up") {
                                setPrefSize(100.0, 30.0)
                                //sign-up --username linus --password 123456
                                action {
                                    runCommandSerivce.runCommand(
                                        "./todo-cli-jvm sign-up --username "+userID.getValue()+" --password "+password.getValue(),
                                        File("./bin"))
                                    serverConfigUpdateService.updateCredential(userID, password)
                                    attemptSync()
                                    title = "Anywhere ToDo"
                                    root.top = headerView.root
                                    root.center = myList.root
                                    root.left = subjectList.root
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun attemptSync(): Boolean {
        val syncStatus: String = runCommandSerivce.runCommand("./todo-cli-jvm sync-from-server", File("./bin"))
        println(syncStatus)
        runCommandSerivce.runCommand("rm data.db", File("./bin"))
        runCommandSerivce.runCommand("./todo-cli-jvm sync-from-server", File("./bin"))
        return syncStatus == "Synchronization successful.\n"
    }
}



