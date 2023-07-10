package edu.uwaterloo.cs.todo_webservice.Data

import java.time.LocalDate

data class Todo (
    val info : String,
    val prio: Int = 3,
    val date: LocalDate = LocalDate.now(),
    val fav: Boolean = false
)