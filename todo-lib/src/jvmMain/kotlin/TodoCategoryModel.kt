package edu.uwaterloo.cs.todo.lib

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import java.util.*

@Suppress("unused")
@Serializable
actual data class TodoCategoryModel(
    @Serializable(with = UUIDAsStringSerializer::class)
    val uniqueId: UUID,
    val name: String,
    val favoured: Boolean,
    val modifiedTime: LocalDateTime
)