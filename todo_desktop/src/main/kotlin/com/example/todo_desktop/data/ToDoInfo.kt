package com.example.todo_desktop.data

import java.time.LocalDate
import java.util.*

class ToDoInfo(val info: String = "", val prio: Int = 3, val date: LocalDate? = LocalDate.now(),
               val fav: Boolean = false, val uuid: UUID
) {
    var author = ""
    var content = info
    var priority = prio
    var dueDate = date
    var isCompleted = false
    var isStared = fav
    var uniqueID = uuid
}