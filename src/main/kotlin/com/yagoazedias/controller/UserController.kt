package com.yagoazedias.controller

import com.yagoazedias.model.User
import com.yagoazedias.service.UserService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UserController(private val userService: UserService) {
    suspend fun getAll(ctx: ApplicationCall) {
        userService.getAll().apply {
            ctx.respond(this)
        }
    }
}