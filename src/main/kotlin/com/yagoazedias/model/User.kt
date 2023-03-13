package com.yagoazedias.model

import java.util.UUID


data class User(
    val id: UUID,
    val name: String,
    val username: String,
    val priority: Int,
)