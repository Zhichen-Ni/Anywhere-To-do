package edu.uwaterloo.cs.todo.lib

import kotlinx.serialization.Serializable

@Serializable
actual data class CloudServiceConfig(val enabled: Boolean, val serverUrl: String?, val userCredential: UserCredential?)