@file:Suppress("unused")

package edu.uwaterloo.cs.todo.lib

expect val configFileName: String
expect val realm: String

expect fun readConfigFile(configFilePath: String = configFileName): Pair<Boolean, CloudServiceConfig?>

expect fun writeConfigFile(config: CloudServiceConfig, configFilePath: String = configFileName): Boolean

expect fun getHashedPassword(userName: String, password: String): ByteArray

expect fun breakLines(s: String, lineWidth: Int): String

expect fun serializeItemList(list: List<TodoItemModel>): String

expect fun deserializeItemList(json: String): List<TodoItemModel>

expect fun serializeCategoryList(list: List<TodoCategoryModel>): String

expect fun deserializeCategoryList(json: String): List<TodoCategoryModel>