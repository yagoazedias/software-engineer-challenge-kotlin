package com.yagoazedias.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    val userController by ModulesConfig.kodein.instance<UserController>()

    routing {
        get("/search") {
            if (call.request.queryParameters["query"].isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "You must provide query param")
            }
        }
    }
}
