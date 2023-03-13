package com.yagoazedias

import com.yagoazedias.config.DbConfig
import com.yagoazedias.config.configureUsers
import io.ktor.server.application.Application
import com.yagoazedias.plugins.configureRouting
import com.yagoazedias.plugins.configureSerialization

fun main(args: Array<String>) {
    DbConfig.setup("jdbc:h2:mem:main", "", "")
    configureUsers()
    return io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
}
