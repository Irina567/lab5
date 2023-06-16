package com.irinaExamlpe.petApplication.service

import com.irinaExamlpe.petApplication.model.User
import com.irinaExamlpe.petApplication.repository.UserRepository
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {

    fun save(user: User): User {
        return userRepository.save(user)
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

}
