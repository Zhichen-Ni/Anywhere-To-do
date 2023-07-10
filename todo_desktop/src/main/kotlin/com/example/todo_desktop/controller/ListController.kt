package com.example.todo_desktop.controller

import com.example.todo_desktop.app.Styles
import com.example.todo_desktop.common.constant
import com.example.todo_desktop.data.ToDoInfo
import tornadofx.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.time.format.DateTimeFormatter

class ListController : Controller() {
    var sortOption = constant.SORT_BY_DEFAULT

    var currDueDate : LocalDate = LocalDate.now()

    var currPriority : Int = 3

    fun deleteToDo(item : ToDoInfo?, records: MutableList<ToDoInfo>) {
        triggerSortOption(records)
    }

    fun addToDo(records: MutableList<ToDoInfo>) {
        triggerSortOption(records)
    }

    fun setPriority(prio : String) {
        currPriority = priorityToInt(prio)
    }

    fun TodayInfo(): String {
        val sdf = SimpleDateFormat("EEEE, MMMM dd")
        return sdf.format(Date())
    }

    fun convertDate(date : String) {
        val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        currDueDate = when (date) {
            constant.DUE_DATE_PICK_DATE -> return
            constant.DUE_DATE_TITLE -> return
            constant.DUE_DATE_TODAY -> LocalDate.now()
            constant.DUE_DATE_TOMORROW -> LocalDate.now().plusDays(1)
            else -> LocalDate.parse(date, formatter)
        }
        println("currDueDate ${currDueDate.toString()}");
    }

    fun DateToString(date : LocalDate?) : String {
        val formatter : DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return when (date) {
            LocalDate.now() -> "Today"
            LocalDate.now().plusDays(1) -> "Tomorrow"
            else -> date.toString()
        }
    }

    fun priorityToInt(priority: String) : Int {
        return when (priority) {
            constant.PRIORITY_CRITICAL -> 1
            constant.PRIORITY_HIGH -> 2
            constant.PRIORITY_MEDIUM -> 3
            constant.PRIORITY_LOW -> 4
            else -> 5
        }
    }

    fun IntToPrioiry(value : Int) : String {
        return when (value) {
            1 -> constant.PRIORITY_CRITICAL
            2 -> constant.PRIORITY_HIGH
            3 -> constant.PRIORITY_MEDIUM
            4 -> constant.PRIORITY_LOW
            else -> constant.PRIORITY_VERY_LOW
        }
    }

    fun sortByPriority(records : MutableList<ToDoInfo>) {
        records.sortBy { it.priority }
    }

    fun sortByDueDate(records: MutableList<ToDoInfo>) {
        records.sortBy { it.dueDate }
    }

    fun sortByDefault(records: MutableList<ToDoInfo>) { }

    fun sortByStar(records: MutableList<ToDoInfo>) {
        records.sortByDescending {
            it.isStared
        }
    }

    fun triggerSortOption(records: MutableList<ToDoInfo>) {
        when (sortOption) {
            constant.SORT_BY_PRIORITY -> sortByPriority(records)
            constant.SORT_BY_DUE_DATE -> sortByDueDate(records)
            constant.SORT_BY_DEFAULT -> sortByDefault(records)
            constant.SORT_BY_STAR -> sortByStar(records)
        }
    }
    fun getStarStyle(todo : ToDoInfo): CssRule {
        return when(todo.isStared) {
            true -> Styles.redHeartIcon
            false -> Styles.heartIcon
        }
    }

    fun changeStarStatus(todo : ToDoInfo) : CssRule{
        todo.isStared = !todo.isStared
        return getStarStyle(todo)
    }
}