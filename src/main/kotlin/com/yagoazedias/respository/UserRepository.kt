package com.yagoazedias.respository

import com.yagoazedias.model.User
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


internal object Users : UUIDTable() {
    val name: Column<String> = varchar("name", 200)
    val username: Column<String> = varchar("username", 150).uniqueIndex()
    val priority: Column<Int> = integer("priority").index()

    fun toDomain(row: ResultRow): User {
        return User(
            id = row[Users.id].value,
            name = row[name],
            username = row[username],
            priority = row[priority],
        )
    }
}

fun String.print(printer: (String) -> String) {
   printer("$$this")
}


class UserRepository {
    init {
        transaction {
            SchemaUtils.create(Users)
        }
    }

    fun getAll(from: Int, size: Int): List<User?> {
        return transaction {
            Users.selectAll()
                .limit(size, from.toLong())
                .orderBy(Users.priority, order = SortOrder.ASC)
                .map {
                Users.toDomain(it)
            }
        }
    }

    fun insertList(users: List<User>) {
        return transaction {
            Users.batchInsert(users) {
                this[Users.id] = it.id
                this[Users.name] = it.name
                this[Users.username] = it.username
                this[Users.priority] = it.priority
            }
        }
    }
}