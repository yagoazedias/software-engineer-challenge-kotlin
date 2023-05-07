package com.yagoazedias.config

import com.yagoazedias.model.User
import com.yagoazedias.respository.UserRepository
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*


fun readCsv(inputStream: InputStream, priorityMap: Map<String, Int>): List<User> {
    val reader = inputStream.bufferedReader()
    return reader.lineSequence()
        .filter { it.isNotBlank() }
        .map {
            val (id, name, username) = it.split(',', ignoreCase = false, limit = 3)
            User(UUID.fromString(id), name, username, priorityMap[id] ?: 3)
        }.toList()
}

fun getPriorityMapByInputStreams(inputStreamPriorityOne: FileInputStream, inputStreamPriorityTwo: FileInputStream): Map<String, Int> {
    val mutableMap: MutableMap<String, Int> = mutableMapOf()
    inputStreamPriorityOne.bufferedReader().forEachLine { mutableMap[it] =  1 }
    inputStreamPriorityTwo.bufferedReader().forEachLine { mutableMap[it] = 2 }
    return mutableMap
}

fun configureUsers() {
    val databasePath = object {}.javaClass.classLoader.getResource("database.csv")?.path
    val relevancePriorityOnePath = object {}.javaClass.classLoader.getResource("lista_relevancia_1.txt")?.path
    val relevancePriorityTwoPath = object {}.javaClass.classLoader.getResource("lista_relevancia_2.txt")?.path

    val inputStreamPriorityOne = relevancePriorityOnePath?.let { File(it).inputStream() }
    val inputStreamPriorityTwo = relevancePriorityTwoPath?.let { File(it).inputStream() }

    val priorityMap = getPriorityMapByInputStreams(inputStreamPriorityOne!!, inputStreamPriorityTwo!!)
    val users = databasePath?.let { File(it).inputStream() }?.let { readCsv(it, priorityMap) }

    val repo = UserRepository()

    if (users != null) {
        repo.insertList(users)
    }
}