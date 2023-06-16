package com.irinaExamlpe.petApplication.repository

import com.irinaExamlpe.petApplication.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String): User?
}
