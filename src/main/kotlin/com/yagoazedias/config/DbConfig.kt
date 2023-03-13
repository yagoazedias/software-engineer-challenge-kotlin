package com.yagoazedias.config

import org.h2.tools.Server
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DbConfig {
    fun setup(jdbcUrl: String, username: String, password: String) {
        Server.createPgServer()
        val config = HikariConfig().also { config ->
            config.jdbcUrl = jdbcUrl
            config.username = username
            config.password = password
        }
        Database.connect(HikariDataSource(config))
    }
}