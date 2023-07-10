package edu.uwaterloo.cs.todo.lib

import kotlinx.serialization.Serializable

@Suppress("unused")
@Serializable
actual data class UserModel(
    val name: String,
    val hashedPassword: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserModel

        if (name != other.name) return false
        if (!hashedPassword.contentEquals(other.hashedPassword)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + hashedPassword.contentHashCode()
        return result
    }
}