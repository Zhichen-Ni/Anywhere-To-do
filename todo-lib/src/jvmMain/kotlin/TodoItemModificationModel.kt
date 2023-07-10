package edu.uwaterloo.cs.todo.lib

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Suppress("unused")
@Serializable
actual data class TodoItemModificationModel(

    val name: String? = null,
    val description: String? = null,
    val favoured: Boolean? = null,
    val importance: ItemImportance? = null,
    val deadline: LocalDate? = null,
    val modifiedTime: LocalDateTime
)