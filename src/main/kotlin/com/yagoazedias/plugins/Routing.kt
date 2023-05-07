package com.yagoazedias.plugins

import com.yagoazedias.config.ModulesConfig
import com.yagoazedias.controller.UserController
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.application.*
import org.kodein.di.generic.instance

fun Application.configureRouting() {
    val userController by ModulesConfig.kodein.instance<UserController>()
    routing {
        get("/search") {
            if (call.request.queryParameters["query"].isNullOrBlank()) {
                call.respond(HttpStatusCode.BadRequest, "You must provide query param")
            }
            val users = userController.getAll(this.context)
            call.respond(HttpStatusCode.OK, users)
        }
    }
}
