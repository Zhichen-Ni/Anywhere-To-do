package edu.uwaterloo.cs.todo.lib

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.util.*

@Suppress("unused")
@Serializable
actual data class TodoItemModel(
    @Serializable(with = UUIDAsStringSerializer::class)
    val uniqueId: UUID,
    @Serializable(with = UUIDAsStringSerializer::class)
    val categoryId: UUID,
    val name: String,
    val description: String,
    val favoured: Boolean,
    val importance: ItemImportance,
    val deadline: LocalDate? = null,
    val modifiedTime: LocalDateTime
)