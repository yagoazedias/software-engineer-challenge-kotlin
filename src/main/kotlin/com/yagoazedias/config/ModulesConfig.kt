package com.yagoazedias.config

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

object ModulesConfig {
    private val userModele = Kodein.Module("USER") {
        bind() from singleton { UserController }
    }
}