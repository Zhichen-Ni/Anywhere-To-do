package com.example.todo_desktop.common
import com.example.todo_desktop.data.ToDoInfo
import com.example.todo_desktop.service.RunCommandService
import tornadofx.*
import java.util.*

class constant {
    val runCommandSerivce : RunCommandService = RunCommandService()
    //val tmpUUID: UUID = deserializeItemList(runCommandSerivce.runCommand("./todo-cli-jvm list-categories --json", File("./bin")))
    companion object {
        val DUE_DATE_TODAY = "Today"
        val DUE_DATE_TOMORROW = "Tomorrow"
        val DUE_DATE_PICK_DATE = "Pick a date"
        val DUE_DATE_TITLE = "Due Date"

        //database
        val DB_URL = "jdbc:mysql://localhost:3306/anywhere_todo"
        val DB_TYPE = "com.mysql.cj.jdbc.Driver"
        val DB_USER_NAME = "root"
        val DB_PASSWORD = "123456"
        val DB_POOL_SIZE = 10

        val DB_CONFIG = dbConfig(DB_URL, DB_TYPE, DB_USER_NAME, DB_PASSWORD)

        // priority
        val PRIORITY_CRITICAL = "Critical"
        val PRIORITY_HIGH = "High"
        val PRIORITY_MEDIUM = "Medium"
        val PRIORITY_LOW = "Low"
        val PRIORITY_VERY_LOW = "Very Low"

        // sort option
        val SORT_BY_DEFAULT = "Default"
        val SORT_BY_PRIORITY = "Priority"
        val SORT_BY_DUE_DATE = "Due Date"
        val SORT_BY_STAR = "Starred"

        // sort option index
        val SORT_DEFAULT_INDEX = 0
        val SORT_PRIORITY_INDEX = 1
        val SORT_DUE_INDEX = 2
        val SORT_STAT = 3

        var curCategory: UUID? = null
        var curItems = mutableListOf<UUID>().observable()

        /*
            1: add a category
            2: delete a category
            3: fav a category
            4: unfav a category
            5: add a todoitem
            6: delete a todoitem
            7: fav a todoitem
            8: unfav a todoitem
         */
        var undoCatOpStack = ArrayDeque<catOp>()
        var redoCatOpStack = ArrayDeque<catOp>()
        var undoItemOpStack = ArrayDeque<itemOp>()
        var redoItemOpStack = ArrayDeque<itemOp>()

        var undoCatName = ""
        var undoCatFav = false
        var redoCatName = ""
        var redoCatFav = false

        var undoTodoItem = ToDoInfo("", 3, date = null, false, UUID.randomUUID())
        var redoTodoItem = ToDoInfo("", 3, date = null, false, UUID.randomUUID())
    }
}

class dbConfig(val url: String, val type: String, val user: String, val password: String)
class catOp(val opCode: Int, val name: String, val fav: Boolean, val uuid: UUID?)
class itemOp(val opCode: Int, val toDoInfo: ToDoInfo)