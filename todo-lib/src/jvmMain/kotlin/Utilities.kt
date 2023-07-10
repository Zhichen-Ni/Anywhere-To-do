@file:Suppress("unused")

package edu.uwaterloo.cs.todo.lib

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

actual const val configFileName: String = "config.json"
actual const val realm: String = "edu.uwaterloo.cs.todo"

@OptIn(ExperimentalSerializationApi::class)
actual fun readConfigFile(configFilePath: String): Pair<Boolean, CloudServiceConfig?> {
    val configFile = File(configFilePath)

    return if (configFile.exists()) {
        try {
            val output = Json.decodeFromStream<CloudServiceConfig>(configFile.inputStream())
            Pair(true, output)
        } catch (_: Exception) {
            Pair(false, null)
        }
    } else Pair(true, null)
}

@OptIn(ExperimentalSerializationApi::class)
actual fun writeConfigFile(config: CloudServiceConfig, configFilePath: String): Boolean {
    val configFile = File(configFilePath)

    return try {
        Json.encodeToStream(config, configFile.outputStream())
        true
    } catch (_: Exception) {
        false
    }
}

actual fun getHashedPassword(userName: String, password: String): ByteArray =
    MessageDigest.getInstance("MD5").digest("$userName:$realm:$password".toByteArray(UTF_8))

actual fun breakLines(s: String, lineWidth: Int): String {
    return buildString {
        var currentLineLength = 0
        for (word in s.split(Regex("\\\\s+"))) {
            if (currentLineLength + 1 + word.length > lineWidth) {
                append("\n")
                currentLineLength = 0
            } else {
                append(" ")
                currentLineLength++
            }

            append(word)
            currentLineLength += word.length
        }
    }
}

actual fun serializeItemList(list: List<TodoItemModel>): String {
    return Json.encodeToString(list)
}

actual fun deserializeItemList(json: String): List<TodoItemModel> {
    return Json.decodeFromString(json)
}

actual fun serializeCategoryList(list: List<TodoCategoryModel>): String {
    return Json.encodeToString(list)
}

actual fun deserializeCategoryList(json: String): List<TodoCategoryModel> {
    return Json.decodeFromString(json)
}