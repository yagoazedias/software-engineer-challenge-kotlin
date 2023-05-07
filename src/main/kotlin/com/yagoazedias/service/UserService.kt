package com.yagoazedias.service

import com.yagoazedias.model.User
import com.yagoazedias.respository.UserRepository

class UserService(private val userRepository: UserRepository) {
    fun getAll(from: Int = 0, size: Int = 150): List<User?> {
        userRepository.getAll(from, size).apply {
            return this
        }
    }
}