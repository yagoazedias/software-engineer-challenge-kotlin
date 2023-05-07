package com.yagoazedias.config

import com.yagoazedias.controller.UserController
import com.yagoazedias.respository.UserRepository
import com.yagoazedias.service.UserService
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

object ModulesConfig {
    private val userModule = Kodein.Module("USER") {
        bind() from singleton { UserController(instance()) }
        bind() from singleton { UserService(instance()) }
        bind() from singleton { UserRepository() }
    }

    internal val kodein = Kodein {
        import(userModule)
    }
}