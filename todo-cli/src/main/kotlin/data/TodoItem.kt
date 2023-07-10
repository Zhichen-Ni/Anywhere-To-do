package data

import data.TodoCategories.clientDefault
import edu.uwaterloo.cs.todo.lib.ItemImportance
import edu.uwaterloo.cs.todo.lib.TodoItemModel
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class TodoItem(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TodoItem>(TodoItems)

    var uniqueId by TodoItems.uniqueId.clientDefault { UUID.randomUUID() }
    var categoryId by TodoItems.categoryId
    var name by TodoItems.name
    var description by TodoItems.description
    var favoured by TodoItems.favoured
    var importance: ItemImportance by TodoItems.importance.transform(
        { it.ordinal },
        { ItemImportance.values()[it] }
    )
    var modifiedTime: LocalDateTime by TodoItems.modifiedTime
        .clientDefault { Clock.System.now().epochSeconds }
        .transform(
            { it.toInstant(TimeZone.currentSystemDefault()).epochSeconds },
            { Instant.fromEpochSeconds(it).toLocalDateTime(TimeZone.currentSystemDefault()) }
        )
    var deadline: LocalDate? by TodoItems.deadline.transform(
        { it?.toEpochDays() },
        { if (it === null) null else LocalDate.fromEpochDays(it) }
    )

    fun toModel(): TodoItemModel =
        TodoItemModel(uniqueId, categoryId, name, description, favoured, importance, deadline, modifiedTime)
}