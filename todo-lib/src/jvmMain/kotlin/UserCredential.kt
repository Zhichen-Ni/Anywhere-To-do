package edu.uwaterloo.cs.todo.lib

import kotlinx.serialization.Serializable

@Serializable
actual data class UserCredential(val userName: String, val password: String)